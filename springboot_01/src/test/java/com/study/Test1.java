package com.study;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.dao.IUserDao;
import com.study.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBoot.class)
public class Test1 {
    @Autowired
    private IUserDao dao;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test1(){
        List<User> all = dao.findAll();
        System.out.println(all);
    }

    @Test
    public void test2() throws JsonProcessingException {
        //1.先从Redis中取出数据
        String userJson = redisTemplate.boundValueOps("user.findAll").get();
        //2.判断Redis中是否存在数据
        if (userJson == null){
            //3.如果没有数据，则从数据库查找，并存入Redis
            List<User> all = dao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            userJson = mapper.writeValueAsString(all);
            redisTemplate.boundValueOps("user.findAll").set(userJson);
            System.out.println("从数据库中获取数据");
        }else {
            //4.有数据则直接返回数据
            System.out.println("从Redis中获取数据");
        }
        System.out.println(userJson);
    }

}
