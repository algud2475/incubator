package utils;

import driver.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitsUtil {
    private static int timeOfWaiting = 5;

    public static WebElement isClickableWait(By locator) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOfWaiting)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement isAppearWait(By locator) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOfWaiting)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static Alert isAlert(){
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOfWaiting)).until((ExpectedConditions.alertIsPresent()));
    }
}
