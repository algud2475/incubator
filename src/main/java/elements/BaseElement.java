package elements;

import driver.Driver;
import org.openqa.selenium.*;
import utils.WaitsUtil;

public abstract class BaseElement {
    private By locator;
    private String elementName;

    public BaseElement(By locator, String elementName) {
        this.locator = locator;
        this.elementName = elementName;
    }

    protected WebElement getClickableElement() {
        return WaitsUtil.isClickableWait(locator);
    }

    public void click() {
        WaitsUtil.isClickableWait(locator).click();
    }

    public void clickJS() {
        WebElement webElement = getClickableElement();
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
        webElement.click();
    }

}
