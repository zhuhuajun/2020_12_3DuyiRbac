package com.zhj.dao;
import com.zhj.domain.Fn;
import com.zhj.jdbc.JdbcFactory;
import com.zhj.jdbc.SqlSession;

import java.util.List;

/**
 *
 *
 * @author     ：
 * @date       ：Created in 2020/12/5 上午 09:11
 */

public class FnDao {
    public static SqlSession session;

    static {
        session = new JdbcFactory("mysql.properties").getSession();
    }

    public List<Fn> selectAll () {
        String sql = "select * from t_fn where del = 1";
        List<Fn> fns = session.selectList(sql, Fn.class);
        return fns;
    }



}
