package com.bookstore.bookapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.bookapp.bean.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByAuthor(String author);
}
