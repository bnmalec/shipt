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

import org.openqa.selenium.NoSuchElementException;

public class ShopperApplicationNamePage extends BasePage<ShopperApplicationNamePage> {
	// This page object is for the first and last name fields of the shopper application
	
	@Name("iframe")
	@FindBy(css = "iframe[data-qa='iframe']")
	private WebElement iframe;
	
	@Name("Progress and nav footer")
	@Visible
	private ProgressComponent progressComponent;
	
	@Name("OK button")
	@Invisible
	@FindBy(css = "button[data-qa='ok-button-visible deep-purple-ok-button-visible']")
	private Button okButton;
	
	@Name("Type your answer here box: first name")
	@Visible
	@FindBy(css = "input[id*='shortText']")
	private WebElement answerBox;
	
	@Name("Type your answer here box: last name")
	// find better way to locate element.  Tried to use the same id for both name fields 
	// but kept getting "could not scroll to" element error
	@FindBy(css = "#shortText-98e9c6640b35751a") 
	private WebElement lastNameAnswerBox;

    @Step("Click OK button on first name field")
	public ShopperApplicationNamePage clickOKButton() {
		wait.until(visibilityOf(okButton));
		okButton.click();
		return this;
	}
    
    @Step("Click OK button to move to email page")
	public ShopperApplicationEmailPage clickOKButtonAndMoveToEmail() {
		wait.until(visibilityOf(okButton));
		okButton.click();
		return PageFactory.newInstance(ShopperApplicationEmailPage.class);
	}
	
    @Step("Enter first name into text box")
	public ShopperApplicationNamePage enterFirstName(String text) {
    	wait.until(visibilityOf(answerBox));
		answerBox.sendKeys(text);
		return this;
	}
    
    @Step("Enter last name into text box")
    public ShopperApplicationNamePage enterLastName(String text) {
    	wait.until(visibilityOf(lastNameAnswerBox));
    	lastNameAnswerBox.sendKeys(text);
    	return this;
    }
    
    public ProgressComponent theProgressComponent() {
    	return progressComponent;
    }
	
	public WebElement getOKButton() {
		return okButton;
	}

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }
    
    public boolean isElementPresent(WebElement element) {
    	try {
    		return element.isDisplayed();
    	} catch (NoSuchElementException e) {
    		return false;
    	}
    }
}