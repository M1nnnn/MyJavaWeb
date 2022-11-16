package com.mnnull.Utils;/**
 * @author mnnull
 * @date 2022/11/5-21:05
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: sqlsessionFactoryUtills
 * @Description:代码优化，优化SqlSessionFactory的重复创建
 * sqlsession两行代码没有优化的原因：sqlsession相当于用户和数据库的连接，多个用户不可以共用连接
 * @Author : MNNull
 * @Date : 2022/11/5  21:05
 */

public class sqlsessionFactoryUtills {
    //为了这个sqlSessionFactory这个局部变量，可以在两个不同的代码块内使用，提升他的作用域，成员变量
    private static SqlSessionFactory sqlSessionFactory;

    static {
        //静态代码块会随着类的加载而加载，且只能加载一次
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
