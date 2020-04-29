package pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPasswordPage extends BasePage {

	public @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]") WebElement text_FORGOTYOURPASSWORD;
	public @FindBy(xpath = "//p[contains(text(),'Please enter the email address you used to registe')]") WebElement text_EmailAddressYouUsedToRegister;
	public @FindBy(css = "#email") WebElement textField_EnterEmailAddressToRetrievePassword;
	public @FindBy(xpath = "//span[contains(text(),'Retrieve Password')]") WebElement button_RetrievePassword;
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/p[1]") WebElement text_ConfirmEmailSentRetrievePassword;
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[1]/a[1]/span[1]") WebElement button_BackToLoginPage;
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/ol[1]/li[1]") WebElement textErrorMessage_BadEmail_BlankSpace;


	public ForgetPasswordPage() throws IOException {
		super();

	}

	public WebElement getText_FORGOTYOURPASSWORD() {
		forgetPasswordPage.WaitUntilWebElementIsVisible(text_FORGOTYOURPASSWORD);
		forgetPasswordPage.text_FORGOTYOURPASSWORD.isDisplayed();
		return text_FORGOTYOURPASSWORD;
	}
	
	public WebElement gettext_EmailAddressYouUsedToRegister() {
		
		String expText = forgetPasswordPage.text_EmailAddressYouUsedToRegister.getText();
		System.out.println("CONFIRMATION EmailAddressYouUsedToRegister TEXT:  " + expText);
		String C_URL = forgetPasswordPage.getCurrentURL();
		System.out.println("CURRENT URL:  " + C_URL);
		return text_EmailAddressYouUsedToRegister;
		
		
	}

	public WebElement enterEmailAddressToRetrievePassword(String arg1) {
		forgetPasswordPage.textField_EnterEmailAddressToRetrievePassword.sendKeys(arg1);
		 return textField_EnterEmailAddressToRetrievePassword;
	}

	public WebElement clickButton_RetrievePassword() {
		basePage.WaitUntilWebElementIsVisible(button_RetrievePassword);
		return button_RetrievePassword;

	}

	public WebElement getText_ConfirmEmailSentRetrievePassword() {
		String ExpconfirmationText = forgetPasswordPage.getText(text_ConfirmEmailSentRetrievePassword);
		System.out.println("CONFIRMATION TEXT:  " + ExpconfirmationText);
		return text_ConfirmEmailSentRetrievePassword;
	}

	public WebElement clickButton_BackToLoginPage() throws InterruptedException, IOException  {
		forgetPasswordPage.waitAndClickElement(button_BackToLoginPage);
		return button_BackToLoginPage;
	}
	
	public WebElement getText_textErrorMessage_BadEmail_BlankSpace(String arg1) {
		forgetPasswordPage.textField_EnterEmailAddressToRetrievePassword.sendKeys(arg1);
		return textErrorMessage_BadEmail_BlankSpace;
	}

}
