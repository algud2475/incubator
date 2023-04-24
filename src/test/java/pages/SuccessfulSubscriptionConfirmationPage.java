package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SuccessfulSubscriptionConfirmationPage extends Form {
    private static final By LOCATOR = By.xpath("//h1[contains(text(),'Your subscription has been successfully confirmed.')]");
    private static final String NAME = "Successful Subscription Confirmation page";
    private final By locatorBackToTheSiteButton = By.xpath("//span[contains(text(),'Back to the site')]");
    private final IButton backToTheSiteButton = AqualityServices.getElementFactory().getButton(locatorBackToTheSiteButton, "Back to the site button");

    public SuccessfulSubscriptionConfirmationPage() {
        super(LOCATOR, NAME);
    }

    public void clickBackToTheSiteButton() {
        backToTheSiteButton.clickAndWait();
    }
}
