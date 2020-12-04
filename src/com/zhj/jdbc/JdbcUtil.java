package com.zhj.jdbc;



import com.zhj.jdbc.pool.ConnectionPool;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/8/24.
 * 对外可以使用jdbc工具
 */
@SuppressWarnings("all")
public class JdbcUtil {

    private String driver ;
    private String url ;
    private String username ;
    private String password ;

    public JdbcUtil(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * insert(sql,new Object[]{}) ;         insert(sql)
     * insert(sql,new Object[]{1}) ;        insert(sql,1)
     * insert(sql,new Object[]{1,2,3,4}) ;  insert(sql,1,2,3,4);
     * @param sql
     * @param param
     * @return
     */
    public int insert(String sql , Object... param ){
        if(sql.trim().substring(0,6).equalsIgnoreCase("insert")) {
            JdbcUpdateTemplate t = new JdbcUpdateTemplate(
                    driver,
                    url,
                    username,
                    password
            );
            t.setPool(pool);
            return (int) t.executeJdbc(sql, param);
        }else{
            throw new SqlFormatException("not a insert sql {"+sql+"}");
        }
    }

    public int update(String sql , Object... param ){
        if(sql.trim().substring(0,6).equalsIgnoreCase("update")) {
            JdbcUpdateTemplate t = new JdbcUpdateTemplate(
                    driver,
                    url,
                    username,
                    password
            );
            t.setPool(pool);
            return (int) t.executeJdbc(sql, param);
        }else{
            throw new SqlFormatException("not a update sql {"+sql+"}");
        }
    }

    public int delete(String sql , Object... param ){
        if(sql.trim().substring(0,6).equalsIgnoreCase("delete")) {
            JdbcUpdateTemplate t = new JdbcUpdateTemplate(
                    driver,
                    url,
                    username,
                    password
            );
            t.setPool(pool);
            return (int) t.executeJdbc(sql, param);
        }else{
            throw new SqlFormatException("not a delete sql {"+sql+"}");
        }
    }

    public List<Map<String,Object>> selectListMap(String sql,Object...param){
        if(sql.trim().substring(0,6).equalsIgnoreCase("select")){
            JdbcQueryTemplate t = new JdbcQueryTemplate(
                    driver,
                    url,
                    username,
                    password
            ) ;
            t.setPool(pool);
            List<Map<String,Object>> rs = (List<Map<String,Object>>) t.executeJdbc(sql,param);
            return rs ;
        }else{
            throw new SqlFormatException("not a select sql {"+sql+"}");
        }
    }

    public  Map<String,Object> selectMap(String sql , Object...param){
        List<Map<String,Object>> rows = selectListMap(sql,param);
        if(rows == null || rows.size() == 0){
            return null ;
        }else{
            return rows.get(0) ;
        }

    }

    public <T> List<T> selectList(String sql ,RowMapper<T> strategy, Object...param){
        if(sql.trim().substring(0,6).equalsIgnoreCase("select")){
            JdbcQueryTemplate t = new JdbcQueryTemplate(
                    driver,
                    url,
                    username,
                    password
            ) ;
            t.setPool(pool);
            List<Map<String,Object>> rs = (List<Map<String,Object>>) t.executeJdbc(sql,param);
            List<T> rows = new ArrayList<T>();
            try {
                //orm  将查询表数据装载到java实体类中
                for(Map<String,Object> r : rs){
                    T row = strategy.mapping(r) ;
                    rows.add(row) ;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return rows ;
        }else{
            throw new SqlFormatException("not a select sql {"+sql+"}");
        }
    }

    public <T> T selectOne(String sql ,RowMapper<T> strategy, Object...param){
        List<T> rows = selectList(sql,strategy,param);
        if(rows == null || rows.size() == 0){
            return null ;
        }else{
            return rows.get(0) ;
        }
    }


    /**
     * selectList("select * from t_car",Car.class);
     * selectList("select count(*) from t_car",Integer.class);
     * selectList("select cname from t_car",String.class);
     */
    public <T> List<T> selectList(String sql ,Class<T> type, Object...param){
        if(sql.trim().substring(0,6).equalsIgnoreCase("select")){
            JdbcQueryTemplate t = new JdbcQueryTemplate(
                    driver,
                    url,
                    username,
                    password
            ) ;
            t.setPool(pool);
            List<Map<String,Object>> rs = (List<Map<String,Object>>) t.executeJdbc(sql,param);
            List<T> rows = new ArrayList<T>();
            try {
                //orm  将查询表中数据装载到java实体类中

                for(Map<String,Object> r : rs){
                    Object row = null ;
                    if(type == int.class || type == Integer.class){
                        Collection cs = r.values();
                        for(Object c :cs){
                            //只做了一次
                            row = ((Number)c).intValue(); // Integer row = (Integer)c;
                        }
                    }else if(type == long.class|| type==Long.class){
                        Collection cs = r.values();
                        for(Object c :cs){
                            //只做了一次
                            row = ((Number)c).longValue(); // Integer row = (Integer)c;
                        }
                    }else if(type == double.class|| type==Double.class){
                        Collection cs = r.values();
                        for(Object c :cs){
                            //只做了一次
                            row = ((Number)c).doubleValue(); // Integer row = (Integer)c;
                        }
                    }else if(type == String.class){
                        Collection cs = r.values();
                        for(Object c :cs){
                            //只做了一次
                            row = (String)c;
                        }
                    }else{
                        //组成domain实体对象
                        row = type.newInstance() ;//Car car = new Car();
                        /*
                            int cno = r.get("cno");
                            car.setCno(cno);

                            String cname = r.get("cname");
                            car.setCname(cname);
                            ...
                         */
                        //通过反射获得实体中所有的属性名，map中找到与之同名的表数据，为其赋值。
                        // cno  == setCno
                        //从封装特性的角度而言，更推荐通过set方法，找到对应属性，通过set方法为属性赋值
                        Method[] ms = type.getMethods() ;
                        for(Method m:ms){
                            String mname = m.getName() ;
                            if(mname.startsWith("set")){
                                //m一个set方法  ->找到对应属性   setCno()->cno
                                mname = mname.substring(3);//去掉set
                                mname = mname.toLowerCase() ; // setCarNo()->carNo->carno
                                Object value = r.get(mname) ;
                                if(value == null){
                                    //当前对象属性没有对应的表数据
                                    continue ;//继续判断下一个属性
                                }else{
                                    //当前属性有对应的表数据，使用set方法赋值
                                    //使用反射调用方法，并赋值。
                                    Class p = m.getParameterTypes()[0];
                                    if(p == int.class || p == Integer.class){
                                        m.invoke(row,((Number)value).intValue()) ;//car.setCno(value); , car.setCname(value)
                                    }else if(p == long.class || p == Long.class){
                                        m.invoke(row,((Number)value).longValue()) ;//car.setCno(value); , car.setCname(value)
                                    }else if(p == double.class || p == Double.class){
                                        m.invoke(row,((Number)value).doubleValue()) ;//car.setCno(value); , car.setCname(value)
                                    }else if(p == String.class ){
                                        m.invoke(row,(String)value) ;//car.setCno(value); , car.setCname(value)
                                    }
                                }
                            }
                        }
                    }
                    rows.add((T) row);
                }
                return rows ;
            }catch(Exception e){
                e.printStackTrace();
            }
            return rows ;
        }else{
            throw new SqlFormatException("not a select sql {"+sql+"}");
        }
    }

    public <T> T selectOne(String sql ,Class<T> type, Object...param){
        List<T> rows = selectList(sql,type,param);
        if(rows == null || rows.size() == 0){
            return null ;
        }else{
            return rows.get(0) ;
        }
    }

    private ConnectionPool pool;
    public void setPool(ConnectionPool pool) {
        this.pool = pool;
    }

}
