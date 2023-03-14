package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized --remote-allow-origins=*"));
        }
        return driver;
    }

    public static void quit() throws InterruptedException {
        Driver.getDriver().quit();
        driver = null;
    }

    public static void getUrl(String url) {
        getDriver().get(url);
    }
}
