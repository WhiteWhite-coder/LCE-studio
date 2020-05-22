package com.miaojie.web.servlet;
/*
 *  作者：吴淼杰
 *  注释：老天保佑，佛祖保佑，别出bug！
 *
 */

import com.miaojie.domain.Goods;
import com.miaojie.domain.PageBean;
import com.miaojie.service.GoodsService;
import com.miaojie.service.impl.GoodsServiceImpl;
import com.miaojie.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GoodsServlet" ,value = "/goodsservlet")
public class GoodsServlet extends BaseServlet {
    //分页
    public String getGoodsListByTypeId(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //1.获得参数
        String typeId = request.getParameter("typeId");
        String _pageNum = request.getParameter("pageNum");
        String _pageSize = request.getParameter("pageSize");
        //2.判空前提
        int pageNum = 1;
        int pageSize = 8;
        if(!StringUtils.isEmpty(_pageNum)){
            pageNum = Integer.parseInt(_pageNum);
            if(pageNum < 1){
                pageNum = 1;
            }
        }
        if(!StringUtils.isEmpty(_pageSize)){
            pageSize = Integer.parseInt(_pageSize);
            if(pageSize < 1){
                pageSize = 8;
            }
        }

        GoodsService goodsService = new GoodsServiceImpl();
        //3.定义条件
        String condition="";
        if(typeId != null && typeId.trim().length() != 0) {
            condition = "typeid=" + typeId;
        }
        //4.发送
        try {
            PageBean<Goods> pageBean = goodsService.findPageByWhere(pageNum,pageSize,condition);  // typeId=1;
            request.setAttribute("pageBean", pageBean);
            request.setAttribute("typeId", typeId);
            return "/goodsList.jsp";//转发
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index.jsp";//重定向
        }
    }
    //商品详情
    public String getGoodsById(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String id = request.getParameter("id");
        GoodsService goodsService = new GoodsServiceImpl();
        if(StringUtils.isEmpty(id)){
            return "redirect:/index.jsp";
        }
        Goods goods = goodsService.findById(Integer.parseInt(id));
        request.setAttribute("goods", goods);

        return "/goodsDetail.jsp";
    }
}
