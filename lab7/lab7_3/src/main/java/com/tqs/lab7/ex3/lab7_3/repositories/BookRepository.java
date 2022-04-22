package com.tqs.lab7.ex3.lab7_3.repositories;

import com.tqs.lab7.ex3.lab7_3.entities.Book;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  
}
