package com.kaikeba.controller;

import com.kaikeba.bean.Courier;
import com.kaikeba.bean.Message;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.service.CourierService;
import com.kaikeba.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 管理控制器
 *
 * @author Faker
 * @date 2020/09/30
 */
public class AdminController {


    /**
     * 寻找登录用户名
     *
     * @param request  请求
     * @param response 响应
     * @return {@link String}
     */
    @ResponseBody("/admin/find.do")
    public String findLoginUserName(HttpServletRequest request, HttpServletResponse response) {
        String username = WebUtil.getLoginUsername();
        Message message = new Message();
        if (username != null) {
            message.setResult(username);
            message.setStatus(0);
        } else {
            message.setResult(null);
            message.setStatus(-1);
        }
        return WebUtil.toJson(message);
    }

//    /**
//     * 登录
//     *
//     * @param request  请求
//     * @param response 响应
//     * @return {@link String}
//     */
//    @ResponseBody("/admin/login.do")
//    public String login(HttpServletRequest request, HttpServletResponse response) {
//        //1.    接参数
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        //2.    调用Service传参数，并获取结果
//        boolean result = AdminService.login(username, password);
//        //3.    根据结果，准备不同的返回数据
//        Message msg = null;
//        if (result) {
//            //{status:0,result:"登录成功"}
//            msg = new Message(0, "登录成功");
//            //登录时间 和 ip的更新
//            Date date = new Date();
//            String ip = request.getRemoteAddr();
//            AdminService.updateLoginTimeAndIp(username, ip);
//            request.getSession().setAttribute("adminUserName", username);
//        } else {
//            //{status:-1,result:"用户名或者密码错误"}
//            msg = new Message(-1, "登录失败");
//        }
//        //4.    将数据转换为JSON
//        return WebUtil.toJson(msg);
//    }

    @ResponseBody("/admin/login.do")
    public String courierLogin(HttpServletRequest request,HttpServletResponse response){
        //1.    接参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Message msg = null;
        Courier courier=null;
        if ((courier=CourierService.login(username,password))!=null){
            //{status:0,result:"登录成功"}
            msg = new Message(0,"登录成功");
            //登录时间 和 ip的更新
            CourierService.updateLoginTime(username);
            request.getSession().setAttribute("adminUserName",courier);
            WebUtil.setLoginUser(courier);
        }else {
            msg = new Message(-1, "登录失败");
        }
        return WebUtil.toJson(msg);
    }

    @ResponseBody("/admin/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Message message = new Message();
        message.setStatus(1);
        if (WebUtil.getLoginUser() != null) {
            WebUtil.removeUsername();
            message.setStatus(0);
        }
        return WebUtil.toJson(message);

    }


}
