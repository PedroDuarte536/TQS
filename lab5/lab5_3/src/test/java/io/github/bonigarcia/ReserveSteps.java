package io.github.bonigarcia;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReserveSteps {

  private WebDriver driver;

  @When("I navigate to {string}")
  public void iNavigateTo(String url) {
    driver = WebDriverManager.chromedriver().create();
    driver.get(url);
  }

  @And("I choose travelling from {string} to {string}")
  public void iChooseDestination(String from, String to) {
    IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
    indexPage.chooseLocations(from, to);
  }

  @Then("I should see the title {string}")
  public void iShouldSee(String title) {
    ReservePage reservePage = PageFactory.initElements(driver, ReservePage.class);
    String pageTitle = reservePage.getTitle();
    driver.quit();
    
    if (!pageTitle.equals(title)) throw new AssertionError(String.format("Expected title '%s', received '%s'", title, pageTitle));
  }
}
