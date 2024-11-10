package com.maben.websocket.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Enter the websocket test interface
 */
@RestController
@RequestMapping("WebSocketController")
public class WebSocketController {
    @RequestMapping("init/{username}")
    public ModelAndView init(@PathVariable String username, HttpServletResponse response) throws IOException {
        return new ModelAndView("webSocket/webSocket");
    }

    @RequestMapping("login")
    public ModelAndView login(){
        return new ModelAndView("webSocket/login");
    }

    @RequestMapping("register")
    public ModelAndView register(){
        return new ModelAndView("webSocket/register");
    }
}
