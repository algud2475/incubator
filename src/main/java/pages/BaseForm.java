package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseForm {
    private By locator;
    private String name;

    public BaseForm(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isPageOpened() {
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            return true;
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }
}
