package dev.greben;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MobileTestSample {

    private DriverHelper helper;

    @BeforeMethod
    public void setUp() throws Exception {
        helper = new DriverHelper("http://127.0.0.1:4723");
        Thread.sleep(100);
    }

    @DataProvider(name = "queries")
    public Object[][] createDataQuery() {
        return new Object[][] {
                { "Москва" },
                { "Сочи" },
                { "Казань" },
        };
    }

    @Test(dataProvider = "queries")
    public void searchForExistingPage(String query) throws InterruptedException {
        helper.findElementById("fragment_onboarding_skip_button").click();
        Thread.sleep(100);

        // Элементы управления поиском
        helper.findElementById("search_container").click();
        Thread.sleep(100);
        helper.findElementById("search_src_text").sendKeys(query);
        // Ожидаем отображение результатов поиска
        Thread.sleep(2000); // Дожидаемся появления результата
        helper.findElementByText("Redirected from " + query).click();
        Thread.sleep(2000); // Дожидаемся появления результата
        helper.findElementById("closeButton").click();
        Thread.sleep(100);
        helper.findElementById("page_contents_container").getText();

        // Проверяем наличие найденной страницы
        assertTrue(helper.findElementById("page_contents_container").isDisplayed());
    }

    @Test
    public void searchForNonexistentPage() throws InterruptedException {
        helper.findElementById("fragment_onboarding_skip_button").click();
        Thread.sleep(100);

        // Поиск по несуществующему запросу
        helper.findElementById("search_container").click();
        Thread.sleep(100);
        helper.findElementById("search_src_text").sendKeys("abcdefg123");
        // Ожидаем отображение результатов поиска
        Thread.sleep(2000); // Дожидаемся появления результата


        // Проверяем отсутствие результатов поиска
        final var noResultsMessage = helper.findElementByText("No results");
        assertTrue(noResultsMessage.isDisplayed());
    }

    @Test
    public void goToSettings() throws InterruptedException {
        helper.findElementById("fragment_onboarding_skip_button").click();
        Thread.sleep(100);

        // Нажатие на кнопку More
        helper.findElementById("nav_tab_more").click();
        Thread.sleep(100);
        helper.findElementById("main_drawer_settings_container").click(); // Переход в настройки
        Thread.sleep(100);
        helper.findElementByText("Wikipedia languages").click();
        Thread.sleep(100);
        helper.findElementByText("Add language").click();
        Thread.sleep(100);
        helper.findElementByText("Русский").click();
        Thread.sleep(100);


        // Проверяем наличие элемента для выбора языка
        final var noResultsMessage = helper.findElementByText("Русский");
        assertTrue(noResultsMessage.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (helper != null) {
            helper.quit(); // закрываем сессию драйвера
        }
    }
}
