package com.mm.demo.service.impl;

import com.mm.demo.dao.LoginMapper;
import com.mm.demo.entity.User;
import com.mm.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired(required = false)
    private LoginMapper loginMapper;

    @Override
    public User login(String name, String password){
        User user=loginMapper.login(name,password);
        return user;
    }

    @Override
    public User getUserList() {
        List<User> user=loginMapper.getUserList();
        return (User) user;
    }

    @Override
    public User getUserByName(String name) {
        User user=loginMapper.getUserByName(name);
        return user;
    }

    @Override
    public void addUser(User user) {
        loginMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        loginMapper.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        loginMapper.deleteUser(id);
    }
}
