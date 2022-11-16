package com.mnnull.service;/**
 * @author mnnull
 * @date 2022/11/7-20:58
 */

import com.mnnull.Utils.sqlsessionFactoryUtills;
import com.mnnull.mapper.BrandMapper;
import com.mnnull.pojo.Brand;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 *@ClassName: BrandService
 *@Description:
 *@Author : MNNull
 *@Date : 2022/11/7  20:58
 */

public class BrandService {
    //将他放入成员的位置，防止每个方法都调用一次 ，占用资源
    SqlSessionFactory sqlSessionFactory = sqlsessionFactoryUtills.getSqlSessionFactory();
    public List<Brand> selectAll() {

        //获取sql session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectall();

        sqlSession.close();

        return brands;
    }

    public void insert(Brand brand){
        //同上
        //获取sql session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.insert(brand);
        //注意提交数据
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    public Brand selectById(int id) {

        //获取sql session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        Brand brand = mapper.selectById(id);

        sqlSession.close();
        return brand;
    }

    public void update(Brand brand){
        //获取sql session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.update(brand);

        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(int id){
        //获取sql session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.delete(id);

        sqlSession.commit();
        sqlSession.close();
    }

}
