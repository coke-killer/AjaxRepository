package com.example.servlet;

import com.example.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/userServlet", name = "userServlet")
public class UserServlet extends HttpServlet {
    Map<Integer, List<User>> map = new HashMap<>();

    public UserServlet() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "aaa", "bbb"));
        list.add(new User(2, "aaa", "bbb"));
        list.add(new User(3, "aaa", "bbb"));
        map.put(1, list);
        list = new ArrayList<>();
        list.add(new User(4, "aaa", "bbb"));
        list.add(new User(5, "aaa", "bbb"));
        list.add(new User(6, "aaa", "bbb"));
        map.put(2, list);
    }

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("userServlet start");
        //由于返回的是xml ,需要为response设置contentType
        resp.setContentType("text/xml;charset=utf-8");
        //从页面获取mapKey的值
        int mapKey = Integer.parseInt(req.getParameter("mapKey"));
        //根据key获取对应的value
        List<User> list = map.get(mapKey);
        //创建一个StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("<users>");
//        //便利List
//        for (User user : list) {
//            stringBuffer.append("<user>");
//            stringBuffer.append("<id>").append(user.getId()).append("</id>");
//            stringBuffer.append("<name>").append(user.getName()).append("</name>");
//            stringBuffer.append("<password>").append(user.getPassword()).append("</password>");
//            stringBuffer.append("</user>");
//        }
//        stringBuffer.append("<users>");
        stringBuffer.append(new ObjectMapper().writeValueAsString(list));
        System.out.println(stringBuffer.toString());
        resp.getWriter().write(stringBuffer.toString());
    }
}
