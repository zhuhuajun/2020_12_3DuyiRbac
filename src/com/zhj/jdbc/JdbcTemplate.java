package com.zhj.jdbc;



import com.zhj.jdbc.pool.ConnectionPool;

import java.sql.*;

/**
 * Created by Administrator on 2020/8/24.
 */
public abstract class JdbcTemplate {

    private String driver ;
    private String url ;
    private String username ;
    private String password ;

    public JdbcTemplate(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection conn ;
    protected PreparedStatement stmt ;
    private ResultSet rs ;

    public Object executeJdbc(String sql , Object[] param){
        try{
            one();
            two();
            three();
            four(sql,param);
            Object result = five();
            return result ;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                six();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null ;

    }

    /**
     *  引入jar文件
     */
    private void one(){}

    /**
     * 加载驱动
     */
    private void two() throws ClassNotFoundException {
        //Class.forName(driver);
    }

    /**
     * 创建连接
     */
    private void three() throws SQLException, InterruptedException {
         //conn = DriverManager.getConnection(url,username,password);
        System.out.println(pool);
        conn = pool.getConnection();
    }

    /**
     * 创建预处理对象
     * @param sql
     * @throws SQLException
     */
    private void four(String sql,Object[] param) throws SQLException {
        stmt = conn.prepareStatement(sql) ;
        for(int i=0;i<param.length;i++) {
            stmt.setObject(i + 1, param[i]);
        }
    }

    /**
     * 执行sql
     *  ResultSet rs = stmt.executeQuery();
     *  int count = stmt.executeUpdate() ;
     */
    protected abstract Object five() throws SQLException  ;

    /**
     * 各种关闭
     */
    private void six() throws SQLException {
        if(rs != null){
            rs.close();
        }
        if(stmt != null){
            stmt.close();
        }
        if(conn != null){
            conn.close();
        }
    }

    private ConnectionPool pool ;
    public void setPool(ConnectionPool pool) {
        this.pool = pool;
    }
}
