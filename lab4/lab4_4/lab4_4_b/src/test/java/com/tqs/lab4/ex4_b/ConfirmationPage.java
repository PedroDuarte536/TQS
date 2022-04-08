package com.tqs.lab4.ex4_b;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage {

  @FindBy(css="tr:nth-child(5) > td:nth-child(2)")private WebElement cardExpiracyDate;

  public String getCardExpiracyDate() {
    return cardExpiracyDate.getText();
  }

}
