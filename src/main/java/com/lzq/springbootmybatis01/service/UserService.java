package com.lzq.springbootmybatis01.service;

import com.lzq.springbootmybatis01.Entity.User;

public interface UserService {
    public User selectUserById(Integer id);
    public User selectUserByTemplate(Integer id);
}
