package pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	String Tshirts = "T-shirts";
	String Blouses = "Blouses";
	String CasualDresses = "Casual Dresses";

	public HomePage() throws IOException {
		super();
	}
	

	
	public @FindBy(xpath = "//a[@class='account']/span")
	WebElement Text_VerifyUserSuscessfullyLoggedIn;
	
	public @FindBy(xpath = "//div[@id='contact-link']//a[contains(text(),'Contact us')]")
	WebElement button_ContactUs_link;
	
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[3]/div[1]/a[1]")
	WebElement button_Cart_link;
	
	public @FindBy(className = "logout")
	WebElement button_SignOut;
	
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[2]/div[1]/div[1]/nav[1]/div[1]/a[1]/span[1]")
	WebElement button_NameVewMyAccout_link;
	
	public @FindBy(xpath = "//h1[@class='page-heading']")
	WebElement text_MYACCOUNT;
	
	public @FindBy(xpath = "//p[@class='info-account']")
	WebElement text_WelcomeTextMessage;
	
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]/span[1]")
	WebElement OrderHistoryDetails_link;
	
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/ul[1]/li[2]/a[1]/span[1]")
	WebElement MyCreditSlips_link;
	
	public @FindBy(xpath = "//span[contains(text(),'My addresses')]")
	WebElement MyAdresses_link;
	
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/ul[1]/li[4]/a[1]/span[1]")
	WebElement MyPersonalInfo_link;
	

	
    public String getVerifyUserSuscessfullyLoggedIn() {
        String text = Text_VerifyUserSuscessfullyLoggedIn.getText();
        return text;
    }
	
	public HomePage clickContactUs_link() throws IOException, InterruptedException {
		basePage.isDisplayed(button_ContactUs_link);
		System.out.println("Account Holder name: " + basePage.getText(text_MYACCOUNT));
		return new HomePage();
	}
	
	public HomePage getMYACCOUNT_text() throws IOException, InterruptedException {
		basePage.isDisplayed(text_MYACCOUNT);
		System.out.println("Account Holder name: " + basePage.getText(text_MYACCOUNT));
		return new HomePage();
	}

	public HomePage clickSignOutButton() throws Exception {
		homePage.isDisplayed(button_SignOut);
		homePage.waitAndClickElement(button_SignOut);
		System.out.println("SignOut Button Name: " + getText(button_SignOut));
		return new HomePage();
	}

	public String getMyAccountButtonName() throws Exception {
		basePage.isDisplayed(button_NameVewMyAccout_link);
		String AccHodersName = text_MYACCOUNT.getText();
		System.out.println("Acc text: " + AccHodersName);
		return AccHodersName;
	}
	
	public HomePage clickCartButton() throws Exception {
		basePage.isDisplayed(button_Cart_link);
		basePage.waitAndClickElement(button_Cart_link);
		return new HomePage();
	}

	public HomePage getWecomeMessageText(String MESSAGE) throws Exception {
		basePage.isDisplayed(text_WelcomeTextMessage);
		String WelcomeMessage = text_WelcomeTextMessage.getText();
		Assert.assertEquals(WelcomeMessage, MESSAGE);
		System.out.println("Welcome Text: " + basePage.getText(text_WelcomeTextMessage));
		return new HomePage();
	}

	public String getOrderHistoryMessageTextLink() throws Exception {
		text_WelcomeTextMessage.isDisplayed();
		String OrderHistoryMessageText = OrderHistoryDetails_link.getText();
		System.out.println("Welcome Text: " + OrderHistoryMessageText);
		Assert.assertEquals(OrderHistoryMessageText, "ORDER HISTORY AND DETAILS");
		return OrderHistoryMessageText;
	}

	public String getCreditSlipsTextLink() throws Exception {
		basePage.isDisplayed(MyCreditSlips_link);
		String CreditSlipsText = MyCreditSlips_link.getText();
		System.out.println("CreditSlips Text: " + CreditSlipsText);
		return CreditSlipsText;
	}

	public String getMyAdressesTextLink() throws Exception {
		basePage.isDisplayed(MyAdresses_link);
		String AddressText = MyAdresses_link.getText();
		System.out.println("Address Text: " + AddressText);
		Assert.assertEquals(AddressText, "MY ADDRESSES");
		return AddressText;
	}

	public String getMyPersonalInfoText() throws Exception {
		basePage.isDisplayed(MyPersonalInfo_link);
		String PersonalInfoText = MyPersonalInfo_link.getText();
		Assert.assertEquals(PersonalInfoText, "MY PERSONAL INFORMATION");
		System.out.println("PersonalInfo Text: " + PersonalInfoText);
		return PersonalInfoText;
	}
}
