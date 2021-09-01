package com.linyuang.www.service.impl;

import com.linyuang.www.mapper.UserDao;
import com.linyuang.www.po.User;
import com.linyuang.www.service.UserService;
import com.linyuang.www.util.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 * 用户业务层实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    /**
     *验证用户输入的信息是否与数据库的数据中一致
     * @param inputUser 用户输入的信息
     * @return int -1：查无用户；0：密码错误；1：验证通过
     */
    @Override
    public int userLogin(User inputUser) {
        User returnUser = userDao.selectUserByAccount(inputUser.getAccount());
        if(returnUser == null){
            return -1;
        }else{
            if(inputUser.getPassword().equals(EncryptionUtil.decryption(returnUser.getPassword()))){
                log.info("账户[{}]登陆成功",inputUser.getAccount());
                return 1;
            }else{
                log.info("账户[{}]登陆失败：密码错误",inputUser.getAccount());
                return 0;
            }
        }
    }
}
