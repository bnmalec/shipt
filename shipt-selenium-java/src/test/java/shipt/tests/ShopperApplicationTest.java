package shipt.tests;

import shipt.pages.HomePage;
import shipt.pages.ShopperApplicationEmailPage;
import shipt.pages.ShopperApplicationNamePage;
import shipt.pages.ShopperApplicationPage;

import com.frameworkium.core.ui.tests.BaseUITest;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Feature("Shopper Application")
@Test
public class ShopperApplicationTest extends BaseUITest {

    @Story("Check progress indicator starts at 0% completed")
    public void checkProgressIndicatorAfterStartingApplication() {
    	
    	ShopperApplicationPage shopperApplication =
                HomePage.open().then().clickGetPaidToShopLink().then().clickApplyNowButton();
    	String progress = shopperApplication.switchToiframe()
    										.then().clickStartButton()
    										.then().with().theProgressComponent().getProgressIndicator(); 
    	assertThat("0% completed").isEqualTo(progress);
    }
    
    @Story("Check for start message at the beginning of the shopper application")
    public void checkForStartMessage() {
    	
    	String startMessage = 
    			HomePage.open()
    					.then().clickGetPaidToShopLink()
    					.then().clickApplyNowButton()
    					.then().switchToiframe()
    					.then().clickStartButton()
    					.then().getStartMessage();

    	assertThat("Start by telling us a little bit about yourself!").isEqualTo(startMessage);
    }
    
    @Story("Check OK button displays only when user enters text in the first name field")
    public void checkOKButtonDisplaysWhenUserEntersText() {
    	
    	ShopperApplicationNamePage shopperApplication =
                HomePage.open()
                		.then().clickGetPaidToShopLink()
                		.then().clickApplyNowButton()
                		.then().switchToiframe()
                		.then().clickStartButton()
                		.then().clickOKButton();
    	assertThat(shopperApplication.isElementPresent(shopperApplication.getOKButton())).isFalse();
    	
    	shopperApplication.enterFirstName("Mahatma");
    	assertThat(shopperApplication.isElementPresent(shopperApplication.getOKButton())).isTrue();
    }

    @Story("Check user can enter full name and see progress indicator update")
    public void checkUserCanEnterFullNameAndSeeProgress() {
    	
    	ShopperApplicationNamePage shopperApplication =
                HomePage.open()
                		.then().clickGetPaidToShopLink()
                		.then().clickApplyNowButton()
                		.then().switchToiframe()
                		.then().clickStartButton()
                		.then().clickOKButton()
    					.then().enterFirstName("Mahatma")
    					.then().clickOKButton();
    	String progressOnLastName = shopperApplication.with().theProgressComponent().getProgressIndicator();
    	assertThat("6% completed").isEqualTo(progressOnLastName);
    	
    	String progressOnEmail = 
    			shopperApplication.enterLastName("Gandhi")
    							  .then().clickOKButtonAndMoveToEmail()
    							  .then().with().theProgressComponent().getProgressIndicator();
    	assertThat("11% completed").isEqualTo(progressOnEmail);
    }
    
    @Story("Check that invalid emails won't be allowed")
    public void checkInvalidEmailAddresses() {
    	
    	ShopperApplicationEmailPage shopperApplication = enterFirstAndLastName();
    	try (Stream<String> stream = Files.lines(Paths.get("src/test/resources/invalidemails.txt"))) {
    	    stream.forEach(e->shopperApplication.checkInvalidEmails(e));
        	assertThat("Hmm…that email doesn't look valid").isEqualTo(shopperApplication.getErrorMessage().toString());
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private ShopperApplicationEmailPage enterFirstAndLastName() {
    	ShopperApplicationEmailPage shopperApplication =
                ShopperApplicationPage.open()
                		.then().switchToiframe()
                		.then().clickStartButton()
                		.then().clickOKButton()
    					.then().enterFirstName("Mahatma")
    					.then().clickOKButton()
    					.then().enterLastName("Gandhi")
    					.then().clickOKButtonAndMoveToEmail();
    	return shopperApplication;
    }
}