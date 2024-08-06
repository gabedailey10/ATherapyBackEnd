package com.aTh.Atherapy.repository;

import com.aTh.Atherapy.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {


    List<User> findByUserNameContaining(String userName);
}
