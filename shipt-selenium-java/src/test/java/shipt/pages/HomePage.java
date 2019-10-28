package shipt.pages;

import com.frameworkium.core.ui.UITestLifecycle;
import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import io.qameta.allure.Step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage<HomePage> {

    @Name("Get Paid to Shop link")
    @Visible
    @FindBy(linkText = "Get Paid to Shop")
    private WebElement getPaidToShopLink;

    @Step("Open home page")
    public static HomePage open() {
        UITestLifecycle.get().getWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        UITestLifecycle.get().getWebDriver().manage().window().maximize();
        return PageFactory.newInstance(HomePage.class, "https://shipt.com/");
    }

    @Step("Click Get Paid to Shop link")
    public BecomeAShopperPage clickGetPaidToShopLink() {
        getPaidToShopLink.click();
        return PageFactory.newInstance(BecomeAShopperPage.class);
    }

}