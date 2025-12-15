package dev.greben;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

public class DriverHelper {
    private final AndroidDriver driver;

    public DriverHelper(String url) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Medium Phone API 36.1");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "org.wikipedia.alpha");
        caps.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        caps.setCapability("noReset", false);
        this.driver = new AndroidDriver(new URL(url), caps);
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public WebElement findElementById(String id) {
        return driver.findElement(By.id("org.wikipedia.alpha:id/"+id));
    }

    public WebElement findElementByText(String value) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='"+value+"']"));
    }

    public void quit() {
        if(driver != null){
            driver.quit(); // закрываем сессию драйвера
        }
    }
}
