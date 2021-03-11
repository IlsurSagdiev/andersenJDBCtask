package com.andersen.database.dao;

import com.andersen.database.entity.User;

import java.util.List;

public interface UserDao {

    public void save(User user);

    public User get(Integer id);

    public void update(User user);

    public void delete(Integer id);

    public List<User> getAllUsers();

    public boolean isExists(User user);
}
