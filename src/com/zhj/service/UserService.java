package com.zhj.service;


import com.zhj.dao.UserDao;
import com.zhj.domain.User;

import java.util.List;

/**
 * @author ：
 * @date ：Created in 2020/11/20 下午 03:16
 */

public class UserService {
    private UserDao userDao = new UserDao();
    public User selectByUserAndPassword (String user ,String password) {
        return userDao.selectByUserAndPas(user,password);
    }

    public List<User> selectLimitUsers (Integer page,Integer size) {
        return userDao.selectLimitUsers(page,size);
    }

    public Integer selectUserSize () {
        return userDao.selectUserSize();
    }


    public User selectById (Integer uid) {
        return userDao.selectUserById(uid);
    }

    public int saveUser (User user) {
        return userDao.saveUser(user);
    }

    /***
    *
    * 按照Id删除用户
    * @param uid
    * @Return int
    */
    public int deleteUserById(String uid) {
        return userDao.deleteUserById(uid);
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

}
