package shipt.pages;

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

public class ShopperApplicationiframePage extends BasePage<ShopperApplicationiframePage> {
	// This page object is the iframe on https://www.shipt.com/shopper-application/
		
	@Name("Welcome to Shipt Shopper Application Message")
	@Visible 
	@FindBy(xpath = "//div[contains(.,'Welcome to the Shipt Shopper Application')]")
	private WebElement welcomeMessage;
	
	@Name("Start button")	  
	@Visible	  
	@FindBy(css = "button[data-qa='start-button']") 
	private Button startButton;
	
	@Name("Start by telling us a little bit about yourself!")
	@FindBy(css = "span[data-qa='block-title deep-purple-block-title']")
	private WebElement startMessage;
	
	// The progress complete indicator and navigation buttons
	@Name("Progress and nav footer")
	private ProgressComponent progressComponent;
	
	@Name("OK button")
	@FindBy(css = "button[data-qa='ok-button-visible deep-purple-ok-button-visible']")
	private Button okButton;
	
    @Step("Get start message")
    public String getStartMessage() {
    	wait.until(visibilityOf(startMessage));
    	String message = startMessage.getText();
    	return message;
    }
	
    // Clicks to start the application
	@Step("Click Start button") 
	public ShopperApplicationiframePage clickStartButton() { 
		startButton.click(); 
		return this; 
	}
    
    public ProgressComponent theProgressComponent() {
    	wait.until(visibilityOf(progressComponent));
    	return progressComponent;
    }

    // Clicks OK to go to first name field
    @Step("Click OK button")
	public ShopperApplicationNamePage clickOKButton() {
		wait.until(visibilityOf(okButton));
		okButton.click();
		return PageFactory.newInstance(ShopperApplicationNamePage.class);
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