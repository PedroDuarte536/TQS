package com.tqs.lab7.ex3.lab7_3;

import com.tqs.lab7.ex3.lab7_3.entities.Book;
import com.tqs.lab7.ex3.lab7_3.repositories.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Testcontainers
@SpringBootTest
public class Lab73IT {

  @Autowired
  private BookRepository bookRepository;

  @Container
  public static PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:alpine")
      .withUsername("duke")
      .withPassword("password")
      .withDatabaseName("test");

  // requires Spring Boot >= 2.2.6
  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Test
  void verifyCount() {

    assertEquals(5, bookRepository.count());

  }

  @Test
  void verifyWrite() {
    
    Book b6 = new Book();
    b6.setName("Book6");

    bookRepository.save(b6);

    Book retrieved = bookRepository.getById(6L);

    assertNotEquals(null, retrieved);
    assertEquals("Book6", retrieved.getName());

  }
  
}
