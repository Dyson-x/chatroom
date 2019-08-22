package com.bittech.client.dao;

import com.bittech.client.entity.User;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author Dyson
 * @date 2019/8/18 8:53
 */
public class AccountDaoTest {
    private AccountDao accountDao = new AccountDao();
    @Test
    public void userReg() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("123456789");
        user.setBrief("森");
        boolean ret = accountDao.userReg(user);
        Assert.assertTrue(ret);
    }

    @Test
    public void userLogin() {
        User user = accountDao.userLogin("test","123456789");
        Assert.assertNotNull(user);
    }
}