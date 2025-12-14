package dev.greben.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {
    protected final WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void open();

    public WebElement getByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement getByCssSelector(String selector) {
        return driver.findElement(By.cssSelector(selector));
    }

    public WebElement findElementByText(String text) {
        return driver.findElement(By.linkText(text));
    }
}
