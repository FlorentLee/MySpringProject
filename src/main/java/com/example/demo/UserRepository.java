package com.example.demo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

        public Optional<User> findByName(String name);
        public Optional<User> findByEmail(String email);
        public Optional<User> findByPhone(String phone);

}