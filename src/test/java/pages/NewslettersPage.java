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
    private static final By LOCATOR = By.xpath("//section[@id='cpt-newletters-archive-esturgeon-block_624d5b94e56bc']");
    private static final String NAME = "Newsletters page";
    private final By locatorSubscriptionPlanButton = By.xpath("//label[contains(@class,'block w-full btn-tertiary unchecked-label cursor-pointer')]");
    private IButton randomSubscriptionPlanButton;
    private ILink randomSubscriptionPlanSeePreviewLink;
    private String locatorRandomSubscriptionPlanButton;
    private String locatorRandomSubscriptionPlanSeePreviewLink;
    private String nameOfRandomSubscriptionPlan;

    public NewslettersPage() {
        super(LOCATOR, NAME);
    }

    public void clickRandomSubscriptionPlanSeePreviewLink() {
        randomSubscriptionPlanSeePreviewLink.getJsActions().click();
    }

    public void clickRandomSubscriptionPlanButton() {
        randomSubscriptionPlanButton.click();
    }

    public String getNameOfRandomSubscriptionPlan() {
        return nameOfRandomSubscriptionPlan;
    }

    public void chooseRandomSubscriptionPlan() {
        List<IButton> subscriptionPlanButtons = getSubscriptionPlanButtons();
        int randomButtonNumber = RandomUtil.getRandomLimitedNumber(subscriptionPlanButtons.size());
        randomSubscriptionPlanButton = subscriptionPlanButtons.get(randomButtonNumber);
        locatorRandomSubscriptionPlanButton = String.format("//label[contains(@class,'block w-full btn-tertiary unchecked-label cursor-pointer') and @for=%s]", randomSubscriptionPlanButton.getAttribute("for"));
        locatorRandomSubscriptionPlanSeePreviewLink = locatorRandomSubscriptionPlanButton + "/ancestor::*[2]/a[contains(@class,'text-primary mt-3 inline-block')]";
        randomSubscriptionPlanSeePreviewLink = AqualityServices.getElementFactory().getLink(By.xpath(locatorRandomSubscriptionPlanSeePreviewLink), "Random Subscription Plan See Preview Link");
        nameOfRandomSubscriptionPlan = randomSubscriptionPlanSeePreviewLink.getAttribute("href").substring(AqualityServices.getBrowser().getCurrentUrl().length() + 1);
    }

    private List<IButton> getSubscriptionPlanButtons() {
        return AqualityServices.getElementFactory().findElements(locatorSubscriptionPlanButton, ElementType.BUTTON);
    }
}
