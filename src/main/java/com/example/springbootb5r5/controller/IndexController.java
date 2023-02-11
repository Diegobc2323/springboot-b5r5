package com.example.springbootb5r5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.example.springbootb5r5.model.Author;
import com.example.springbootb5r5.model.Book;
import com.example.springbootb5r5.service.AuthorService;
import com.example.springbootb5r5.service.BookService;

@Controller
public class IndexController {

	
	public AuthorService authorService;
	public BookService bookService;
	
	@Autowired
	public IndexController(AuthorService authorService, BookService bookService) {
		this.authorService = authorService;
		this.bookService = bookService;
	}
	
	@GetMapping("/")
	public ModelAndView goToIndexPage() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		
		return modelAndView;
	}
	
	@GetMapping("/goAuthorForm")
	public ModelAndView goAuthorForm() {
		ModelAndView m = new ModelAndView("addAuthor");
		Author author = new Author();
		
		m.addObject(author);
		
		return m;
	}
	
	@GetMapping("/goBookForm")
	public ModelAndView goBookForm() {
		ModelAndView m = new ModelAndView("addBook");
		Book book = new Book();
		
		List<Author> authors = authorService.getAuthors();

		
		System.out.println(authors);
		
		m.addObject("authors",authors);
		m.addObject(book);
		
		return m;
	}
	
	
	
	@PostMapping("addAuthorForm")
	public ModelAndView addAuthorForm(@ModelAttribute("author") Author author) {
		authorService.add(author);
		//l.info(owner.toString());  ESTO SACA POR LOG EL TOSTRING DEL DUEÑO
		
		ModelAndView m = new ModelAndView("index");
		
		return m;
	}
	
	@PostMapping("addBookForm")
	public ModelAndView addBookForm(@ModelAttribute("book") Book book) {
		bookService.add(book);
		//l.info(owner.toString());  ESTO SACA POR LOG EL TOSTRING DEL DUEÑO
		
		ModelAndView m = new ModelAndView("index");
		
		return m;
	}
	
}
