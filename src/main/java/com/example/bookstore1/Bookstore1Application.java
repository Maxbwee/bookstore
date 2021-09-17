package com.example.bookstore1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore1.domain.Book;
import com.example.bookstore1.domain.BookRepository;
import com.example.bookstore1.domain.Category;
import com.example.bookstore1.domain.CategoryRepository;





@SpringBootApplication
public class Bookstore1Application {

	private static final Logger log = LoggerFactory.getLogger(Bookstore1Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Bookstore1Application.class, args);
	}

	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
				
			log.info("save a couple of books");
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Horror"));
		

			
			brepository.save(new Book("Sun Tzu", "Art of War", 1, "978010-22", crepository.findByName("History").get(0)));
	
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
