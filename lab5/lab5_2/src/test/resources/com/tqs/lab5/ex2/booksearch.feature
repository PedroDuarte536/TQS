Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Background: Initialize the library
    Given These books exist
        | title | author | published | category |
        | One good book  | Anonymous  | 14-03-2013  | Sci-Fi  |
        | Some other book  | Tim Tomson  | 23-08-2014  | Horror  |
        | How to cook a dino  | Fred Flintstone  | 01-01-2012  | Comedy  |
        | How to eat a dino  | Fred Flintstone  | 01-01-2015  | Horror  |
  
  Scenario: Search books by publication year
    When the customer searches for books published between '2013' and '2014'
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'
  
  Scenario: Search books by author
    When the customer searches for books written by 'Fred Flintstone'
    Then 2 books should have been found
      And Book 1 should have the title 'How to cook a dino'
      And Book 2 should have the title 'How to eat a dino'
  
  Scenario: Search books by category
    When the customer searches for books with the category 'Horror'
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'How to eat a dino'
  
  Scenario: Search books by title
    When the customer searches for books with the title 'book'
    Then 2 books should have been found
      And Book 1 should have the title 'One good book'
      And Book 2 should have the title 'Some other book'