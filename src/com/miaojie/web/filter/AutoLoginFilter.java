package com.miaojie.web.filter;

import com.miaojie.domain.User;
import com.miaojie.service.UserService;
import com.miaojie.service.impl.UserServiceImpl;
import com.miaojie.utils.Base64Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */

@WebFilter(filterName = "AutoLoginFilter",value = "/index.jsp")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.判断当前是否已经登录
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            chain.doFilter(req, resp);//2.登录了直接放行
            return;
        }
        //3.没有登录
        Cookie[] cookies = request.getCookies();//获得cookie
        if(cookies != null){
            for (Cookie cookie:cookies) {//有cookie遍历查询
                if(cookie.getName().equals("userinfo")){
                    String value = cookie.getValue();//获得value值
                    String userinfo = Base64Utils.decode(value);//解密
                    String[] userinfos = userinfo.split("#");//#分割
                    UserService userService = new UserServiceImpl();//创建业务
                    User user2 = userService.login(userinfos[0],userinfos[1]);//登录
                    if(user2 != null){
                        if(user2.getFlag() == 1){
                            request.getSession().setAttribute("user",user2);
                            chain.doFilter(req, resp);//登录成功放行
                            return;
                        }
                    }else{//不对则删除cookie
                        Cookie cookie1 = new Cookie("userinfo","");
                        cookie1.setMaxAge(0);
                        cookie1.setPath("/");
                        response.addCookie(cookie1);
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
