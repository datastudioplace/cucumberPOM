package pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateAnAccountPage extends BasePage{
	
	public CreateAnAccountPage() throws IOException {
		super();

	}
	
	// User can sees text as "CREATE AN ACCOUNT"//
	public @FindBy(xpath = "//h1[@class='page-heading']") WebElement text_CREATEANACCOUNT;
	
	// YOUR PERSONAL INFORMATION//
	public @FindBy(xpath = "//h3[contains(text(),'Your personal information')]") WebElement text_YourPersonalInformationSection;
	public @FindBy(id = "id_gender1") WebElement radioButton_MrTitle;
	public @FindBy(xpath = "//div[@class='clearfix']//div[2]//label[1]") WebElement radioButton_TitleMrs;
	public @FindBy(css = "#customer_firstname") WebElement textfield_FirstNamePersonalInfo;
	public @FindBy(css = "#customer_lastname") WebElement textfield_LastNamePersonalInfo;
	public @FindBy(xpath = "//label[contains(text(),'Email')]") WebElement textfield_Emailconfirm;
	public @FindBy(xpath = "//input[@id='passwd']") WebElement textfield_Password;
	public @FindBy(xpath = "//select[@id='days']//option[contains(text(),'18')]") WebElement DropBox_DayDOB;
	public @FindBy(xpath = "//option[contains(text(),'September')]") WebElement DropBox_MonthDOB;
	public @FindBy(xpath = "//option[contains(text(),'1975')]") WebElement DropBox_YearDOB;
	public @FindBy(id = "uniform-newsletter") WebElement TickButton_SignupNewsletter;
	public @FindBy(xpath = "//input[@id='optin']") WebElement TickButton_ReceiveSpecialOffers;

	// YOUR ADDRESS//

	public @FindBy(xpath = "//h3[contains(text(),'Your address')]") WebElement text_YourAddressSection;
	public @FindBy(xpath = "//input[@id='firstname']") WebElement textfield_FirstNameYourAddress;
	public @FindBy(xpath = "//input[@id='lastname']") WebElement textfield_LastNameYourAddress;
	public @FindBy(xpath = "//input[@id='company']") WebElement textfield_CompanyName;
	public @FindBy(xpath = "//input[@id='address1']") WebElement textfield_Address;
	public @FindBy(css = "#address2") WebElement textfield_Address2;
	public @FindBy(xpath = "//input[@id='city']") WebElement textfield_City;
	public @FindBy(name = "id_state") WebElement DropBox_State;
	public @FindBy(xpath = "//input[@id='postcode']") WebElement textfield_ZipPostalCode;
	public @FindBy(name = "id_country") WebElement DropBox_Country;
	public @FindBy(name = "other") WebElement textfield_AdditionalInfo;
	public @FindBy(xpath = "//input[@id='phone']") WebElement textfield_HomePhone;
	public @FindBy(id = "phone_mobile") WebElement textfield_MobilePhone;
	public @FindBy(xpath = "//input[@id='alias']") WebElement textfield_AssignAddressAlias;
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/div[4]/button[1]/span[1]") WebElement Button_Register;
	

	// ****** RED TEXT HINT WARNING MESSAGES ******//
	
	public @FindBy(xpath = "//span[contains(text(),'Street address, P.O. Box, Company name, etc.')]") WebElement text_StreetAddressPOBoxCompanyNameetc;
	public @FindBy(xpath = "//p[@class='inline-infos']") WebElement textRED_YouMustRegisterAtLeastOnePhoneNumber;

	// ******RED ERROR WARNING MESSAGES ******//
	
	
	// MEDTHODS
	public CreateAnAccountPage clickTitleMr() throws Exception {
		Thread.sleep(3000);

		int count = driver.findElements(By.name("id_gender")).size();
		WebElement attr = driver.findElements(By.name("id_gender")).get(0);
		System.out.println("Size of Radio buttons:" + count);
		System.out.println("Uging get():" + attr);
		for (int i = 0; i < count; i++) 
		    {
			driver.findElements(By.name("id_gender")).get(0).click();
			}
			   return new CreateAnAccountPage();
			
	  }
		
		/*
		 * Thread.sleep(3000); WebElement RadioMr =
		 * driver.findElement(By.id("id_gender1")); Thread.sleep(3000);
		 * System.out.println(RadioMr.isDisplayed());
		 * System.out.println(RadioMr.isEnabled());
		 * System.out.println("Before selecting radio button , the status is:" +
		 * RadioMr.isSelected()); Thread.sleep(10000); RadioMr.click();
		 * System.out.println("After selecting radio button , the status is:" +
		 * RadioMr.isSelected()); // true
		 */

	
	public CreateAnAccountPage getContactUsPage(String Url) throws IOException {
		getDriver().get(Url);
		return new CreateAnAccountPage();
	}
	public CreateAnAccountPage getCreateAnAccountText_createAnAccount(String arg1) throws Exception {
		createAnAccountPage.WaitUntilWebElementIsVisible(text_CREATEANACCOUNT);
		String CreateAnAccountText = createAnAccountPage.text_CREATEANACCOUNT.getText();
		Assert.assertEquals(CreateAnAccountText,arg1);
		System.out.println("CONFIRMATION Create An Account Text : " + CreateAnAccountText);
		return new CreateAnAccountPage();
	}
			   
	public CreateAnAccountPage enterFName(String Url) throws Exception {
		createAnAccountPage.sendKeysToWebElement(textfield_FirstNamePersonalInfo, Url);
		return new CreateAnAccountPage();
	}
	
	public CreateAnAccountPage enterLName(String Url) throws Exception {
		createAnAccountPage.sendKeysToWebElement(textfield_LastNamePersonalInfo, Url);
		return new CreateAnAccountPage();
	}
	
	public CreateAnAccountPage enterPassword(String Url) throws Exception {
		createAnAccountPage.sendKeysToWebElement(textfield_Password, Url);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage getconfirmEmailaddress(String arg1) throws Exception {
		createAnAccountPage.textfield_Emailconfirm.isDisplayed();
		return new CreateAnAccountPage();
	}
	
	public CreateAnAccountPage gettext_YourPersonalInformationSection(String arg1) throws Exception {
		String SectionText1 = createAnAccountPage.text_YourPersonalInformationSection.getText();
		System.out.println("YourPersonalInformationSection: " + SectionText1);
        Assert.assertTrue(SectionText1.contains(arg1));
		return new CreateAnAccountPage();
	}
	
	public CreateAnAccountPage gettext_YourAddressSection(String arg1) throws Exception {
		String SectionText2 = createAnAccountPage.text_YourAddressSection.getText();
		System.out.println("YourPersonalInformationSection: " + SectionText2);
        Assert.assertTrue(SectionText2.contains(arg1));
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterDayDOB(String arg1) throws Exception {

		new Select(driver.findElement(By.xpath("//select[@id='days']"))).selectByValue(arg1);
		return new CreateAnAccountPage();
	}
	
	public CreateAnAccountPage enterMonthDOB(String arg1) throws Exception {
		// basePage.WaitUntilWebElementIsVisible(DropBox_MonthDOB);
		// DropBox_MonthDOB.click();
		// new
		// Select(driver.findElement(By.xpath("//select[@id='days']"))).selectByValue(arg1);

		// new
		// Select(driver.findElement(By.xpath("//select[@id='months']"))).selectByValue(arg1);
		new Select(driver.findElement(By.xpath("//select[@id='months']"))).selectByVisibleText(arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterYearDOB(String arg1) throws Exception {

		new Select(driver.findElement(By.xpath("//select[@id='years']"))).selectByValue(arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage clickSignupNewsletterCheckbox() throws Exception {
		WaitUntilWebElementIsVisible(TickButton_SignupNewsletter);
		TickButton_SignupNewsletter.click();
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage clickSignupReceiveSpecialOffersCheckbox() throws Exception {
		WaitUntilWebElementIsVisible(TickButton_SignupNewsletter);
		TickButton_ReceiveSpecialOffers.click();
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterCompanyName(String arg1) throws Exception {
		createAnAccountPage.textfield_CompanyName.isDisplayed();
		basePage.sendKeysToWebElement(textfield_CompanyName, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterFirstAddress(String arg1) throws Exception {
		createAnAccountPage.textfield_Address.isDisplayed();
		basePage.sendKeysToWebElement(textfield_Address, arg1);
		return new CreateAnAccountPage();
	}
	
	public CreateAnAccountPage getConfirmationAddress_RedWarningAddresstext(String arg1) throws Exception {
		createAnAccountPage.WaitUntilWebElementIsVisible(text_StreetAddressPOBoxCompanyNameetc);
		createAnAccountPage.text_StreetAddressPOBoxCompanyNameetc.isDisplayed();
		String StreetAddressPOBoxCompanyNameetc = createAnAccountPage.text_StreetAddressPOBoxCompanyNameetc.getText();
		Assert.assertEquals(StreetAddressPOBoxCompanyNameetc, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterSecondAddress(String arg1) throws Exception {
		createAnAccountPage.textfield_Address2.isDisplayed();
		basePage.sendKeysToWebElement(textfield_Address2, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterCityName(String arg1) throws Exception {
		basePage.sendKeysToWebElement(textfield_City, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage selectStateDropBox(String arg1) throws Exception {
		Select drpCountry = new Select(DropBox_State);
		drpCountry.selectByVisibleText(arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterZiporPostCode(String arg1) throws Exception {
		basePage.sendKeysToWebElement(textfield_ZipPostalCode, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage selectCountryDropBox(String arg1) throws Exception {

		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterHomePhoneNumber(String arg1) throws Exception {
		basePage.sendKeysToWebElement(textfield_HomePhone, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterMobilePhoneNumber(String arg1) throws Exception {
		basePage.sendKeysToWebElement(textfield_MobilePhone, arg1);
		return new CreateAnAccountPage();
		
	}
	
	public CreateAnAccountPage getConfirmationAddress_RedWarningtextRED_YouMustRegisterAtLeastOnePhoneNumberText(String arg1) throws Exception {
		createAnAccountPage.WaitUntilWebElementIsVisible(textRED_YouMustRegisterAtLeastOnePhoneNumber);
		createAnAccountPage.text_StreetAddressPOBoxCompanyNameetc.isDisplayed();
		String YouMustRegisterAtLeastOnePhoneNumber = createAnAccountPage.textRED_YouMustRegisterAtLeastOnePhoneNumber.getText();
		Assert.assertEquals(YouMustRegisterAtLeastOnePhoneNumber, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterAdditionalInfo(String arg1) throws Exception {
		basePage.sendKeysToWebElement(textfield_AdditionalInfo, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage enterAliasAddressRef(String arg1) throws Exception {
		basePage.sendKeysToWebElement(textfield_AssignAddressAlias, arg1);
		return new CreateAnAccountPage();
	}

	public CreateAnAccountPage clickRegisterButton() throws Exception {
		basePage.waitAndClickElement(Button_Register);
		return new CreateAnAccountPage();
	}

	public HomePage getRegisterConfirmationMessage() throws Exception {
		homePage.text_WelcomeTextMessage.isDisplayed();
		homePage.getMyAccountButtonName();
		return new HomePage();
	}

	public HomePage clicksHomepageSignOutButtion() throws Exception {
		homePage.clickSignOutButton();
		return new HomePage();
	}
	
	/* SoftAssert SA = new SoftAssert();
	// compare the titles
	SA.assertEquals(driver.getTitle(), "Google", "title is not google");
	//try to print the statement
	System.out.println("this step should execute");
	System.out.println("this step also gets executed");
	// execute all the statement before below step
	SA.assertAll();
	// below step will not be executed
	System.out.println("This step will not be executed"); */
}
