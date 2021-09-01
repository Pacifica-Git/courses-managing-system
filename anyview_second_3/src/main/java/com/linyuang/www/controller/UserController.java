package com.linyuang.www.controller;

import com.linyuang.www.po.User;
import com.linyuang.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public void login(User inputUser, HttpServletRequest request){
        int result = userService.userLogin(inputUser);
    }
}
