package com.bookstore.bookapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.bookapp.bean.Book;
import com.bookstore.bookapp.repository.BookRepository;


@Controller
public class BookController {

    @Autowired
    private BookRepository repository;
    
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    
    @RequestMapping(value="/booklist", method=RequestMethod.GET)
    public String bookList(Model model) {
    	model.addAttribute("books", repository.findAll());
			return "booklist";
    }
    @RequestMapping(value="/addBook")
    public String addBook(Model model) {
    	model.addAttribute("book", new Book());
    	return "addBook";
    }
    
    @RequestMapping(value="save", method=RequestMethod.POST)
    public String saveBook(Book book) {
    	repository.save(book);
    	return "redirect:booklist";
    }
    
    @RequestMapping(value = "delete/{id}", method=RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	repository.delete(id);
    	return "redirect:../booklist";
    }
    
	// Show all books
    @RequestMapping(value="/bookrestlist")
    public String bookRestList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "bookrestlist";
    }
  
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long id) {	
    	return repository.findOne(id);
    }       
    
    // Add new book
    @RequestMapping(value = "/add")
    public String addRestBook(Model model){
    	model.addAttribute("book", new Book());
        return "addrestbook";
    }     
    
    // Save new book
    @RequestMapping(value = "/saverest", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:bookrestlist";
    }    

    // Delete book
    @RequestMapping(value = "/deleterest/{id}", method = RequestMethod.GET)
    public String delBook(@PathVariable("id") Long id, Model model) {
    	repository.delete(id);
        return "redirect:../bookrestlist";
    }
}
