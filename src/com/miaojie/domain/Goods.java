package com.miaojie.domain;
/*
 *  作者：吴淼杰
 *  注释：老天保佑，佛祖保佑，别出bug！
 *  CREATE TABLE `tb_goods` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `name` varchar(100) NOT NULL,
      `pubdate` date DEFAULT NULL,
      `picture` varchar(255) NOT NULL,
      `price` devimal(8,2) NOT NULL,
      `star` tinyint(4) NOT NULL DEFAULT '0',
      `intro` text,
      `typeid` int(11) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `fk_typeid` (`typeid`),
      CONSTRAINT `fk_typeid` FOREIGN KEY (`typeid`) REFERENCES `tb_goods_type` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8

 */

import java.math.BigDecimal;
import java.util.Date;

//Goods货品实体类
public class Goods {
    private Integer id;//id
    private String name;//货品名字
    private Date pubdate;//日期，发布时间
    private String picture;//货品图片
    private BigDecimal price;//货品价格
    private Integer star;//商品等级 0-5
    private String intro;//介绍
    private Integer typeid;

    public Goods(Integer id, String name, Date pubdate, String picture, BigDecimal price, Integer star, String intro, Integer typeid) {
        this.id = id;
        this.name = name;
        this.pubdate = pubdate;
        this.picture = picture;
        this.price = price;
        this.star = star;
        this.intro = intro;
        this.typeid = typeid;
    }

    public Goods() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pubdate=" + pubdate +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", star=" + star +
                ", intro='" + intro + '\'' +
                ", typeid=" + typeid +
                '}';
    }
}
