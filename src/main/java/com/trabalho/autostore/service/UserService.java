package com.trabalho.autostore.service;

import com.trabalho.autostore.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsrCode(int usrCode);

    User findByUsrName(String usrName);

    void add(User user);

    void update(User user);

    void delete(User user);
}
