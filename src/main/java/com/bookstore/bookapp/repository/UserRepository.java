package com.bookstore.bookapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bookapp.bean.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}