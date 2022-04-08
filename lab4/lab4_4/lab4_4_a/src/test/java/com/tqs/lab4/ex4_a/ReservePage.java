package com.tqs.lab4.ex4_a;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReservePage {

  @FindBy(css="h3") private WebElement title;
  @FindBy(className="table") private WebElement flightsTable;

  public String getTitle () {
    return title.getText();
  }

  public void chooseFLight (int flightIdx) {
    flightsTable.findElement(By.cssSelector(String.format("tr:nth-child(%d) .btn", flightIdx))).click();
  }
  
}
