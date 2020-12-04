package com.zhj.jdbc;


import com.zhj.jdbc.pool.ConnectionPool;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2020/8/26.
 * 负责创建JdbcUtil工具
 */
public class JdbcFactory {

    public JdbcFactory(){
        this("db.properties") ;
    }

    /**
     * 人为规定的读取的文件都放置在src下
     * new JdbcFactory("db.properties");
     * @param fileName
     */
    public JdbcFactory(String fileName){
        /*
            简单理解为获得src目录下的文件路径
            其实获得的是classpath目录下的文件
         */
        String path = Thread.currentThread().getContextClassLoader()
                .getResource(fileName).getPath();
        File file = new File(path);
        readFile(file);
    }

    /**
     * 使用者根据程序，自己获得配置文件。交给工厂读取
     * File file = new File("d:/z/db.propeties");
     * new JdbcFactory(file);
     * @param file
     */
    public JdbcFactory(File file){
        readFile(file);
    }

    private String driver ;
    private String url ;
    private String username ;
    private String password ;

    private Integer total ;
    private Integer maxWait ;
    private Integer minIdel ;
    private void readFile(File file){
        try {
            InputStream is = new FileInputStream(file);
            Properties p = new Properties();
            p.load(is);

            driver = p.getProperty("driver") ;
            url = p.getProperty("url");
            username=p.getProperty("username");
            password=p.getProperty("password") ;

            String total=p.getProperty("total") ;
            String maxWait=p.getProperty("maxWait") ;
            String minIdel=p.getProperty("minIdel") ;

            if(total != null && !"".equalsIgnoreCase(total)){
                this.total = Integer.parseInt(total);
            }
            if(maxWait != null && !"".equalsIgnoreCase(maxWait)){
                this.maxWait = Integer.parseInt(maxWait);
            }
            if(minIdel != null && !"".equalsIgnoreCase(minIdel)){
                this.minIdel = Integer.parseInt(minIdel);
            }

            this.createConnectionPool();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionPool pool ;
    private void createConnectionPool() throws SQLException, ClassNotFoundException {
        pool = new ConnectionPool(driver,url,username,password);
        pool.setTotal(total);
        pool.setMaxWait(maxWait);
        pool.setMinIdle(2);
    }

    public JdbcUtil getUtil(){
        JdbcUtil util =  new JdbcUtil(driver,url,username,password);
        util.setPool(pool);
        return util ;
    }

    public SqlSession getSession(){
        SqlSession session =  new SqlSession(driver,url,username,password,pool) ;
        return session ;
    }

}
