package elements;

import driver.Driver;
import org.openqa.selenium.*;

public abstract class BaseElement {
    private By locator;
    private String elementName;

    public BaseElement(By locator, String elementName) {
        this.locator = locator;
        this.elementName = elementName;
    }

    protected WebElement getElement() {
        return Driver.getDriver().findElement(locator);
    }
    protected By getLocator() {
        return locator;
    }
    public String getElementName() {
        return elementName;
    }

    public void click() {
        Driver.getDriver().findElement(locator).click();
    }

}
