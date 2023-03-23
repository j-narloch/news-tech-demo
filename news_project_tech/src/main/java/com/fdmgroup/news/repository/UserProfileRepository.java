package com.fdmgroup.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.news.model.User;

@Repository
public interface UserProfileRepository extends JpaRepository<User, Integer>{

}
