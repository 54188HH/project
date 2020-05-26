package com.lzq.springbootmybatis01.Mapper;

import com.lzq.springbootmybatis01.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface AdminMapper {
    @Select("select * from admin where user_name = #{userName} and password = #{password}")
    public Admin selectOne(Admin admin);
}
