package com.zhj.service;


import com.zhj.dao.FnDao;
import com.zhj.domain.Fn;
import com.zhj.jdbc.JdbcFactory;
import com.zhj.jdbc.SqlSession;

import java.util.List;

/**
 *
 *
 * @author     ：
 * @date       ：Created in 2020/12/5 上午 09:06
 */

public class FnService {
    private static FnDao fnDao;
    static {
        fnDao = new FnDao();
    }


    public List<Fn> list() {
       return fnDao.selectAll();
    }

}
