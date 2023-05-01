package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.RandomUtil;

import java.util.List;

public class NewslettersPage extends Form {
    private static final By LOCATOR = By.xpath("//section[contains(@id,'cpt-newletters-archive-esturgeon-block')]");
    private static final String NAME = "Newsletters page";
    private final List<IButton> subscriptionPlanButtons = AqualityServices.getElementFactory().findElements(By.xpath("//label[contains(@class,'block w-full btn-tertiary unchecked-label cursor-pointer')]"), ElementType.BUTTON);
    private String subscriptionPlanID;
    private String nameOfSubscriptionPlan;
    private String locatorSubscriptionPlanButton = "//label[contains(@class,'block w-full btn-tertiary unchecked-label cursor-pointer') and @for=%s]";
    private IButton subscriptionPlanButton;
    private String locatorSubscriptionPlanSeePreviewLink = "//label[contains(@class,'block w-full btn-tertiary unchecked-label cursor-pointer') and @for=%s]/ancestor::*[2]/a[contains(@class,'mt-3')]";
    private ILink subscriptionPlanSeePreviewLink;

    public NewslettersPage() {
        super(LOCATOR, NAME);
        subscriptionPlanID = getRandomSubscriptionPlanID();
        locatorSubscriptionPlanButton = String.format(locatorSubscriptionPlanButton, subscriptionPlanID);
        locatorSubscriptionPlanSeePreviewLink = String.format(locatorSubscriptionPlanSeePreviewLink, subscriptionPlanID);
        subscriptionPlanButton = AqualityServices.getElementFactory().getButton(By.xpath(locatorSubscriptionPlanButton), "Subscription Plan button");
        subscriptionPlanSeePreviewLink = AqualityServices.getElementFactory().getLink(By.xpath(locatorSubscriptionPlanSeePreviewLink), "Subscription Plan See Preview Link");
        nameOfSubscriptionPlan = subscriptionPlanSeePreviewLink.getAttribute("href").substring(AqualityServices.getBrowser().getCurrentUrl().length() + 1);
    }

    public NewslettersPage(String subscriptionPlanID) {
        super(LOCATOR, NAME);
        this.subscriptionPlanID = subscriptionPlanID;
        locatorSubscriptionPlanButton = String.format(locatorSubscriptionPlanButton, subscriptionPlanID);
        locatorSubscriptionPlanSeePreviewLink = String.format(locatorSubscriptionPlanSeePreviewLink, subscriptionPlanID);
        subscriptionPlanButton = AqualityServices.getElementFactory().getButton(By.xpath(locatorSubscriptionPlanButton), "Subscription Plan button");
        subscriptionPlanSeePreviewLink = AqualityServices.getElementFactory().getLink(By.xpath(locatorSubscriptionPlanSeePreviewLink), "Subscription Plan See Preview Link");
        nameOfSubscriptionPlan = subscriptionPlanSeePreviewLink.getAttribute("href").substring(AqualityServices.getBrowser().getCurrentUrl().length() + 1);
    }

    public void clickSubscriptionPlanSeePreviewLink() {
        subscriptionPlanSeePreviewLink.getJsActions().click();
    }

    public void clickSubscriptionPlanButton() {
        subscriptionPlanButton.getJsActions().click();
    }

    public String getNameOfSubscriptionPlan() {
        return nameOfSubscriptionPlan;
    }

    public String getSubscriptionPlanID() {
        return subscriptionPlanID;
    }

    private String getRandomSubscriptionPlanID() {
        int randomButtonNumber = RandomUtil.getRandomLimitedNumber(subscriptionPlanButtons.size());
        return subscriptionPlanButtons.get(randomButtonNumber).getAttribute("for");
    }
}
