package shipt.pages.components;

import com.frameworkium.core.ui.annotations.Visible;
import io.qameta.allure.Step;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.*;

@Name("iframe progress and navigation area")
@FindBy(css = "div[data-qa='fixed-footer']")
public class ProgressComponent extends HtmlElement {
	// The footer component with progress indicator and nav buttons
	
	@Visible
	@Name("Percent completed indicator")
	@FindBy(css = "div[data-qa='fixed-footer-progress-label']")
	private WebElement progressIndicator;

    @Visible
    @Name("Navigation button previous")
    @FindBy(css = "button[data-qa='fixed-footer-navigation-previous']")
    private Button previousNavigationButton;
    
    @Visible
    @Name("Navigation button next")
    @FindBy(css = "button[data-qa='fixed-footer-navigation-next']")
    private Button nextNavigationButton;
    
    @Step("Check progress indicator")
    public String getProgressIndicator() {
    	String progress = progressIndicator.getText();
    	return progress;
    }
}