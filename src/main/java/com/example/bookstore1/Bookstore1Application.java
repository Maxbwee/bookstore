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
import com.example.bookstore1.domain.User;
import com.example.bookstore1.domain.UserRepository;





@SpringBootApplication
public class Bookstore1Application {

	private static final Logger log = LoggerFactory.getLogger(Bookstore1Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Bookstore1Application.class, args);
	}

	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			urepository.deleteAll();	
			log.info("save a couple of books");
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Horror"));
		

			
			brepository.save(new Book("Art of War", "Sun Tzu", 1, "978010-22", crepository.findByName("History").get(0)));
	
			// Creating users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
