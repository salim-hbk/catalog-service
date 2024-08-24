package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogserviceApplicationTests {
	@Autowired
	private WebTestClient client;

	@Test
	void contextLoads() {
	}

	@Test
	void whenPostRequestThenBookCreated(){
		var expectedBook = new Book("1235231231", "Title", "Author", 9.90);
		client.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(actualBook->{
					assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				});
	}

}
