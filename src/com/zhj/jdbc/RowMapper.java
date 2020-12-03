package com.zhj.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2020/8/25.
 * 指定查询的每条记录组成对应的对象的策略规则
 */
public interface RowMapper<T> {

    /*
        将结果集对象中的一条记录 组成 对应的domain对象
        切记不要循环结果集
     */
    public T mapping(Map<String,Object> row)throws Exception;

}
