package com.trabalho.autostore.service;

import com.trabalho.autostore.dao.UserDao;
import com.trabalho.autostore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserService")
@Transactional
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UserDao userDao;

    @Value("${system.web.address}")
    private String systemWebAddress;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsrCode(int usrCode) {
        return userDao.findByUsrCode(usrCode);
    }

    @Override
    public User findByUsrName(String usrName) {
        return userDao.findByUsrName(usrName);
    }

    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public long count() {
        return userDao.count();
    }
}
