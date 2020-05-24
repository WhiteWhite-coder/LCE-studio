package com.miaojie.web.servlet;

import cn.dsna.util.images.ValidateCode;
import com.miaojie.domain.Address;
import com.miaojie.domain.User;
import com.miaojie.service.AddressService;
import com.miaojie.service.UserService;
import com.miaojie.service.impl.AddressServiceImpl;
import com.miaojie.service.impl.UserServiceImpl;
import com.miaojie.utils.Base64Utils;
import com.miaojie.utils.RandomUtils;
import com.miaojie.utils.StringUtils;
import com.sun.deploy.net.proxy.WDefaultBrowserProxyConfig;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "UserServlet",value = "/userservlet")
public class UserServlet extends BaseServlet {
    public String register(HttpServletRequest request, HttpServletResponse response) {
        //1.获取数据
        try {
            User user = new User();
            BeanUtils.populate(user, request.getParameterMap());
            //重复密码
            String repassword = request.getParameter("repassword");

            //2.校验数据
            //用户名
            if (StringUtils.isEmpty(user.getUsername())) {
                request.setAttribute("registerMsg", "用户名不能为空");
                return "/register.jsp";//转发
            }
            //密码
            if (StringUtils.isEmpty(user.getPassword())) {
                request.setAttribute("registerMsg", "密码不能为空");
                return "/register.jsp";//转发
            }
            //重复密码
            if (!user.getPassword().equals(repassword)) {
                request.setAttribute("registerMsg", "两次密码不一致");
                return "/register.jsp";//转发
            }
            //邮箱
            if (StringUtils.isEmpty(user.getEmail())) {
                request.setAttribute("registerMsg", "邮箱不能为空");
                return "/register.jsp";//转发
            }

            //3.调用业务
            UserService userService = new UserServiceImpl();
            //flag role code
            user.setFlag(0);//没有激活
            user.setRole(1);//角色
            user.setCode(RandomUtils.createActive());//激活码
            System.out.println(user.toString());
            userService.register(user);
            System.out.println("用户注册");
            //4.注册成功
            return "redirect:/registerSuccess.jsp";//重定向

        } catch (Exception e) {
            request.setAttribute("registerMsg", "注册失败");
            e.printStackTrace();
        }

        return "/register.jsp";//失败还在当前页面
    }

    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");//获取username
        if (username == null || username.trim().length() == 0) {//用户什么没给
            return null;
        }
        UserService userService = new UserServiceImpl();
        User user = userService.checkUserName(username);
        if (user != null) {
            response.getWriter().write("1");//非空即重复
        } else {
            response.getWriter().write("0");//空即不重复
        }
        return null;
    }

    public String login(HttpServletRequest request, HttpServletResponse response) {
        //1.获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String auto = request.getParameter("auto");

        String valcode = request.getParameter("valcode");
        String servercode = (String) request.getSession().getAttribute("vcode");
        if(StringUtils.isEmpty(valcode)){
            request.setAttribute("msg", "验证码不能为空");
            return "/login.jsp";
        }
        if(!valcode.equalsIgnoreCase(servercode)){
            request.setAttribute("msg", "验证码输入有误");
            return "/login.jsp";
        }

        if(StringUtils.isEmpty(username)){
            request.setAttribute("msg", "用户名不能为空");
            return "/login.jsp";
        }
        if(StringUtils.isEmpty(password)){
            request.setAttribute("msg", "密码不能为空");
            return "/login.jsp";
        }
        //2.验证用户名和密码是否正确
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        //3.1不正确情况
        if(user == null){
            //没有用户
            request.setAttribute("msg", "用户名或密码有误");
            return "/login.jsp";
        }else{
            //有用户
            //有没有激活flag=1
            if(user.getFlag()!=1){
                request.setAttribute("msg", "用户尚未激活或禁用");
                return "/login.jsp";
            }
            //有没有权限role=1
            if(user.getRole()!=1){
                //权限role=0
                if(user.getRole()==0){
                    request.getSession().setAttribute("admin", username);
                    return "redirect:/admin/admin.jsp";
                }
                request.setAttribute("msg", "用户没有权限");
                return "/login.jsp";
            }
            //登录成功
            request.getSession().setAttribute("user", user);

            //auto
            if(auto != null){
                //创建cookie
                String userinfo=username+"#"+password;
                Cookie cookie=new Cookie("userinfo", Base64Utils.encode(userinfo));
                //设置有效期
                cookie.setMaxAge(60*60*24*14);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);

            }

            //重定向到首页
            return "redirect:/index.jsp";
        }

    }

    public String code(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //1.创建验证码,validate封装的方法
        ValidateCode validateCode = new ValidateCode(100, 40, 4, 20);
        //2.得到验证码
        String code = validateCode.getCode();
        //3.存入session
        request.getSession().setAttribute("vcode", code);
        //4.返回这个流
        validateCode.write(response.getOutputStream());

        return null;
    }

    public String checkCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.1.接收浏览器传来的验证码
        String clientcode= request.getParameter("code");
        //2.接收code()的验证码
        String servercode = (String) request.getSession().getAttribute("vcode");
        //3.判断相同
        //3.1.判断验证码空
        if(StringUtils.isEmpty(clientcode)){
            return null;
        }
        if(clientcode.equalsIgnoreCase(servercode)){
            response.getWriter().write("0");
        }else{
            response.getWriter().write("1");
        }
        return null;
    }

    public String logOut(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //1.删除session中的用户数据
        request.getSession().removeAttribute("user");
        //2.session失效
        request.getSession().invalidate();
        //3.删除cookie
        Cookie cookie=new Cookie("userinfo", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/index.jsp";
    }

    public String getAddress(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //1.判断用户有没有登录
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/login.jsp";//没有登录则去登录界面
        }
        //2.获取收获地址（根据用户uid）
        AddressService addressService = new AddressServiceImpl();
        List<Address> addList = addressService.findByUid(user.getId());
        request.setAttribute("addList", addList);

        return "/self_info.jsp";
    }

    public String addAddress(HttpServletRequest request,HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("utf-8");
        //1.判断用户有没有登录
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/login.jsp";//没有登录则去登录界面
        }
        //2.得到参数并判空
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        if(StringUtils.isEmpty(name)){
            request.setAttribute("msg","收件人名字不能为空");
            return getAddress(request,response);//为空再去取一次
        }
        if(StringUtils.isEmpty(phone)){
            request.setAttribute("msg","收件人电话不能为空");
            return getAddress(request,response);
        }
        if(StringUtils.isEmpty(detail)){
            request.setAttribute("msg","收件人地址不能为空");
            return getAddress(request,response);
        }
        //3.添加地址
        Address address = new Address(null, detail, name, phone, user.getId(),0);
        AddressService addressService = new AddressServiceImpl();
        addressService.add(address);

        return getAddress(request,response);
    }

    public String defaultAddress(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //levle为1即默认，level为0即普通
        //1.判断用户有没有登录
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/login.jsp";//没有登录则去登录界面
        }
        //2.得到参数地址id
        String id = request.getParameter("id");
        //3.修改地址级别
        AddressService addressService = new AddressServiceImpl();
        addressService.updateDefault(Integer.parseInt(id),user.getId());

        return null;
    }
}
