package com.maben.websocket.service.impl;

import com.maben.websocket.dao.User;
import com.maben.websocket.mapper.UserMapper;
import com.maben.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public List<User> page(int pageNum, int pageSize) {
        return userMapper.page((pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public List<User> login(String name, String pwd) {
        return userMapper.login(name,pwd);
    }

    @Override
    public Integer add(User user) {
        return userMapper.add(user);
    }

    @Override
    public Integer update(User user) {
        return userMapper.update(user);
    }

    @Override
    public Integer delete(Integer id) {
        return userMapper.delete(id);
    }
}
