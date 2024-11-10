package com.maben.websocket.service;

import com.maben.websocket.dao.Message;
import com.maben.websocket.dao.User;

import java.util.List;

public interface MessageService {
    List<Message> list();
    List<Message> page(int pageNum,int pageSize);
    Integer add(Message message);
    Integer update(Message message);
    Integer delete(Integer id);
}
