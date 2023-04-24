package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private static final By LOCATOR = By.xpath("//strong[contains(text(),'Top tags')]");
    private static final String NAME = "Main page";
    private final String MainPageURL = "https://www.euronews.com/";
    private final By locatorNewslettersLink = By.xpath("//ul[contains(@class,'o-header-topbar__internal-links')]//a[contains(@href,'/newsletters')]");
    private final ILink newslettersLink = AqualityServices.getElementFactory().getLink(locatorNewslettersLink, "Newsletters Link");
    private final By locatorAgreeWithCookiesButton = By.xpath("//button[@id='didomi-notice-agree-button']");
    private final IButton agreeWithCookiesButton = AqualityServices.getElementFactory().getButton(locatorAgreeWithCookiesButton, "Agree With Cookies Button");

    public MainPage() {
        super(LOCATOR, NAME);
    }

    public String getMainPageURL() {
        return MainPageURL;
    }

    public void clickNewslettersLink() {
        newslettersLink.click();
    }

    public boolean agreeWithCookiesIsDisplayed() {
        return agreeWithCookiesButton.state().waitForDisplayed();
    }

    public void agreeWithCookies() {
        agreeWithCookiesButton.click();
    }
}
