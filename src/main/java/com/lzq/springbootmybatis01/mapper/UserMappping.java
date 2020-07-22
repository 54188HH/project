package com.lzq.springbootmybatis01.mapper;

import com.lzq.springbootmybatis01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMappping {
    @Select("select * from t_user_info")
    public List<User> method();
    @Select("select * from t_user_info where id = #{id}")
    public User selectOne(int id);
    @Update("update t_user_info set weight = 123 where id = #{id}")
    public Integer updateById(int id);
}
