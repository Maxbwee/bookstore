package com.example.bookstore1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
@RequestMapping("/index")
	public String getBook(Model model) {
	
	return "index";
}
}
