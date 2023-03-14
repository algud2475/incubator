package pages;

import forms.LeftMenu;
import org.openqa.selenium.By;

public class ElementsPage extends BasePage {
    private static By locator = By.xpath("//div[contains(@class,'main-header') and contains(text(),'Elements')]");
    private LeftMenu leftMenu;
    public ElementsPage() {
        super(locator, "Elements Page");
    }

    public LeftMenu getLeftMenu() {
        if (leftMenu == null) {
            leftMenu = new LeftMenu();
        }
        return leftMenu;
    }


}
