package com.tqs.lab5.ex2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSearchSteps {
  Library library = new Library();
  List<Book> result = new ArrayList<>();

  @DataTableType
  public Book bookEntry (Map<String, String> entry) throws ParseException {
    return new Book(entry.get("title"), entry.get("author"), new SimpleDateFormat("dd-MM-yyyy").parse(entry.get("published")), entry.get("category"));
  }

  @Given("These books exist")
  public void addNewBook(List<Book> books) throws ParseException {
    for (Book book : books) library.addBook(book);
  }

  @When("the customer searches for books published between {string} and {string}")
  public void setSearchParameters(final String from, final String to) throws ParseException {
    Date fromDate = new SimpleDateFormat("yyyy").parse(from);
    Date toDate = new SimpleDateFormat("yyyy").parse(to);
    result = library.findBooks(fromDate, toDate);
  }

  @When("the customer searches for books written by {string}")
  public void the_customer_searches_for_books_written_by(String author) {
    result = library.findBooksByAuthor(author);
  }

  @When("the customer searches for books with the category {string}")
  public void the_customer_searches_for_books_with_the_category(String category) {
    result = library.findBooksByCategory(category);
  }

  @When("the customer searches for books with the title {string}")
  public void the_customer_searches_for_books_with_the_title(String title) {
    result = library.findBooksByTitle(title);
  }

  @Then("{int} books should have been found")
  public void verifyAmountOfBooksFound(final int booksFound) {
    assertEquals(booksFound, result.size());
  }

  @Then("Book {int} should have the title {string}")
  public void verifyBookAtPosition(final int position, final String title) {
    assertEquals(title, result.get(position - 1).getTitle());
  }
}
