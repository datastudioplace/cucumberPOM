package pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAccountPage extends BasePage{
	public int ran;
	public @FindBy(xpath = "//p[contains(text(),'Please enter your email address to create an accou')]") WebElement  text_Pleaseenteryouremailaddresstocreateanaccount;
	public @FindBy(xpath = "//input[@id='email_create']") WebElement textField_EmailaddressCreateAnAccount;
	public @FindBy(xpath = "//form[@id='create-account_form']//span[1]") WebElement button_CreateAnAccount;
	public @FindBy(xpath = "//input[@id='email']") WebElement textField_EmailaddressSignIn;
	public @FindBy(xpath = "//input[@id='passwd']") WebElement textField_PasswordSignIn;
	public @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]") WebElement linkText_ForgotYourpasswordSignIn;
	public @FindBy(xpath = "//p[@class='submit']//span[1]") WebElement button_SignIn_SignIn;
	public @FindBy(xpath = "//h1[@class='page-heading']") WebElement  text_AUTHENTICATION;
	public @FindBy(xpath = "//h3[contains(text(),'Create an account')]") WebElement  text_CREATEANACCOUNT;
	public @FindBy(xpath = "//h3[contains(text(),'Already registered?')]") WebElement  text_ALREADYREGISTERED;
	
	//RED Colour Error Messages Variables
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/ol[1]/li[1]") WebElement text_ErrorConfirmation;
	public @FindBy(xpath = "//li[contains(text(),'An email address required.')]") WebElement textField_BlankInputEmailAddress_CreateAnAccount;
	public @FindBy(xpath = "//li[contains(text(),'Invalid email address.')]") WebElement text_ErrorBadEmailCreateAnAccount;
	public @FindBy(xpath = "//li[contains(text(),'An account using this email address has already be')]") WebElement textField_EmailAddressHasAlreadyBeenRegistered;
	public @FindBy(xpath = "//li[contains(text(),'Invalid email address.')]") WebElement textField_InvalidEmailAddressSignIn;
	public @FindBy(xpath = "//li[contains(text(),'An email address required.')]") WebElement textField_BlankInputsForBoth_SignIn;
	public @FindBy(xpath = "//li[contains(text(),'An email address required.')]") WebElement textField_BlankInputsEmailAddress_SignIn;
	public @FindBy(xpath = "//li[contains(text(),'Password is required.')]") WebElement textField_BlankPasswordInput_SignIn;
	

	public MyAccountPage() throws IOException {
		super();
	}
	
	 public MyAccountPage userSignIn(String EMAIL, String PASSWORD) throws Exception {
		 myAccountPage.WaitUntilWebElementIsVisible(textField_EmailaddressSignIn);	        		 
		 myAccountPage.sendKeysToWebElement(textField_EmailaddressSignIn, EMAIL);
		 myAccountPage.WaitUntilWebElementIsVisible(textField_PasswordSignIn);
		 myAccountPage.sendKeysToWebElement(textField_PasswordSignIn, PASSWORD);
		 myAccountPage.waitAndclickElementUsingJS(button_SignIn_SignIn);
		 return new MyAccountPage();
	}
	public WebElement enterEmailaddressCreateAnAccount() throws IOException, InterruptedException {
		
		
		String email = System.currentTimeMillis()+"@naver.com";
		Thread.sleep(5000);
		basePage.scrollDownVertically();
		myAccountPage.scrollIntoView(textField_EmailaddressCreateAnAccount);
		myAccountPage.textField_EmailaddressCreateAnAccount.sendKeys("James"+email);
		System.out.println("This is the Generated and used email id: " + email);
		
		/* ran = 1000 + (int)(Math.random() * (1000 - 1) + 1);
		   System.out.println("This is the Generated and used email id: " + ran);
		   Thread.sleep(1000);
		   myAccountPage.textField_EmailaddressCreateAnAccount.sendKeys("James"+ran+"@naver.com");
		   System.out.println("This is the Generated and used email id: " + ran);
		   Thread.sleep(2000); */
				
		/* myAccountPage.WaitUntilWebElementIsVisible(textField_EmailaddressCreateAnAccount);
		myAccountPage.isDisplayed(textField_EmailaddressCreateAnAccount);
		myAccountPage.textField_EmailaddressCreateAnAccount.sendKeys(EMAIL); */
		return textField_EmailaddressCreateAnAccount; 
	}
    public WebElement enterInvalidCredentails_CreateAnAccount(String EMAIL) throws IOException, InterruptedException {
    	basePage.WaitUntilWebElementIsVisible(textField_EmailaddressCreateAnAccount);
    	basePage.scrollDownVertically();
    	myAccountPage.textField_EmailaddressCreateAnAccount.sendKeys(EMAIL);
    	String ActualInvalidEmailCredential = textField_EmailaddressCreateAnAccount.getText();
    	log.info("Asserting Invalid Email credential..........");
    	Assert.assertEquals(ActualInvalidEmailCredential,EMAIL);
    	log.info("Assertion PASSED............");
		return textField_EmailaddressCreateAnAccount; 
	}
	public WebElement clickButtonCreateAnAccount() throws InterruptedException, IOException {
		myAccountPage.isDisplayed(button_CreateAnAccount);
		myAccountPage.waitAndClickElement(button_CreateAnAccount);
		return button_CreateAnAccount;
	}
	public WebElement sendTextFieldEmailaddressSignIn(String EMAIL) throws Exception {
		myAccountPage.sendKeysToWebElement(textField_EmailaddressSignIn, EMAIL);
		return textField_EmailaddressSignIn;
	}
	
	public WebElement sendEmailaddressSignIn(String EMAIL) throws Exception {
		myAccountPage.sendKeysToWebElement(textField_EmailaddressSignIn, EMAIL);
		return textField_EmailaddressSignIn;
	}
	public WebElement enterTextField_PasswordSignIn(String PASSWORD) {
		myAccountPage.textField_PasswordSignIn.sendKeys(PASSWORD);
		return textField_PasswordSignIn;
	}
	public WebElement clickLinkTextForgotYourpasswordSignIn() {
		myAccountPage.linkText_ForgotYourpasswordSignIn.click();
		return linkText_ForgotYourpasswordSignIn;
	}
	public WebElement clickButtonSignIn_SignIn() throws IOException, InterruptedException {
		myAccountPage.button_SignIn_SignIn.click();
		return button_SignIn_SignIn;
	}
	public WebElement getAUTHENTICATIONText() {
		myAccountPage.text_AUTHENTICATION.isDisplayed();
		String textAUTHENTICATION = myAccountPage.text_AUTHENTICATION.getText();
		System.out.println( "User is displayed AUTHENTICATION text on MyAccount page: " + textAUTHENTICATION);
		return text_AUTHENTICATION;
	}
	public String getCREATEANACCOUNTText(String myACCOUNTPAGE_SECTION) {
		myAccountPage.text_CREATEANACCOUNT.isDisplayed();
		System.out.println( "User is displayed CREATE AN ACCOUNT text on MyAccount page: " + myAccountPage.text_CREATEANACCOUNT.getText());
		return myACCOUNTPAGE_SECTION;
	}
	public WebElement getALREADYREGISTEREDText() throws IOException, InterruptedException {
		myAccountPage.text_ALREADYREGISTERED.isDisplayed();
		System.out.println( "User is displayed ALREADY REGISTERED text on MyAccount page: " + myAccountPage.text_ALREADYREGISTERED.getText());
		return text_ALREADYREGISTERED;
	}
	public WebElement getPleaseenteryouremailaddresstocreateanaccountText() {
		myAccountPage.text_Pleaseenteryouremailaddresstocreateanaccount.isDisplayed();
		System.out.println( "User is displayed Please enter your emailaddress to create an account text on MyAccount page: " + myAccountPage.text_Pleaseenteryouremailaddresstocreateanaccount.getText());
		return text_Pleaseenteryouremailaddresstocreateanaccount; 
    }
	public String getErrorConfirmationMessageText_SignIn(String MESSAGE) {
		myAccountPage.WaitUntilWebElementIsVisible(text_ErrorConfirmation);
		String ErrorConfirmation = basePage.getText(text_ErrorConfirmation);
		Assert.assertEquals(ErrorConfirmation, MESSAGE);
		return ErrorConfirmation;
	}
	public String getMessageForBlankInputEmailAddress_CreateAccount(String EMAIL, String MESSAGE) throws Exception {
		myAccountPage.WaitUntilWebElementIsVisible(textField_EmailaddressCreateAnAccount);
		myAccountPage.sendKeysToWebElement(textField_EmailaddressCreateAnAccount, EMAIL);
		myAccountPage.WaitUntilWebElementIsVisible(textField_BlankInputEmailAddress_CreateAnAccount);
		String MessageForBlankInputEmailAddress = basePage.getText(textField_BlankInputEmailAddress_CreateAnAccount);
		Assert.assertEquals(MessageForBlankInputEmailAddress, MESSAGE);
		System.out.println("CONFIRMATION Message For Blank Input Email Address: " + MessageForBlankInputEmailAddress);
		return MessageForBlankInputEmailAddress;
	}
	public String getErrorMessageForBadEmailAddress_CreateAnAccount(String EMAIL, String MESSAGE) throws Exception {	
		myAccountPage.WaitUntilWebElementIsVisible(textField_EmailaddressCreateAnAccount);
		myAccountPage.sendKeysToWebElement(textField_EmailaddressCreateAnAccount, EMAIL);
		String getErrorBadEmailAddressCreateAnAccount = basePage.getText(textField_EmailaddressCreateAnAccount);
		Assert.assertEquals(getErrorBadEmailAddressCreateAnAccount, MESSAGE);
		return getErrorBadEmailAddressCreateAnAccount;
	}
    public String getErrorMessageForAlreadyRegisteredEmailAddress_CreateAnAccount() {
    	myAccountPage.WaitUntilWebElementIsVisible(textField_EmailaddressCreateAnAccount);
		String ErrorMessageForBadEmailAddressUsed = basePage.getText(textField_EmailaddressCreateAnAccount);
		Assert.assertEquals(ErrorMessageForBadEmailAddressUsed, "An account using this email address has already been registered. Please enter a valid password or request a new one.");
		return ErrorMessageForBadEmailAddressUsed;
	}
    public String getErrorMessageForBlankInputsForBothEmailAndPassword_SignIn() {
		myAccountPage.WaitUntilWebElementIsVisible(textField_BlankInputsForBoth_SignIn);
    	String ErrorMessageForBlankInputsForBothEmailAndPassword = myAccountPage.getText(textField_BlankInputsForBoth_SignIn);
    	Assert.assertEquals(ErrorMessageForBlankInputsForBothEmailAndPassword, "An email address required.");
		return ErrorMessageForBlankInputsForBothEmailAndPassword;
	}
	public String getErrorMessageForBlankEmailAddress_SignIn() throws IOException, InterruptedException {
		myAccountPage.WaitUntilWebElementIsVisible(textField_BlankInputsEmailAddress_SignIn);
		String ErrorMessageForBlankEmailAddressUsed = basePage.getText(textField_BlankInputsEmailAddress_SignIn);
		Assert.assertEquals(ErrorMessageForBlankEmailAddressUsed, "An email address required.");
		return ErrorMessageForBlankEmailAddressUsed;
	}
    public String getErrorMessageForBlankPassword_SignIn() throws IOException, InterruptedException {
    	myAccountPage.WaitUntilWebElementIsVisible(textField_BlankPasswordInput_SignIn);
		String ErrorMessageForBlankPasswordTextField = basePage.getText(textField_BlankPasswordInput_SignIn);
		Assert.assertEquals(ErrorMessageForBlankPasswordTextField, "Password is required.");
		return ErrorMessageForBlankPasswordTextField;
	}

}
