package com.example.bookstore1;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore1.domain.Book;
import com.example.bookstore1.domain.BookRepository;
import com.example.bookstore1.domain.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("Art of War");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Sun Tzu");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Moby Dick", "Herman Melville", 1851, "1999-123", new Category("Nautical fiction"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	

	
}
