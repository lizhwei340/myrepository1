package com.study.dao;

import com.study.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IUserDao {

    @Select("select * from user")
    List<User> findAll();
}
