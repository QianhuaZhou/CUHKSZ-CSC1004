package com.maben.websocket.controller;


import com.maben.websocket.dao.Message;
import com.maben.websocket.dao.User;
import com.maben.websocket.service.impl.MessageServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    @Resource
    MessageServiceImpl messageService;

    @GetMapping("/page")
    public List<Message> getUserPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return messageService.page(pageNum,pageSize);
    }
}
