package dev.greben;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MobileTestSample {

    private AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Medium Phone API 36.1");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "org.wikipedia.alpha");
        caps.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        caps.setCapability("noReset", false);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @Test
    public void searchForExistingPage() throws InterruptedException {
        driver.findElement(By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        Thread.sleep(100);

        // Элементы управления поиском
        driver.findElement(By.id("org.wikipedia.alpha:id/search_container")).click();
        Thread.sleep(100);
        driver.findElement(By.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Москва");
        // Ожидаем отображение результатов поиска
        Thread.sleep(2000); // Дожидаемся появления результата

        // Проверяем наличие найденной страницы
        final var resultTitle = driver.findElement(By.xpath("//android.widget.TextView[@text='Redirected from Москва']"));
        assertNotNull(resultTitle.getText());
    }

    @Test
    public void searchForNonexistentPage() throws InterruptedException {
        driver.findElement(By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        Thread.sleep(100);

        // Поиск по несуществующему запросу
        driver.findElement(By.id("org.wikipedia.alpha:id/search_container")).click();
        Thread.sleep(100);
        driver.findElement(By.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("abcdefg123");
        // Ожидаем отображение результатов поиска
        Thread.sleep(2000); // Дожидаемся появления результата


        // Проверяем отсутствие результатов поиска
        final var noResultsMessage = driver.findElement(By.xpath("//android.widget.TextView[@text='No results']"));
        assertTrue(noResultsMessage.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null){
            driver.quit(); // закрываем сессию драйвера
        }
    }
}
