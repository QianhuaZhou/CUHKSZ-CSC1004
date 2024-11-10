## Chatroom

## Running environment setting

* JDK8
* Maven3.5
* springboot 2.6.8

## Maven dependency

```xml
<!--webSocket-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

## WebSocket configuration class

```java
package com.maben.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket
 * configuration
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }
}
```

## Page entry tested class

```java
package com.maben.websocket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Enter the websocket test interface
 */
@RestController
@RequestMapping("WebSocketController")
public class WebSocketController {
    @RequestMapping("init")
    public ModelAndView init(){
        return new ModelAndView("webSocket/webSocket");
    }
}
```

## WebSocketServer class

```java
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

```

## Front-end interface

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Chatroom</title>

    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <style>
    </style>
</head>
<body style="background-color: #FFFFFF">
    <h1>Chatroom</h1>
    <div style="width: 100%;height: 100%;">
        <table>
            <tr>
                <td>
                    <input id="msg" type="text" value="" placeholder="Please fill in the information" onkeydown="enterLis()">
                </td>
                <td>
                    <input type="button" value="send" onclick="sendMessage()" class="oa_btn">
                </td>
            </tr>
        </table>
    </div>
    <div id="cons" style="border: 1px solid red; font-size: 12px;"></div>
</body>
<script>
    var socket;
    var step = 1;
    function openSocket() {
        var host= window.location.host;

        var socketUrl = "ws://"+host+"/WebSocketServer/pushMessage/" + new Date().getTime();
        console.log(socketUrl);
        if(socket!=null){
            socket.close();
            socket=null;
        }
        socket = new WebSocket(socketUrl);
        //open
        socket.onopen = function() {
            console.log("websocket has been opened");
        };
        //gain message
        socket.onmessage = function(msg) {
            console.log(msg.data);
            $("#cons").append("<p style='margin-left: 15px;'>"+msg.data+"</p>");
        };
        //close
        socket.onclose = function() {
            console.log("websocket has been closed");
        };
        //error
        socket.onerror = function() {
            console.log("websocket have errors");
        }
    }
    $(function () {
        openSocket();
        setInterval(function () {
            var lastDom = $("#cons p:last");
            var content = lastDom.text();
            if (content.endsWith("。。。")) {
                lastDom.text(content.substr(0,content.length-1));
            }else if (content.endsWith("。。")){
                lastDom.text(content.substr(0,content.length-1));
            } else if (content.endsWith("。")){
                lastDom.text(content+"。。");
            }
        },1000)
    });
    function sendMessage() {
        var msg = {
            step: step,
            msg : $("#msg").val()
        };
        socket.send(JSON.stringify(msg));
        step+=1;
        $("#msg").val('');
    }
    function enterLis(event) {
        var oEvent = event || window.event || arguments.callee.caller.arguments[0];
        var keyCode = oEvent.keyCode;
        if (keyCode === 13) {
            sendMessage();
        }
    }

</script>
</html>
```

