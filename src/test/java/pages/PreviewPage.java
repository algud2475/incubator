package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PreviewPage extends Form {
    private static final By LOCATOR = By.xpath("//div[contains(@class,'jquery-modal blocker current')]");
    private static final String NAME = "Preview page";
    private final ILabel labelWithName = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class,'jquery-modal blocker current')]/div"), "Label with name of Preview Page");
    private final ILabel iFrame = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class,'jquery-modal blocker current')]//iframe"), "iFrame of Preview Page");
    private final ILink clickHereLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[contains(text(),'clicking here')]"), "Click here link on iFrame of Preview Page");

    public PreviewPage() {
        super(LOCATOR, NAME);
    }

    public String getPreviewPageName() {
        return labelWithName.getAttribute("id");
    }

    public void clickUnsubscribeLink() {
        AqualityServices.getBrowser().getDriver().switchTo().frame(iFrame.getElement());
        AqualityServices.getBrowser().goTo(clickHereLink.getAttribute("href"));
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }
}
