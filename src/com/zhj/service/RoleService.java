package com.zhj.service;

import com.zhj.dao.RoleDao;
import com.zhj.domain.Role;

import java.util.List;

/**
 * @author ：
 * @date ：Created in 2020/12/1 下午 01:50
 */

public class RoleService {
    private RoleDao roleDao = new RoleDao();


    public List<Role> getRoles (Integer page) {

        return roleDao.selectRoles(page);
    }

    public Long getCount () {
        return roleDao.selectAll();
    }

}
