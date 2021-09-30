package com.example.bookstore1.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore1.domain.Book;
import com.example.bookstore1.domain.BookRepository;
import com.example.bookstore1.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Login
	@RequestMapping(value ="/login")
	public String login() {
		return "login";
	}
	
	// Show all books
	@RequestMapping(value = {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	// REST service to findAll
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	// REST service to findById
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
	
	
	// Add new book
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addBook";
	}
	// Save new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	// Delete a book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
		
	}
	// Edit a book
	@RequestMapping(value ="/edit/{id}")
	public String editBook(@PathVariable("id")Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categorys", crepository.findAll());
		return "editBook";
	
	}
	@RequestMapping("/index")
	public String getBook(Model model) {
	
	return "index";
}
}
