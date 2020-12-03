package com.zhj.controller;

import com.alibaba.fastjson.JSON;
import com.zhj.connect.Page;
import com.zhj.domain.Role;
import com.zhj.mymvc.RequestParam;
import com.zhj.mymvc.ResponseBody;
import com.zhj.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：
 * @date ：Created in 2020/12/1 下午 01:48
 */

public class RoleController {
    private RoleService roleService = new RoleService();


    @ResponseBody
    public Map<String,Object> list(@RequestParam("page") String paramPage) {
        Integer rpage = Integer.parseInt(paramPage) - 1;
        List<Role> roles = roleService.getRoles(rpage);
        HashMap<String, Object> map = new HashMap<>();

        Long count = roleService.getCount();
        int max = (int) Math.ceil((double) count / count);
        Page page = new Page();
        page.setMax(max);
        page.setList(roles);
        map.put("list",roles);
        map.put("page",page);


        return map;
    }
}
