package com.mnnull.mapper;

import com.mnnull.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author mnnull
 * @date 2022/11/7-20:46
 */
public interface BrandMapper {
    //查询所有
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectall();

    //插入
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void insert(Brand brand);

    //修改前回返数据
    @Select("select * from tb_brand where id = #{id};")
    @ResultMap("brandResultMap")
    Brand selectById(int id);

    //修改
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id = #{id}")
    void update(Brand brand);

    //删除
    @Delete("delete from tb_brand where id = #{id}")
    void delete(int id);
}
