package shipt.pages;

import com.frameworkium.core.ui.annotations.Invisible;
import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import shipt.pages.components.ProgressComponent;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ShopperApplicationEmailPage extends BasePage<ShopperApplicationEmailPage> {
	// This page object is for the email field of the shopper application
	
	@Name("Progress and nav footer")
	@Visible
	private ProgressComponent progressComponent;
	
	@Name("OK button")
	@Invisible
	@FindBy(css = "button[data-qa='ok-button-visible deep-purple-ok-button-visible'")
	private Button okButton;
	
	@Name("Type your answer here box: email field")
	@Visible
	@FindBy(css = "input[id*='email']") 
	private WebElement answerBox;

	// Error message that displays if email is invalid
	@Name("Error message on email form")
	@Invisible
	@FindBy(css = "div[data-qa='error-message-visible']")
	private WebElement errorMessage;

    @Step("Click OK button")
	public ShopperApplicationNamePage clickOKButton() {
		wait.until(visibilityOf(okButton));
		okButton.click();
		return PageFactory.newInstance(ShopperApplicationNamePage.class);
	}
	
    @Step("Enter email into text box")
	public ShopperApplicationEmailPage enterEmailAddress(String email) {
		wait.until(visibilityOf(answerBox));
		answerBox.clear();
		answerBox.sendKeys(email);
		return this;
	}
    
    @Step("Try to submit invalid email addresses and verify error message")
    public ShopperApplicationEmailPage checkInvalidEmails(String email) {
    	enterEmailAddress(email);
    	okButton.click();
    	return this;
    }
    
    public ProgressComponent theProgressComponent() {
    	return progressComponent;
    }
	
	public WebElement getOKButton() {
		return okButton;
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
}