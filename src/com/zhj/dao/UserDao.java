package com.zhj.dao;

import com.mysql.cj.Session;
import com.zhj.domain.User;
import com.zhj.jdbc.JdbcFactory;
import com.zhj.jdbc.SqlSession;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：
 * @date ：Created in 2020/11/20 下午 03:16
 */

public class UserDao implements Serializable {
    private static SqlSession session;
    static{
        session = new JdbcFactory("mysql.properties").getSession();
    }


    public User selectByUserAndPas(String uname, String upass) {
        HashMap<String, String> umap = new HashMap<>();
        umap.put("uname",uname);
        umap.put("upass",upass);
        String sql = "select * from t_user where uname = #{uname} and upass = #{upass}";
        User user = session.selectOne(sql,umap,User.class);

        return user;
    }

    public List<User> selectLimitUsers (Integer page,Integer size) {

        HashMap<String, Integer> umap = new HashMap<>();
        umap.put("page",page);
        umap.put("size",size);

        String sql = "select * from t_user where del = 1 limit #{page},#{size}";

        List<User> userList = session.selectList(sql,umap,User.class);
        return userList;
    }

    public Integer selectUserSize () {
        String sql = "select count(*) from t_user where del = 1";

        return session.selectOne(sql,Integer.class);
    }

    public User selectUserById (Integer uid) {
        String sql = "select * from t_user where uid = #{uid}";
        return session.selectOne(sql,uid,User.class);
    }

    public int saveUser(User user) {
        String sql = "insert into t_user values(null,#{UName},#{UPass},#{URueName},#{age},#{sex},1,now(),#{yul1},#{yul2}) ";
        return session.insert(sql,user);
    }

    public int deleteUserById(String uid) {
        String sql = "update t_user set del = 0 where uid = #{uid}";
        return session.update(sql,uid);
    }

    public int updateUser (User user) {
        String sql = "update t_user set uName = #{uName},uPass = #{uPass},uRueName = #{uRueName},age = #{age},sex = #{sex},createTime = now() ,yul1 = #{yul1} ,yul2 = #{yul2} where uid = #{uid}";
        System.out.println();
        return session.update(sql, user);
    }
}
