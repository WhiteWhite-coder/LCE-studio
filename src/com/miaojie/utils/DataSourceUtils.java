package com.miaojie.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  作者：吴淼杰
 *  注释：老天保佑，佛祖保佑，别出bug！
 */

//定义JDBCUtils/DataSourceUtils工具类
public class DataSourceUtils {
    //1.定义成员变量 DruidDataSource
    private static DruidDataSource dataSource;
    static {
        try {
            //1.加载配置文件,实例化
            Properties properties=new Properties();
            //类加载器加载流
            //class.getClassLoader().getResourceAsStream
            //class.getResourceAsStream
            //一个去当前类所在包下找，一个去str找
            InputStream is=DataSourceUtils.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(is);
            is.close();
            //2.获取dataSource
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化连接池失败");
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}