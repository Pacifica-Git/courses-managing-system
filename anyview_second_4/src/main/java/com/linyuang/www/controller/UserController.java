package com.linyuang.www.controller;

import com.linyuang.www.po.ResultDto;
import com.linyuang.www.po.User;
import com.linyuang.www.service.UserService;
import com.linyuang.www.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    /**
     *处理用户登陆请求
     * @param inputUser 接收的用户信息
     * @param request request对象
     * @return com.linyuang.www.po.ResultDto
     */
    @RequestMapping("/login")
    public ResultDto login(User inputUser, HttpServletRequest request, HttpServletResponse response){
        /*结合拦截器的作用，如果该登录请求被放行了，要么是没有设定token，要么是token验证成功；
        如果token为空，说明没有设置token，验证用户输入的信息与数据库中是否一致，一致则生成token返回成功信息，
        否则是token验证成功，直接返回成功信息*/
        String headerToken = request.getHeader("token");
        if(headerToken == null){
            int result = userService.userLogin(inputUser);
            switch (result){
                case 1:{
                    Map<String,String> map = new HashMap<>();
                    map.put("userAccount",inputUser.getAccount());
                    String newToken = JwtUtil.generateToken(map);
                    response.setHeader("token",newToken);
                    return new ResultDto(200,"登陆成功");
                }
                case 0:{
                    return new ResultDto(401,"密码错误");
                }
                default:{
                    return new ResultDto(401,"查无该账户");
                }
            }
        }else{
            return new ResultDto(200,"登陆成功");
        }
    }
}
