package com.andersen.database.dao.impl;

import com.andersen.database.dao.UserDao;
import com.andersen.database.entity.RoleEnum;
import com.andersen.database.entity.User;
import org.junit.Assert;
import org.junit.Test;

class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    void save() {
        Integer beforeListSize = userDao.getAllUsers().size();
        User user = new User("Test", RoleEnum.USER, "test", "test");
        userDao.save(user);
        Assert.assertEquals(1, userDao.getAllUsers().size() - beforeListSize);
    }


    @Test
    void get() {
        Assert.assertNotNull(userDao.get(1));
    }

    @Test
    void getAllUsers() {
        Assert.assertTrue(userDao.getAllUsers().size() > 0);
    }

    @Test
    void update() {
        User user = new User("Test", RoleEnum.USER, "test", "test");
        userDao.save(user);
        user.setName("admin");
        user.setRole(RoleEnum.ADMIN);
        user.setPassword("admin");
        userDao.update(user);

        Assert.assertEquals(user, userDao.get(user.getId()));
        userDao.delete(user.getId());
    }

    @Test
    void delete() {
        User user = new User("Test", RoleEnum.USER, "test", "test");
        userDao.save(user);
        Integer beforeListSize = userDao.getAllUsers().size();
        userDao.delete(user.getId());
        Assert.assertEquals(0, userDao.getAllUsers().size());
    }

    @Test
    void isExists() {
        User user = new User("Test", RoleEnum.USER, "test", "test");
        userDao.save(user);
        userDao.isExists(user);
        Assert.assertEquals(user, userDao.get(user.getId()));
        userDao.delete(user.getId());
    }
}