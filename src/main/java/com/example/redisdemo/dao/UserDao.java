package com.example.redisdemo.dao;

import com.example.redisdemo.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户接口类
 */
public interface UserDao {

    User findById(@Param("id") Long id);
    Long saveUser(User user);
    Long updateUser(User user);
    Long deleteUser(Long id);


    User findByName(@Param("userName") String userName);
    List<User> findAllUser();
}
