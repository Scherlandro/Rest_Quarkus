package com.walletapi.service;

import java.util.List;
import java.util.Optional;

import com.walletapi.entity.Role;
import com.walletapi.entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    User getUser(String username);
    Optional<User> findById(Long id) ; 
    List<User> listUsers();
   // List<User> listUsers(Integer page, Integer pageSize);
    void removeUser(Long id);
}
