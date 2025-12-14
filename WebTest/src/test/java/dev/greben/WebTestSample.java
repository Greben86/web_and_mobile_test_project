package dev.greben;

import dev.greben.pages.ArticlesPage;
import dev.greben.pages.FeedPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebTestSample {
    private WebDriver driver;

    @BeforeClass
    void launchBrowser() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    void createContextAndPage() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
    }

    @AfterMethod
    void closeContext() {
        driver.close();
        driver.quit();
        driver = null;
    }

    @Test(testName = "Тест перехода на тематическую страницу разработки")
    void testCaseHabr1() {
        final var page = new FeedPage(driver);
        page.open();
        page.getMenuButton().click();
        page.getByXPath("/html/body/div[1]/div/div/div[3]/div[3]/div/div/div[1]/nav/a[8]").click();
        final var currentText = page.getByCssSelector(".tm-section-name__text").getText();

        // Проверяем, соответствует ли заголовок страницы ожидаемому значению
        final var expectedText = "Разработка";
        assertEquals(currentText, expectedText);
    }

    @Test(testName = "Тест перехода на страницу поиска")
    void testCaseHabr2() throws InterruptedException {
        final var page = new FeedPage(driver);
        page.open();
        page.getSearchButton().click();
        page.getByCssSelector("input.tm-search__input").sendKeys("Docker");
        page.getByCssSelector("input.tm-search__input").sendKeys(Keys.ENTER);

        // Проверяем, соответствует ли адрес страницы ожидаемому адресу
        final var expectedUrl = "https://habr.com/ru/search/";
        assertTrue(driver.getCurrentUrl().contains(expectedUrl));
    }

    @Test(testName = "Тест поиска организации")
    void testCaseHabr3() {
        final var page = new ArticlesPage(driver);
        page.open();
        page.getOrganisationsLink().click();
        page.getByCssSelector(".tm-input-text-decorated__input").sendKeys("Яндекс");
        page.getByCssSelector(".tm-input-text-decorated__input").sendKeys(Keys.ENTER);

        // Проверяем, соответствует ли адрес страницы ожидаемому адресу
        final var expectedValue = "Яндекс";
        final var foundElement = page.findElementByText(expectedValue).getText();
        assertEquals(foundElement, expectedValue);
    }

    @Test(testName = "Тест перехода на следующую страницу")
    void testCaseHabr4() {
        final var page = new FeedPage(driver);
        page.open();
        page.getByCssSelector("#pagination-next-page").click();

        // Проверяем, соответствует ли адрес страницы ожидаемому адресу
        final var expectedUrl = "https://habr.com/ru/feed/page2/";
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test(testName = "Тест перехода на страницу песочницы")
    void testCaseHabr5() {
        final var page = new FeedPage(driver);
        page.open();
        page.getByCssSelector("div.tm-footer-menu__block:nth-child(2) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)").click();
        final var currentText = page.getByCssSelector(".tm-tabs__tab-link_active").getText();

        // Проверяем, соответствует ли пункт меню ожидаемому значению
        final var expectedText = "ОЖИДАЮТ ПРИГЛАШЕНИЯ";
        assertEquals(expectedText, currentText);
    }
}
