package com.tqs.lab4.ex4_a;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PurchasePage {
  
  @FindBy(css="h2") private WebElement title;
  @FindBy(css="p:nth-child(2)") private WebElement airline;
  @FindBy(css="p:nth-child(3)") private WebElement flightNumber;
  @FindBy(css="p:nth-child(4)") private WebElement price;

  @FindBy(name="inputName") private WebElement nameInput;
  @FindBy(name="address") private WebElement addressInput;
  @FindBy(name="city") private WebElement cityInput;
  @FindBy(name="state") private WebElement stateInput;
  @FindBy(name="zipCode") private WebElement zipCodeInput;
  @FindBy(name="cardType") private WebElement creditCardTypeInput;
  @FindBy(name="creditCardNumber") private WebElement creditCardNumberInput;
  @FindBy(name="creditCardMonth") private WebElement creditCardMonthInput;
  @FindBy(name="creditCardYear") private WebElement creditCardYearInput;
  @FindBy(name="nameOnCard") private WebElement nameOnCardInput;
  @FindBy(name="rememberMe") private WebElement rememberMeInput;
  @FindBy(className="btn-primary") private WebElement submitBtn;

  public String getTitle() {
    return title.getText();
  }

  public String getAirline() {
    return airline.getText();
  }

  public String getFlightNumber() {
    return flightNumber.getText();
  }

  public String getPrice() {
    return price.getText();
  }

  public void fillForm(String name, String address, String city, String state, String zipCode, String creditCardType, Integer creditCardNum, Integer month, Integer year, String cardName, boolean rememberMe) {
    fillTextInput(nameInput, name);
    fillTextInput(addressInput, address);
    fillTextInput(cityInput, city);
    fillTextInput(stateInput, state);
    fillTextInput(zipCodeInput, zipCode);
    fillSelectInput(creditCardTypeInput, creditCardType);
    fillTextInput(creditCardNumberInput, creditCardNum.toString());
    fillTextInput(creditCardMonthInput, month.toString());
    fillTextInput(creditCardYearInput, year.toString());
    fillTextInput(nameOnCardInput, cardName);

    if (rememberMe) rememberMeInput.click();

    submitBtn.click();
  }

  private void fillTextInput (WebElement element, String content) {
    element.clear();
    element.sendKeys(content);
  }

  public void fillSelectInput (WebElement element, String optText) {
    element.findElement(By.xpath(String.format("//option[. = '%s']", optText))).click();
  }

}
