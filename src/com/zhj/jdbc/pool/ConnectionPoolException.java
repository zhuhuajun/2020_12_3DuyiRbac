package com.zhj.jdbc.pool;

/**
 * Created by Administrator on 2020/8/28.
 * 当连接池工作时出现问题，抛出该异常
 */
public class ConnectionPoolException extends RuntimeException{
    public ConnectionPoolException(){}

    public ConnectionPoolException(String msg){
        super(msg) ;
    }
}
