package com.mm.demo.service;

import com.mm.demo.entity.User;

public interface LoginService {
    /**
     * 登陆时查询是否存在
     * @param name
     * @param password
     * @return
     */
    User login(String name, String password);

    /**
     * 获取所有用户信息
     * @return
     */
    User getUserList();


    /**
     * 修改时根据姓名查询
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 新增对象
     * @param user
     */
    void addUser(User user);

    /***
     * 修改对象
     * @param user
     */
    void updateUser(User user);

    /***
     * 删除对象，根据id
     * @param id
     */
    void deleteUser(int id);
}
