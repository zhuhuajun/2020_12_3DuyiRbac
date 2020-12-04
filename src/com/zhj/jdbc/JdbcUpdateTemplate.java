package com.zhj.jdbc;

import java.sql.SQLException;

/**
 * Created by Administrator on 2020/8/24.
 * 增删改模板
 */
class JdbcUpdateTemplate extends JdbcTemplate{

    public JdbcUpdateTemplate(String driver, String url, String username, String password) {
        super(driver, url, username, password);
    }

    @Override
    protected Object five() throws SQLException {
        return stmt.executeUpdate();
    }
}
