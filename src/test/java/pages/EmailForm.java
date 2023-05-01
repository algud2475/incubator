package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EmailForm extends Form {
    private static final By LOCATOR = By.xpath("//section[contains(@class,'bottom-0')]");
    private static final String NAME = "Email form";
    private final ITextBox emailField = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@type='email']"), "Input email field");
    private final IButton emailSubmitButton = AqualityServices.getElementFactory().getButton(By.xpath("//form[@id='register-newsletters-form']//input[@type='submit']"), "Email submit button");

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
