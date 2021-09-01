package com.linyuang.www.mapper;


import com.linyuang.www.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User表dao
 * @author Lenovo
 */
@Mapper
public interface UserDao {
    /**
     *根据
     * @param account 要查找的账号
     * @return com.linyuang.www.po.User
     */
    User selectUserByAccount(String account);
}
