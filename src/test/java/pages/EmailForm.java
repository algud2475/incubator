package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EmailForm extends Form {
    private static final By LOCATOR = By.xpath("//section[contains(@class,'cpt-newletters-archive-esturgeon cta-newsletter-esturgeon relative bg-black py-6 md:py-16 sticky bottom-0')]");
    private static final String NAME = "Email form";
    private final By locatorEmailField = By.xpath("//input[contains(@type,'email')]");
    private final ITextBox emailField = AqualityServices.getElementFactory().getTextBox(locatorEmailField, "Input email field");
    private final By locatorEmailSubmitButton = By.xpath("//form[@id='register-newsletters-form']//input[@type='submit']");
    private final IButton emailSubmitButton = AqualityServices.getElementFactory().getButton(locatorEmailSubmitButton, "Email submit button");


    public EmailForm() {
        super(LOCATOR, NAME);
    }

    public void fillEmailField(String email) {
        emailField.clearAndType(email);
    }

    public void clickEmailSubmitButton() {
        emailSubmitButton.click();
    }
}
