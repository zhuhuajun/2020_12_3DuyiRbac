package com.zhj.controller;


import com.zhj.connect.Page;
import com.zhj.domain.User;
import com.zhj.mymvc.ModelAndView;
import com.zhj.mymvc.RequestParam;
import com.zhj.mymvc.ResponseBody;
import com.zhj.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.POIDocument;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author ：
 * @date ：Created in 2020/11/20 下午 03:11
 */

public class UserController {
    private UserService service = new UserService();

    public String login(@RequestParam("user") String user, @RequestParam("password") String password, HttpServletRequest request) {
        service = new UserService();
        User account = service.selectByUserAndPassword(user,password);
        if (account == null) {
            request.setAttribute("msg","登录失败,账号名或密码错误");
            return "index.jsp";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user",account);
            return "main.jsp";
        }
    }

    /***
    *
    *        退出
    * @param
    * @Return java.lang.String
    */
    public String exit(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/";
    }

    /***
    *
    *        编辑
    * @Return com.zhj.mymvc.ModelAndView
    */
    public ModelAndView edit (@RequestParam("uid")String uid) {
        ModelAndView view = new ModelAndView();

        User user = service.selectById(Integer.parseInt(uid));
        view.addAttribute("user",user);
        view.setViewName("userEdit.jsp");
        return view;
    }


    public void save (User user,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String sex = new String(request.getParameter("sex").getBytes("iso-8859-1"),"utf-8");
        user.setSex(sex);
        System.out.println(sex);
        service.saveUser(user);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("保存成功");
    }

    /***
    *
    *        分页
    * @param page
    * @Return com.zhj.mymvc.ModelAndView
    */
    public ModelAndView list (@RequestParam("page") Integer page) {

        ModelAndView mv = new ModelAndView();
        Integer count = service.selectUserSize();
        Page Upage = new Page();
        Upage.setSize(10);
        Upage.setPage(page);
        int max = (int) Math.ceil((double)count / Upage.getSize());
        Upage.setMax(max);
        Integer start = (page-1) * Upage.getSize();
        List<User> users = service.selectLimitUsers(start,Upage.getSize());
        Upage.setList(users);

        mv.addAttribute("page",Upage);
        mv.setViewName("user.jsp");

        return mv;
    }

    /***
    *
    * 删除用户
    * @param uid
    * @Return java.lang.String
    */
    public String delete (@RequestParam("uid") String uid) {
        System.out.println(uid+"=============");
        service.deleteUserById(uid);

        return "redirect:UserController.do?method=list&page=1";
    }

    public String update (User user,HttpServletRequest request) throws UnsupportedEncodingException {
        user.setSex(new String(request.getParameter("sex").getBytes("iso-8859-1"),"utf-8"));
        user.setURueName(new String(request.getParameter("uRueName").getBytes("iso-8859-1"),"utf-8"));
        service.updateUser(user);
        return "redirect:UserController.do?method=list&page=1";
    }

    @ResponseBody
    public String batch (HttpServletRequest request) throws FileUploadException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);
        // 获得fileItems
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            InputStream is = fileItem.getInputStream();
            Workbook work = WorkbookFactory.create(is);
            Sheet sheetAt = work.getSheetAt(0);

            for (int i = 1; i < sheetAt.getLastRowNum() ; i++) {
                Row row = sheetAt.getRow(i);

                Cell c1 = row.getCell(0);
                Cell c2 = row.getCell(1);
                Cell c3 = row.getCell(2);
                Cell c4 = row.getCell(3);
                Cell c5 = row.getCell(4);

                String uname = c1.toString();
                String upass = c2.toString().replace(".0","");
                Integer age = Integer.parseInt(c3.toString().replace(".0",""));
                String sex = c4.toString();
                String uRname = c5.toString();

                User user = new User();
                user.setUName(uname);
                user.setUPass(upass);
                user.setAge(age);
                user.setSex(sex);
                user.setURueName(uRname);

                service.saveUser(user);

            }


        }
        return "保存成功";
    }

}
