package dev.greben.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ArticlesPage extends AbstractPage {
    private final String url = "https://habr.com/ru/articles/";

    public ArticlesPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
    }

    public WebElement getOrganisationsLink() {
        return getByCssSelector("span.tm-tabs__tab-item:nth-child(6) > a:nth-child(1)");
    }
}
