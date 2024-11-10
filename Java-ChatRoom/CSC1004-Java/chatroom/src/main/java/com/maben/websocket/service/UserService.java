package com.maben.websocket.service;

import com.maben.websocket.dao.User;

import java.util.List;

public interface UserService {
    List<User> list();
    List<User> page(int pageNum,int pageSize);
    List<User> login(String name,String pwd);
    Integer add(User user);
    Integer update(User user);
    Integer delete(Integer id);
}
