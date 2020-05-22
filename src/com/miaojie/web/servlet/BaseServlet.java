package com.miaojie.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//抽取通用Servlet
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断行为
        //1.获取方法名称
        String methodName = request.getParameter("method");
        //利用反射调用
        try {
            //2.获取方法对象
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            //3.让方法执行，接受返回值
            String url = (String) method.invoke(this,request,response);//this.method();
            //4.判断返回值是否为空，若不为空，统一处理请求转发
            //&&url.trim().length!=0
            if(url!=null&&url.trim().length()!=0){
                //有数据
                if(url.startsWith("redirect:")){
                    //前缀含有redirect:则重定向
                    response.sendRedirect(request.getContextPath()+url.split(":")[1]);//当前动态路径+url.split(":")[1]截取路径
                }else {
                    //前缀不含有redirect:则转发
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
