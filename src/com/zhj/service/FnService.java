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

        return assemblyData(fns,-1);
    }

    /***
    *
    *        拼装数据，按照子父类型
    * @param date 数据
    * @param pid 父级菜单
    * @Return java.util.List<com.zhj.domain.Fn>
    */

    private List<Fn> assemblyData (List<Fn> date,Integer pid) {
        List<Fn> fns = new ArrayList<>();
        // 遍历所有的数据
        for (Fn fn : date) {
            // 找到父级菜单
            if (fn.getPid().equals(pid)) {
        // 第一个进来，是id 1 pid为 -1 的,表示这是一个父级的菜单
        // 然后递归，把数据传到里面，寻找子类，什么是他的子类，pid = 父级菜单的 fid // 然后第一次传的就是 1，

        // 第一轮为pid为-1 不符合，
        // 第二轮pid为1符合，然后在次进入递归寻找pid为2的，然后数据库里面没有pid为2的所以返回的是一个空的集合
        // 然后执行fn.setCarte(list)和fns.add(fn)，此时fns集合里面正式有了一个数据，然后这是一个for循环
        // 第三轮和第二轮类似
        // 第四轮和第三轮类似
        // 第五轮往后都条件都不符合所以最后的结果为fns里面有3条数据,
        // 然后到最大的一层,最原始的pid为1时234都不符合,到5符合了,在走一遍上面的流程
        List<Fn> list = assemblyData(date, fn.getFid());
                fn.setCarte(list);
                fns.add(fn);
            }
        }
        return fns;
    }



}
