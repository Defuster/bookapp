package com.bookstore.bookapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.bookapp.bean.Book;
import com.bookstore.bookapp.repository.BookRepository;

@SpringBootApplication
public class BookappApplication {

	private static final Logger log = LoggerFactory.getLogger(BookappApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookappApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("Bob", "000-1-00-101010-0", 2000));
			repository.save(new Book("Billy", "100-1-00-101010-1", 2001));
			repository.save(new Book("Bolly", "010-1-01-101010-2", 2002));
			repository.save(new Book("Illy", "001-1-10-101010-3", 2003));
			repository.save(new Book("Olly", "200-2-00-202020-0", 1999));
			
			log.info("Books found with findAll():");
			log.info("---------------------------");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("");
			
			Book book = repository.findOne(1L);
			log.info("Book found with findOne(1L):");
			log.info("----------------------------");
			log.info(book.toString());
			log.info("");
			
			log.info("Book found with findByAuthor('Bob'):");
			log.info("------------------------------------");
			for (Book bob : repository.findByAuthor("Bob")) {
				log.info(bob.toString());
			}
			log.info("");
		};
	}
}
