package com.example.redisdemo.service;

import com.example.redisdemo.bean.User;

public interface UserService {
    /**
     * 根据用户名称，查询用户信息
     * @param userName
     */
    User findUserByName(String userName);
    /**
     * 根据用户ID,查询用户信息
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    Long saveUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    Long updateUser(User user);

    /**
     * 根据用户ID,删除用户信息
     *
     * @param id
     * @return
     */
    Long deleteUser(Long id);
}
