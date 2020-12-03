package com.zhj.filter;

import com.zhj.jdbc.JdbcFactory;

/**
 * @author ：
 * @date ：Created in 2020/11/26 下午 03:00
 */

public class SqlFactoryUtil {
    private static JdbcFactory jdbcFactory = new JdbcFactory("mysql.properties");
    private static JdbcFactory getJdbcFactory() {
        return jdbcFactory;
    }
}
