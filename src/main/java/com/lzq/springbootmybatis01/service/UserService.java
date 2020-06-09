package com.lzq.springbootmybatis01.service;

import com.lzq.springbootmybatis01.entity.User;

public interface UserService {
    public User selectUserById(Integer id);
    public User selectUserByTemplate(Integer id);
}
