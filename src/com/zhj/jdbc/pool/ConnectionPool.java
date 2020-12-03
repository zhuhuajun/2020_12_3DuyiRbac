package com.zhj.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/28.
 */
public class ConnectionPool {
    private List<ConnectionProxy> connections ; //装载连接池中的所有的连接
    private ConnectionGenerator generator ;
    private String driver ;
    private String url ;
    private String username ;
    private String password ;
    private Integer total = 100;//连接总数
    private Integer maxWait = 2000 ;//最大等待时间(毫秒)
    private Integer minIdle = 2 ;//最小空闲数，当连接池空闲连接数少于数量时，准备补充新连接
    public ConnectionPool(String driver,String url,String username,String password) throws ClassNotFoundException, SQLException {
        this.driver = driver ;
        this.url = url ;
        this.username = username ;
        this.password = password ;

        //创建连接池时，就自动创建一组初始连接对象
        Class.forName(driver) ;
        connections = new ArrayList(10) ;
        for(int i=0;i<10;i++){
            Connection conn = DriverManager.getConnection(url,username,password) ;
            ConnectionProxy cp = new ConnectionProxy(conn) ;
            connections.add(cp) ;
        }

        this.generator = new ConnectionGenerator();
        generator.setDaemon(true);//精灵线程或守护线程， 用户线程，守护线程
        generator.start();
    }

    public Connection getConnection() throws InterruptedException {
        //找到空闲连接，改变使用状态，返回连接对象
        int wait_time = 0 ;
        wait:while(true) {
            find:for (ConnectionProxy cp : connections) {
                if (!cp.useFlag) { // cp.useFlag == false
                    synchronized (cp) {
                        if(!cp.useFlag) {
                            //当前连接是空闲
                            cp.useFlag = true;
                            synchronized ("dmc"){
                                "dmc".notify();//唤醒
                            }
                            return cp;
                        }else{
                            continue wait;
                        }
                    }
                }
            }
            //没有空闲连接，等一会
            wait_time += 100 ;
            if(wait_time == 2000){
                //不再继续了。
                throw new ConnectionPoolException("connect time out ") ;
            }else{
                Thread.sleep(100);
                continue wait;
            }
        }
    }


    //连接生成器，连接不足是造连接
    private class ConnectionGenerator extends Thread{
        @Override
        public void run(){
            /* */
            try {
                checkAndCreate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //检测连接，发现不足创建补充连接
        public void checkAndCreate() throws SQLException, InterruptedException {
            while(true){
                int count = 0 ;//记录剩余空闲连接的数量
                for(ConnectionProxy cp : connections){
                    if(!cp.useFlag){
                        //当前连接是一个空闲连接
                        count++ ;
                    }
                }
                if(count <= minIdle){
                    //连接不充足了，补充，每次补充10个，暂定上限100
                    int add_count = 10 ;
                    if(connections.size() + 10 > total){
                        //此次补充过后，超上限了
                        add_count = total-connections.size() ;
                    }

                    for(int i=0;i<add_count;i++){
                        Connection conn = DriverManager.getConnection(url,username,password) ;
                        ConnectionProxy ncp = new ConnectionProxy(conn) ;
                        connections.add(ncp);
                    }
                }
                synchronized ("dmc"){
                    "dmc".wait();
                }
            }
        }
    }

    //-----------------------------------------------------


    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }
}

