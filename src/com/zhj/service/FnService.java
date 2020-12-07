package com.zhj.service;


import com.zhj.dao.FnDao;
import com.zhj.domain.Fn;
import com.zhj.jdbc.JdbcFactory;
import com.zhj.jdbc.SqlSession;

import java.util.ArrayList;
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
        List<Fn> fns = fnDao.selectAll();
        return fns;
    }

    public List<Fn> AssemblyData (List<Fn> date,String pid) {
        List<Fn> fns = new ArrayList<>();

        for (Fn fn : date) {
            // 代表这是一个
            if (fn.getPid().equals(pid)) {


            }
        }
        return null;
    }



}
