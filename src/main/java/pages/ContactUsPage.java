package pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BasePage {

	public ContactUsPage() throws IOException {
		super();
	}
	
	
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/h1[1]")
	WebElement ConfirmContactPage;

	public @FindBy(xpath = "//h3[@class='page-subheading']")
	WebElement text_SendAMessage;

	public @FindBy(xpath = "//select[@id='id_contact']")
	WebElement buttonDropdown_SubjectHeading;

	public @FindBy(xpath = "//input[@id='email']")
	WebElement textField_EmailAddress;

	public @FindBy(css = "#id_order")
	WebElement textField_OrderReference;

	public @FindBy(xpath = "#fileUpload")
	WebElement button_UploadFile;

	public @FindBy(xpath = "//textarea[@id='message']")
	WebElement textfield_Message;

	public @FindBy(xpath = "//span[contains(text(),'Send')]")
	WebElement button_Send;

	public @FindBy(xpath = "//li[contains(text(),'Bad file extension')]")
	WebElement text_BadFileExtensionUpLoadConfirmation;
	
	public @FindBy(xpath = "//*[@id='center_column']/p")
	WebElement text_sentMessageConfirmation;

	
	public ContactUsPage getConfirmationContactUsText() throws IOException, InterruptedException {
		basePage.isDisplayed(ConfirmContactPage);
		System.out.println("CONFIRMING ContactUs Page: " + ConfirmContactPage.getText());
		return new ContactUsPage();
	}
	public ContactUsPage CustomeServiceContactUs_text() throws IOException, InterruptedException {
		basePage.isDisplayed(ConfirmContactPage);
		return new ContactUsPage();
	}
	public ContactUsPage getSubjectHeading_DropdownButton() throws IOException, InterruptedException {
		Select dropdown = new Select(buttonDropdown_SubjectHeading);
		// select 1st option from the drop down options
		dropdown.selectByIndex(2);
		return new ContactUsPage();
	}
	public ContactUsPage enterEmailAddressTextfield(String arg1) throws Exception {
		basePage.WaitUntilWebElementIsVisible(textField_EmailAddress);
		basePage.sendKeysToWebElement(textField_EmailAddress, arg1);
		return new ContactUsPage();
	}

	public ContactUsPage enterOrderReferenceTextfield(String arg1) throws Exception {
		basePage.WaitUntilWebElementIsVisible(textField_OrderReference);
		basePage.isDisplayed(textField_OrderReference);
		contactUsPage.sendKeysToWebElement(textField_OrderReference, arg1);
		return new ContactUsPage();
	}	
	public ContactUsPage getMessageSentTextField(String arg1) throws Exception {
			textfield_Message.click();
			sendKeysToWebElement(textfield_Message, arg1);
			return new ContactUsPage();
	}
		public ContactUsPage clickButtonUploadFile() throws Exception {
			return new ContactUsPage();
	}
		
		public ContactUsPage clickSendButtonContactUs() throws Exception {
			contactUsPage.waitAndClickElement(button_Send);
			return new ContactUsPage();
	}

		public ContactUsPage getConfirmationMessageContactUs(String arg1) throws Exception {
			verifyTextContains(arg1, text_sentMessageConfirmation.getText());
			return new ContactUsPage();
	}
}
