package com.miaojie.domain;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class PageBean<T> {
    private int pageNum;//页码标签
    private int pageSize;//一页的商品数量
    private long totalSize;//总商品数量
    private int pageCount;//页码个数
    private List<T> data;
    private int startPage;
    private int endPage;

    public PageBean(int pageNum, int pageSize, long totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.data = data;

        //计算总页数
        this.pageCount= (int) (totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1);

        //计算导航的开始页和结束页
        /**
         * 分情况
         * 1-5 开始页1，结束页10
         * 6-95 开始页当前页-4，结束页当前页+5
         * 95-100 开始页总页数-9，结束页总页数
         */
        //1.正常情况 开始页当前页-4，结束页当前页+5
        this.startPage = pageNum - 4;
        this.endPage = pageNum + 5;
        //2.当前页码小于5 开始页1，结束页10
        if(pageNum < 5){
            this.startPage = 1;
            this.endPage = 10;
        }
        //3.当前页码大于总页码-5 开始页总页数-9，结束页总页数
        if(pageNum > pageCount - 5){
            this.startPage = pageCount - 9;
            this.endPage = pageCount;
        }
        //4.总页数小于10
        if(pageCount < 10){
            this.startPage = 1;
            this.endPage = pageCount;
        }

    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
