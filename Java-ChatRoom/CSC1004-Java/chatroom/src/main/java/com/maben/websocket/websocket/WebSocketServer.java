package com.maben.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maben.websocket.dao.Message;
import com.maben.websocket.service.MessageService;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/WebSocketServer/pushMessage/{id}")
@Slf4j(topic = "m.webSocketServer")
public class WebSocketServer {

    private static ApplicationContext applicationContext;

    private MessageService messageService;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();

    private Session session;

    private String id = "";


    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;
        this.id = id;
        if (webSocketMap.containsKey(id)) {
            webSocketMap.remove(id);

            webSocketMap.put(id, session);
        } else {

            webSocketMap.put(id, session);
        }

        Message message1 = new Message();
        message1.setMsg(id+" joins at "+new Date());
        message1.setSend("system");
        //1-normal message，0-broadcast
        message1.setType(0);
        message1.setTo("group1");
        message1.setTime(new Timestamp(System.currentTimeMillis()));

        broadcast(JSON.toJSON(message1).toString());
    }


    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(id)) {
            webSocketMap.remove(id);
        }
        log.info("End of import");
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Message:" + id + ",Message:" + message);
        MessageService messageService = applicationContext.getBean(MessageService.class);

        if (StringUtils.isNotBlank(message)) {
            try {
                final JSONObject jsonObject = JSON.parseObject(message);
                final String msg = jsonObject.getString("msg");
                String msgData = EmojiParser.parseToAliases(msg, EmojiParser.FitzpatrickAction.PARSE);

                final String send = jsonObject.getString("send");

                Message message1 = new Message();
                message1.setMsg(msgData);
                message1.setSend(send);
                //1-normal message，0-broadcast
                message1.setType(1);
                message1.setTo("group1");
                message1.setTime(new Timestamp(System.currentTimeMillis()));
                messageService.add(message1);
                //broadcast
                message1.setMsg(EmojiParser.parseToUnicode(message1.getMsg()));
                broadcast(JSON.toJSON(message1).toString());
                //sendMessage(session,send+": "+msg);  Return massage
            } catch (Exception e) {
                e.printStackTrace();
                sendMessage(session,"An error occurred in the program. The error message is："+e.getMessage());
                sendMessage(session,"Please refresh and try again.");
            }
        }
    }

    /**
     * @param message
     */
    public void broadcast(String message){
        for(ConcurrentHashMap.Entry<String,Session> entry:webSocketMap.entrySet()){
            sendMessage(entry.getValue(),message);
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("Code error:" + this.id + ",Reason:" + error.getMessage());
        error.printStackTrace();
    }


    public void sendMessage(Session session,String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void sendInfo(String message, String id) throws IOException {
        log.info("Send message to:" + id + "，Message:" + message);
        if (StringUtils.isNotBlank(id) && webSocketMap.containsKey(id)) {
            webSocketMap.get(id).getBasicRemote().sendText(message);
        } else {
            log.error("User" + id + "is offline！");
        }
    }
}
