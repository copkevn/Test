package com.mm.demo.dao;

import com.mm.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper {
    User login(@Param("name") String name, @Param("password")String password);

    List<User> getUserList();
    User getUserByName(@Param("name") String name);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(@Param("id") int id);
}
