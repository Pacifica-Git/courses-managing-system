package com.linyuang.www.service.impl;

import com.linyuang.www.po.User;
import com.linyuang.www.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 * 用户业务层实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public int userLogin(User inputUser) {
        return 0;
    }
}
