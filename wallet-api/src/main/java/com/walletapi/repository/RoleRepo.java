package com.walletapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletapi.entity.Role;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
