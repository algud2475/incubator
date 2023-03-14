package leftmenu;

import elements.Button;
import org.openqa.selenium.By;

public class ElementsForm extends BaseForm {
    private static By locatorElementsForm = By.xpath("//div[text()='Elements']//ancestor::div[contains(@class,'element-group')]/div[contains(@class,'show')]");

    public ElementsForm() {
        super(locatorElementsForm,"Elements Form");
    }

    private Button buttonElements = new Button(By.xpath("//div[contains(text(),'Elements')]"), "Button Elements");
    private Button buttonWebTables = new Button(By.xpath("//div[text()='Elements']//ancestor::div[contains(@class,'element-group')]//span[contains(text(),'Web Tables')]"), "Button WebTables");


    private void openForm() {
        if (!isPageOpened()) {
            buttonElements.click();
        }
    }

    public void clickButtonWebTables() {
        openForm();
        buttonWebTables.click();
    }

}
