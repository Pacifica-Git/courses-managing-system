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
     * @param username 要查询的用户名
     * @return com.linyuang.www.po.User
     */
    User selectUserByUsername(String username);
}
