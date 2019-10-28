package shipt.pages;

import com.frameworkium.core.ui.UITestLifecycle;
import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class BecomeAShopperPage extends BasePage<BecomeAShopperPage> {

    @Name("Apply Now button")
    @Visible
    @FindBy(css = "a[href*='shopper-application']")
    private WebElement applyNowButton;

    @Step("Click Apply Now button")
    public ShopperApplicationPage clickApplyNowButton() {
        applyNowButton.click();
        return PageFactory.newInstance(ShopperApplicationPage.class);
    }

}