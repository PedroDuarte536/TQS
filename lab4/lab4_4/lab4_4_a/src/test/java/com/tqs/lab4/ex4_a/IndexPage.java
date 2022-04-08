package com.tqs.lab4.ex4_a;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {

  @FindBy(name="fromPort") private WebElement fromInput;
  @FindBy(name="toPort") private WebElement toInput;
  @FindBy(className="btn-primary") private WebElement submitBtn;

  public void chooseLocations (String from, String to) {
    updateSelect(fromInput, from);
    updateSelect(toInput, to);
    submitBtn.click();
  }

  private void updateSelect (WebElement element, String optText) {
    element.findElement(By.xpath(String.format("//option[. = '%s']", optText))).click();
  }

}
