package com.linyuang.www.service;

import com.linyuang.www.po.User;

public interface UserService {
    /**
     *用户登陆
     * @param inputUser 输入的用户
     * @return int
     */
    int userLogin(User inputUser);
}
