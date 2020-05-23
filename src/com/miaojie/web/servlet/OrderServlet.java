package com.miaojie.web.servlet;
/*
 *  @author 吴淼杰
 *  老天保佑，佛祖保佑，别出bug！
 *
 */

import com.miaojie.domain.Address;
import com.miaojie.domain.Cart;
import com.miaojie.domain.User;
import com.miaojie.service.AddressService;
import com.miaojie.service.CartService;
import com.miaojie.service.impl.AddressServiceImpl;
import com.miaojie.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/orderservlet")
public class OrderServlet extends BaseServlet {
    public String getOrderView(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //查询订单然后转发给订单预览界面
        //1.判断用户有没有登录
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/login.jsp";//没有登录则去登录界面
        }
        //2.查询购买的商品
        CartService cartService=new CartServiceImpl();
        List<Cart> carts = cartService.findByUid(user.getId());
        request.setAttribute("carts", carts);
        //3.获取收获地址（根据用户uid）
        AddressService addressService = new AddressServiceImpl();
        List<Address> addList = addressService.findByUid(user.getId());
        request.setAttribute("addList", addList);
        return "/order.jsp";
    }
}
