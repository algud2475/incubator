package forms;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import utils.WaitsUtil;

public class ElementsForm {
    private static By locatorElementsForm = By.xpath("//div[text()='Elements']//ancestor::div[contains(@class,'element-group')]/div[contains(@class,'show')]");

    private Button buttonElements = new Button(By.xpath("//div[contains(text(),'Elements')]"), "Button Elements");
    private Button buttonWebTables = new Button(By.xpath("//div[text()='Elements']//ancestor::div[contains(@class,'element-group')]//span[contains(text(),'Web Tables')]"), "Button WebTables");

    public boolean isFormOpened() {
        try {
            WaitsUtil.isAppearWait(locatorElementsForm).isDisplayed();
            return true;
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }


    public void openForm() {
        if (!isFormOpened()) {
            buttonElements.click();
        }
    }

    public void closeForm() {
        if (isFormOpened()) {
            buttonElements.click();
        }
    }

    public void clickButtonWebTables() {
        buttonWebTables.click();
    }

}
