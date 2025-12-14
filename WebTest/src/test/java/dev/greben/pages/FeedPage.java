package dev.greben.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FeedPage extends AbstractPage {
    private final String url = "https://habr.com/ru/feed/";

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
    }

    public WebElement getMenuButton() {
        return getByCssSelector(".burger-button");
    }

    public WebElement getSearchButton() {
        return getByCssSelector("a.tm-header-user-menu__item:nth-child(1)");
    }

    public WebElement getNextPage() {
        return getByCssSelector("#pagination-next-page");
    }
}
