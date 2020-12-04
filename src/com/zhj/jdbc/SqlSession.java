package com.zhj.jdbc;


import com.zhj.jdbc.annocations.Delete;
import com.zhj.jdbc.annocations.Insert;
import com.zhj.jdbc.annocations.Select;
import com.zhj.jdbc.annocations.Update;
import com.zhj.jdbc.pool.ConnectionPool;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/8/26.
 * 实现jdbc交互
 * 与JdbcUtil作用相同
 * 当前对象针对于一种新的sql语法
 * insert into t_car values(null,#{cname},#{color},#{price})
 */
public class SqlSession {

    private JdbcUtil util  ;
    public SqlSession(String driver, String url, String username, String password, ConnectionPool pool){
        util = new JdbcUtil(driver,url,username,password);
        util.setPool(pool);
    }


    public int insert(String sql , Object param){
        /*
            传进来的sql是 "insert into t_car values(null,#{cname},#{color},#{price})":;
            调用util.insert()需要传递的sql："insert into t_car values(null,?,?,?)"

            传进来的参数是一个car对象
            调用util.insert()需要传递是一个数组，数组中包括{car.cname,car.color,car.price}
         */

        //sql 与 参数的处理
        SqlAndParam sp = SqlHandler.execute(sql,param) ;
        System.out.println(sp.sql +"sql----------");
        System.out.println(sp.params + "params------------");
        return util.insert(sp.sql,sp.params.toArray());
    }
    //insert into t_car values(1001,'bmw','blue',300000);
    public int insert(String sql){
        return util.insert(sql);
    }

    public int update(String sql , Object param){
        SqlAndParam sp = SqlHandler.execute(sql,param) ;
        return util.update(sp.sql,sp.params.toArray());
    }
    public int update(String sql){
        return util.update(sql);
    }

    public int delete(String sql , Object param){
        SqlAndParam sp = SqlHandler.execute(sql,param) ;
        return util.delete(sp.sql,sp.params.toArray());
    }
    public int delete(String sql ){
        return util.delete(sql);
    }

    public List<Map<String,Object>> selectListMap(String sql , Object param){
        SqlAndParam sp = SqlHandler.execute(sql,param) ;
        return util.selectListMap(sp.sql,sp.params.toArray());
    }

    public Map<String,Object> selectMap(String sql , Object param){
        SqlAndParam sp = SqlHandler.execute(sql,param) ;
        return util.selectMap(sp.sql,sp.params.toArray());
    }

    public <T> List<T> selectList(String sql , Object param , Class<T> type){
        SqlAndParam sp = SqlHandler.execute(sql,param) ;
        return util.selectList(sp.sql,type,sp.params.toArray());
    }

    public <T> T selectOne(String sql , Object param , Class<T> type){
        SqlAndParam sp = SqlHandler.execute(sql,param) ;
        return util.selectOne(sp.sql,type,sp.params.toArray());
    }

    public List<Map<String,Object>> selectListMap(String sql ){
        return util.selectListMap(sql);
    }

    public Map<String,Object> selectMap(String sql ){
        return util.selectMap(sql);
    }

    public <T> List<T> selectList(String sql ,Class<T> type){
        return util.selectList(sql,type);
    }

    public <T> T selectOne(String sql, Class<T> type){
        return util.selectOne(sql,type);
    }



    /**
     * 根据指定的dao接口规则，创建其对应的实现类
     * @param daoInterface
     * @param <T>
     * @return
     */
    //getMapper
    public <T> T createDaoImpl(Class<T> daoInterface){
        //匿名内部类
        return (T) Proxy.newProxyInstance(
                daoInterface.getClassLoader(), //在dao接口的同包下创建代理类
                new Class[]{daoInterface}, //创建代理类也实现指定的接口 class Proxy implements CarDao3
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //当业务成仿佛调用dao接口的方法时，其实调用的就是当前invoke方法
                        //所以从业务而言，我们希望调用接口中的方法时执行什么操作，就在当前的invoke方法中写响应的代码
                        /*
                            proxy 当前产生的代理对象，只需要知道，不要(反射)应用。会产生栈内存溢出
                            method 当前调用的方法  dao.save() -->method 表示 save
                            args 调用方法时传递的参数 dao.save(car,book) --> args{car,book}
                         */
                        Annotation a = method.getAnnotations()[0] ; //获得当前方法上的注解
                        Method vm = a.getClass().getMethod("value");//获得注解中的value属性(方法)
                        String sql = (String) vm.invoke(a);// @Insert.value(); 获得sql

                        Object param = args==null?null:args[0] ; //获得本次方法调用时传递的参数。人为要求只能传递0或1个参数。

                        //此时获得了本次业务操作对应的sql和参数param
                        Object result = null ;//返回值
                        if(a.annotationType() == Insert.class){
                            if(param == null){
                                result = insert(sql) ;
                            }else{
                                result = insert(sql,param) ;
                            }
                        }else if(a.annotationType() == Update.class){
                            if(param == null){
                                result = update(sql) ;
                            }else{
                                result = update(sql,param) ;
                            }
                        }else if(a.annotationType() == Delete.class){
                            if(param == null){
                                result = delete(sql) ;
                            }else{
                                result = delete(sql,param) ;
                            }
                        }else if(a.annotationType() == Select.class){
                            Class rt = method.getReturnType() ;
                            if(rt == List.class){
                                //查询结果组成的类型应该是方法的返回类型中的泛型 List<Car> -> Car
                                //如何使用反射获得泛型
                                Type type = method.getGenericReturnType() ; //获得完整的返回类型(包括泛型)
                                ParameterizedType pt = (ParameterizedType) type; //type是所有类型的爹，需要强转成可以获得泛型的类型
                                Class fx = (Class) pt.getActualTypeArguments()[0];//获得List集合中的那1个泛型参数
                                if(fx == Map.class){
                                    if(param == null){
                                        result = selectListMap(sql) ;
                                    }else{
                                        result = selectListMap(sql,param) ;
                                    }
                                }else{
                                    if(param == null){
                                        result = selectList(sql,fx);
                                    }else{
                                        result = selectList(sql,param,fx);
                                    }
                                }
                            }else{
                                if(rt == Map.class){
                                    if(param == null){
                                        result = selectMap(sql);
                                    }else{
                                        result = selectMap(sql,param);
                                    }
                                }else{
                                    if(param == null){
                                        result = selectOne(sql,rt);
                                    }else{
                                        result = selectOne(sql,param,rt);
                                    }
                                }
                            }
                        }

                        return result;
                    }
                }
        );
    }


}
