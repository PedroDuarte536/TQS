package com.tqs.lab4.ex4_a;
// Generated by Selenium IDE
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.seljup.Arguments;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class Ex2Test {
  @Test
  public void ex4_a(@Arguments("--headless") ChromeDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1130, 689));

    String from = "Portland";
    String to = "Cairo";

    IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
    indexPage.chooseLocations(from, to);

    ReservePage reservePage = PageFactory.initElements(driver, ReservePage.class);
    assertThat(reservePage.getTitle()).isEqualTo(String.format("Flights from %s to %s:", from, to));
    reservePage.chooseFLight(3);

    PurchasePage purchasePage = PageFactory.initElements(driver, PurchasePage.class);
    assertThat(purchasePage.getTitle()).isEqualTo(String.format("Your flight from %s to %s has been reserved.", from, to));
    assertThat(purchasePage.getAirline()).isEqualTo("Airline: Aer Lingus");
    assertThat(purchasePage.getFlightNumber()).isEqualTo("Flight Number: 9696");
    assertThat(purchasePage.getPrice()).isEqualTo("Price: $200.98");
    purchasePage.fillForm("Pedro", "Aveiro", "Aveiro", "Aveiro", "12", "American Express", 10, 11, 2020, "Jorge", true);

    assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");

    ConfirmationPage confirmationPage = PageFactory.initElements(driver, ConfirmationPage.class);
    assertThat(confirmationPage.getCardExpiracyDate()).isEqualTo("11 /2020");
  }
}
