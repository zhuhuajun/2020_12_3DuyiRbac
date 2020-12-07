package com.zhj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhj.connect.Page;
import com.zhj.domain.Fn;
import com.zhj.mymvc.ModelAndView;
import com.zhj.mymvc.ResponseBody;
import com.zhj.service.FnService;

import javax.xml.ws.Service;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：
 * @date ：Created in 2020/12/5 上午 08:47
 */
public class FnController {
    private static FnService fnService;

  static {
    fnService = new FnService();
  }

  @ResponseBody
  public List<Fn> list() {
     List<Fn> list = fnService.list();
    System.out.println(list);
     return list ;
  }
}
