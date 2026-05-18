package com.walletapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletapi.entity.User;

//import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;


//public interface UserRepo extends PanacheRepositoryBase<User, Long> {
 public interface UserRepo extends JpaRepository<User, Long> {  
    User findByUsername(String username);
}
