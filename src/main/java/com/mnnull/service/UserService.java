package com.mnnull.service;/**
 * @author mnnull
 * @date 2022/11/16-10:45
 */

import com.mnnull.Utils.sqlsessionFactoryUtills;
import com.mnnull.mapper.UserMapper;
import com.mnnull.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @ClassName: UserService
 * @Description:
 * @Author : MNNull
 * @Date : 2022/11/16  10:45
 */

public class UserService {
    //将他放入成员的位置，防止每个方法都调用一次 ，占用资源
    SqlSessionFactory sqlSessionFactory = sqlsessionFactoryUtills.getSqlSessionFactory();

    /**
     * 功能描述: <br>
     * 〈〉登录方法
     *
     * @Param: [username, password]
     * @Return: [java.lang.String, java.lang.String]
     * @Author: itestar
     * @Date: 2022/11/16 10:59
     */

    public User login(String username, String password) {
        //获取sql session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.select(username, password);

        sqlSession.close();

        return user;
    }
    /**
     * 功能描述: <br>
     * 〈〉注册方法
     * @Param: [user]
     * @Return: [com.mnnull.pojo.User]
     * @Author: itestar
     * @Date: 2022/11/16 15:49
     */

    public boolean register(User user) {
        //获取sql session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //判断用户名是否存在
        User user1 = mapper.selectByUsername(user.getUsername());
        if (user1==null){
            //用户名不存在，注册
            mapper.add(user);
            sqlSession.commit();
        }

        sqlSession.close();

        return user1==null;
    }
}
