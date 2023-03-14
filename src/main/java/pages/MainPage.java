package pages;

import elements.Button;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {
    private static By locator = By.xpath("//img[contains(@class,'banner-image') and @alt='Selenium Online Training']");
    private static String name = "mainPage";

    public MainPage() {
        super(locator, name);
    }

    private By locatorButtonElements = By.xpath("//div[contains(@class,'card-body')]/*[contains(text(),'Elements')]");
    private Button buttonElements = new Button(locatorButtonElements, "buttonElements");

    public void clickButtonElements() {
        buttonElements.clickJS();
    }
}
