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
    public String getGoodsListByTypeId(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String typeId = request.getParameter("typeId");
        String _pageNum = request.getParameter("pageNum");
        String _pageSize = request.getParameter("pageSize");
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
        //定义条件
        String condition="";
        if(typeId!=null && typeId.trim().length()!=0) {
            condition = "typeid=" + typeId;
        }

        try {
            PageBean<Goods> pageBean=goodsService.findPageByWhere(pageNum,pageSize,condition);  // typeId=1;
            request.setAttribute("pageBean", pageBean);

            return "/goodsList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index.jsp";
        }
    }
}
