package forms;

import org.openqa.selenium.By;
import pages.BaseForm;

public class LeftMenu extends BaseForm {
    private static By locator = By.xpath("//div[contains(@class,'left-pannel')]/div[contains(@class,'accordion')]");
    public LeftMenu() {
        super(locator, "Left Menu form");
    }

    private AlertsFrameWindowsForm alertsFrameWindowsForm;
    private ElementsForm elementsForm;

    public AlertsFrameWindowsForm getAlertsFrameWindowsForm() {
        if (alertsFrameWindowsForm == null) {
            alertsFrameWindowsForm = new AlertsFrameWindowsForm();
        }
        return alertsFrameWindowsForm;
    }

    public ElementsForm getElementsForm() {
        if (elementsForm == null) {
            elementsForm = new ElementsForm();
        }
        return elementsForm;
    }

}
