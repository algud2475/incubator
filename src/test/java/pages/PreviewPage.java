package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public class PreviewPage extends Form {
    private static final By LOCATOR = By.xpath("//div[contains(@class,'jquery-modal blocker current')]");
    private static final By LOCATOR_OF_NAME = By.xpath("//div[contains(@class,'jquery-modal blocker current')]/div");
    private static final By LOCATOR_OF_IFRAME = By.xpath("//div[contains(@class,'jquery-modal blocker current')]//iframe");
    private static final String NAME = "Preview page";
    private final By locatorUnsubscribeLink = By.xpath("//a[contains(text(),'clicking here')]");

    public PreviewPage() {
        super(LOCATOR, NAME);
    }

    public String getPreviewPageName() {
        return AqualityServices.getElementFactory().getLabel(LOCATOR_OF_NAME, "Label with name").getAttribute("id");
    }

    public void clickUnsubscribeLink() throws InterruptedException {
        WebElement iFrame = AqualityServices.getBrowser().getDriver().findElement(LOCATOR_OF_IFRAME);
        AqualityServices.getBrowser().getDriver().switchTo().frame(iFrame);
        ILink clickHereLink = AqualityServices.getElementFactory().getLink(locatorUnsubscribeLink, "Unsubscribe link");
        AqualityServices.getBrowser().goTo(clickHereLink.getAttribute("href"));
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }
}
