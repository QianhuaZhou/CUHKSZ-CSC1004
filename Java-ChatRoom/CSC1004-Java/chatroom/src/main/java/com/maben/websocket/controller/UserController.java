package com.maben.websocket.controller;

import com.maben.websocket.dao.User;
import com.maben.websocket.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/page")
    public List<User> getUserPage(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        return userService.page(pageNum,pageSize);
    }

    @PostMapping("login")
    public String login(String name, String pwd, HttpSession session){
        List<User> users = userService.login(name, pwd);
        if(users!=null&&users.size()>0){
            session.setAttribute("user",users.get(0));
            return "ok";
        }else {

            return "no";
        }

    }

    @GetMapping("/findUser")
    public Object findUser(HttpSession session){
        Object result = session.getAttribute("user");
        if(result==null){
            return "empty";
        }
        return session.getAttribute("user");
    }

    @Resource
    UserService userService;

    @GetMapping("/list")
    public List<User> getUserList(){
        return userService.list();
    }


    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        Integer addCount = userService.add(user);
        if (addCount > 0){
            return "ok";
        }
        return "no";
    }

    @PostMapping("/update")
    public boolean updateUser(@RequestBody User user){
        Integer addCount = userService.update(user);
        if (addCount > 0){
            return true;
        }
        return false;
    }

    @GetMapping("/delete")
    public boolean delUser(Integer id){
        Integer addCount = userService.delete(id);
        if (addCount > 0){
            return true;
        }
        return false;
    }

}
