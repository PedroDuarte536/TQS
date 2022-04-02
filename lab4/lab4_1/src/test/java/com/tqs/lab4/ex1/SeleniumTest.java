package com.tqs.lab4.ex1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumTest {

  private WebDriver driver;
  
  @BeforeEach
  void setup () {
    driver = new ChromeDriver();
  }

  @Test
  void test () {
    driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
    assertThat(driver.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");
  }

  @AfterEach
  void teardown () {
    driver.quit();
  }
  
}
