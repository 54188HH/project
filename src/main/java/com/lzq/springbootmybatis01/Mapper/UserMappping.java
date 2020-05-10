package com.lzq.springbootmybatis01.Mapper;

import com.lzq.springbootmybatis01.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMappping {
    @Select("select * from t_user_info")
    public List<User> method();
    @Select("select * from t_user_info where id = #{id}")
    public User selectOne(int id);
}
