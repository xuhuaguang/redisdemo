package com.example.redisdemo.service.impl;

import com.example.redisdemo.bean.User;
import com.example.redisdemo.dao.UserDao;
import com.example.redisdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    //注入redis的
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findUserByName(String userName) {
        return userDao.findByName(userName);
    }

    /**
     * 获取用户逻辑：
     * 如果缓存存在，从缓存中获取用户信息
     * 如果缓存不存在，从 DB 中获取用户信息，然后插入缓存
     */
    @Override
    public User findUserById(Long id) {
        String key = "user_key_"+id;
        //opsForValue操作简单属性的数据
        ValueOperations<String,User> ops = redisTemplate.opsForValue();
        Boolean flag = redisTemplate.hasKey(key);
        //缓存存在
        if (flag) {
            User user = ops.get(key);
            logger.info("UserServiceImpl.findUserById() : 从缓存中获取了用户 >> " + user.toString());
            return user;
        }
        //缓存不存在，查询数据库，并把信息存进缓存里
        User user = userDao.findById(id);
        //ops.set(key, user);
        ops.set(key,user,1, TimeUnit.MINUTES);//1分钟过期 存入缓存的时候也可以设置过期时间:
        logger.info("UserServiceImpl.findUserById() : 存入缓存用户 >> " + user.toString());
        return user;
    }

    @Override
    public Long saveUser(User user) {
        return userDao.saveUser(user);
    }

    /**
     * 更新用户逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public Long updateUser(User user) {
        Long aLong = userDao.updateUser(user);
        String key = "user_key_" + user.getId();
        //判断是否存在key
        boolean haskey = redisTemplate.hasKey(key);
        if (haskey) {
            redisTemplate.delete(key);
            logger.info("UserServiceImpl.updateUser() : 从缓存中删除用户 >> " + user.toString());
        }
        return aLong;
    }

    @Override
    public Long deleteUser(Long id) {
        Long aLong = userDao.deleteUser(id);
        // 缓存存在，删除缓存
        String key = "user_key_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("UserServiceImpl.updateUser() : 从缓存中删除用户id >> " + id);
        }
        return aLong;
    }
}
