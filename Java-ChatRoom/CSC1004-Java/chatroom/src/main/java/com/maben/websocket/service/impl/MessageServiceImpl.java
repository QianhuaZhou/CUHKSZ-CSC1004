package com.maben.websocket.service.impl;

import com.maben.websocket.dao.Message;
import com.maben.websocket.mapper.MessageMapper;
import com.maben.websocket.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageMapper messageMapper;
    @Override
    public List<Message> list() {
        return messageMapper.list();
    }

    @Override
    public List<Message> page(int pageNum, int pageSize) {
        return messageMapper.page((pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public Integer add(Message message) {
        return messageMapper.add(message);
    }

    @Override
    public Integer update(Message message) {
        return messageMapper.update(message);
    }

    @Override
    public Integer delete(Integer id) {
        return messageMapper.delete(id);
    }
}
