package shipt.pages;

import com.frameworkium.core.ui.UITestLifecycle;

import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.util.concurrent.TimeUnit;

public class ShopperApplicationPage extends BasePage<ShopperApplicationPage> {

	@Name("iframe")
	@FindBy(css = "iframe[data-qa='iframe']")
	private WebElement iframe;
    
    @Step("Open shopper application page")
    public static ShopperApplicationPage open() {
        UITestLifecycle.get().getWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        UITestLifecycle.get().getWebDriver().manage().window().maximize();
        return PageFactory.newInstance(ShopperApplicationPage.class, "https://shipt.com/shopper-application/");
    }
	
    @Step("Switch to iframe")
    public ShopperApplicationiframePage switchToiframe() {
    	wait.until(visibilityOf(iframe));
    	driver.switchTo().frame(iframe);
    	return PageFactory.newInstance(ShopperApplicationiframePage.class);
    }
    
}