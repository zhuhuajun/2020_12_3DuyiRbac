package com.zhj.dao;

import com.zhj.domain.Role;
import com.zhj.jdbc.JdbcFactory;
import com.zhj.jdbc.SqlSession;

import java.util.List;

/**
 * @author ：
 * @date ：Created in 2020/12/1 下午 01:50
 */

public class RoleDao {
    private static SqlSession session;
    static {
        JdbcFactory factory = new JdbcFactory("mysql.properties");
        session = factory.getSession();

    }


    public List<Role> selectRoles (Integer page) {
        String sql = "select * from t_role where del = 1 limit #{page},10";
        return session.selectList(sql,page,Role.class);
    }

    public Long selectAll () {
        String sql = "select count(*) from t_role where del = 1";

        return session.selectOne(sql,Long.class);
    }

}
