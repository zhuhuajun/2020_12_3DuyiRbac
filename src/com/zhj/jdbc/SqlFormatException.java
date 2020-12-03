package com.zhj.jdbc;

/**
 * Created by Administrator on 2020/8/24.
 * insert("update ..") 抛出该异常
 */
public class SqlFormatException extends RuntimeException {
    public SqlFormatException(){}

    public SqlFormatException(String msg){
        super(msg) ;

    }
}
