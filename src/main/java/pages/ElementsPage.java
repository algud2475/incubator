package pages;

import forms.LeftMenu;
import org.openqa.selenium.By;

public class ElementsPage extends BaseForm {
    private static By locator = By.xpath("//div[contains(@class,'main-header') and contains(text(),'Elements')]");
    public ElementsPage() {
        super(locator, "Elements Page");
    }

    private LeftMenu leftMenu;

    public LeftMenu getLeftMenu() {
        if (leftMenu == null) {
            leftMenu = new LeftMenu();
        }
        return leftMenu;
    }


}
