package com.zhj.jdbc.pool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by Administrator on 2020/8/28.
 * 连接对象的代理
 */
public class ConnectionProxy extends AbstractConnection{

    boolean closeFlag = false ;  //false不关闭，释放 。 true 关闭
    boolean useFlag = false ;// flase空闲，true 被使用。

    public ConnectionProxy(Connection conn){
        super.conn = conn ;
    }

    @Override
    public void close() throws SQLException {
        if(closeFlag == true){
            conn.close();
        }else{
            //释放连接
            useFlag = false ;
        }
    }


}
