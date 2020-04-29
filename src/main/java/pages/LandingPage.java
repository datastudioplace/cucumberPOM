package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    public @FindBy(xpath = "//div[@class='row']//nav//div//a[contains(text(),'Contact us')]")
    WebElement Button_ContactUs;
	public @FindBy(xpath = "//a[@class='login']")
	WebElement Button_SignIn;
	public @FindBy(name = "search_query")
	WebElement textField_Search;
	public @FindBy(name = "submit_search")
	WebElement button_Search;
	public @FindBy(css = "ide-left-column.hide-right-column.lang_en:nth-child(6) div.header-container div.container div.row div.col-sm-4.clearfix:nth-child(3) div.shopping_cart > a:nth-child(1)")
	WebElement Button_Cart;
	public @FindBy(xpath = "//a[@class='sf-with-ul'][contains(text(),'Women')]")
	WebElement linkText_Women;
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[6]/ul[1]/li[2]/a[1]")
	WebElement linkText_Dresses;
	public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[6]/ul[1]/li[3]/a[1]")
	WebElement linkText_TShirts;
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	@FindBy(xpath="//a[@class='sf-with-ul'][contains(text(),'Women')]")
	public WebElement dressesMenu;
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtsMenu;
	@FindBy(xpath="//*[@id='center_column']/div/div[1]/ul/li[1]/a/span")
	WebElement orderHistoryAndDetailsLink;
	@FindBy(xpath="//*[@id='center_column']/div/div[1]/ul/li[3]/a/span")
	WebElement myAddresses;
	@FindBy(xpath = "//li[@class='last']//a[contains(text(),'Women')]")
	WebElement Categories_WomenLink;
	@FindBy(xpath = "//a[contains(text(),'Specials')]")
	WebElement Information_SpecialsLink;
	@FindBy(xpath = "//h4[contains(text(),'Store information')]")
	WebElement Information_NewProductsLink;
	@FindBy(xpath = "//a[contains(text(),'New products')]")
	WebElement Information_BestSellersLink;
	@FindBy(xpath = "//a[contains(text(),'Best sellers')]")
	WebElement Information_OurStoresLink;
	@FindBy(xpath = "//li[@class='item']//a[contains(text(),'Contact us')]")
	WebElement Information_ContactUsLink;
	@FindBy(xpath = "//a[contains(text(),'Terms and conditions of use')]")
	WebElement Information_TermsandconditionsOfUseLink;
	@FindBy(xpath = "//a[contains(text(),'About us')]")
	WebElement Information_AboutUsLink;
	@FindBy(xpath = "//a[contains(text(),'Sitemap')]")
	WebElement Information_SitemapLink;
    @FindBy(xpath = "//a[contains(text(),'My personal info')]")
	WebElement NewsletterEnterYourEmailLink;
    @FindBy(id = "//a[contains(text(), \"Sign in\")]")
	WebElement HomePageSlider;
	@FindBy(id = "homefeatured')]")
	WebElement ContainerPopular;
	@FindBy(xpath = "htmlcontent_home")
	WebElement FooterBaners;
    @FindBy(xpath = "//a[contains(text(),'Sitemap')]")
	WebElement MyAaccountLink;
	@FindBy(xpath = "//a[contains(text(),'Sitemap')]")
	WebElement MyOrdersLink;
	@FindBy(xpath = "//a[contains(text(),'My credit slips')]")
	WebElement MyCreditSlipsLink;
	@FindBy(xpath = "//a[contains(text(),'My addresses')]")
	WebElement MyAddressesLInk;
	@FindBy(xpath = "//a[contains(text(),'My personal info')]")
	WebElement MyPersonalInfo;
    @FindBy(xpath = "//a[contains(text(),'My personal info')]")
	WebElement emailLink;
	
	//Constructor//
	public LandingPage() throws IOException {
		super();
	}
	public LandingPage getUrlLandingPage() throws IOException {
		getDriver().get(p.getProperty("Url"));
		return new LandingPage();
	}
	public LandingPage getUrlLandingPage(String Url) throws IOException {
		getDriver().get(p.getProperty("Url"));
		return new LandingPage();
	}
	public LandingPage getCucumberlandingPage(String URL) throws IOException {
		getDriver().get(URL);
		return new LandingPage();
	}
	public String clickContactUsButton() throws IOException, InterruptedException {
		basePage.waitAndClickElement(Button_ContactUs);
			return Button_ContactUs.getText();
	}
	public LandingPage clickSignInButtonInLandingPage() throws IOException, InterruptedException {
		landingPage.waitAndClickElement(Button_SignIn);
		return new LandingPage();
	}
	public LandingPage clickSearchButton(String KEYWORD) throws Exception {
		basePage.waitAndClickElement(textField_Search);
		basePage.sendKeysToWebElement(textField_Search, KEYWORD);
		basePage.waitAndClickElement(button_Search);
		return new LandingPage();
	}
	public void mouseOver(String data){
		log.info("doing mouse over on :"+data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
	}
	public ProductCategoryPage clickOnIntem(String data) throws IOException{
		log.info("clickin on :"+data);
		driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
		return new ProductCategoryPage();
	}
	public LandingPage clickOnMenu(WebElement element) throws IOException{
		log.info("clickin on : "+element.getText());
		element.click();
		return new LandingPage();
	}
	
    public void gotoWomenDropdownButton(){
	       linkText_Women.click();
		
	}
	public void clickDressesButton() throws IOException, InterruptedException {
		basePage.waitAndClickElement(linkText_Dresses);
	}
	public void clickTShirtsButton() throws IOException, InterruptedException {
		basePage.waitAndClickElement(linkText_TShirts);
	}
	public void navigateToButton_ContactUs() throws IOException {
		landingPage.Button_ContactUs.click();
		
	}
	public LandingPage clicklandingPageButton_SignIn() throws IOException {
		landingPage.Button_SignIn.click();
		return new LandingPage();
	}
	public LandingPage sendToTextField_Search(String arg1) throws IOException {
		landingPage.click_Button_Search().sendToTextField_Search(arg1);
		return new LandingPage();
	}
	public LandingPage click_Button_Search() throws IOException {
		landingPage.button_Search.click();
		return new LandingPage();
	}
	public LandingPage clickButton_Cart() throws IOException {
		landingPage.Button_Cart.click();
		return new LandingPage();
	}
	public LandingPage navigateToLinkText_Women() throws IOException {
		landingPage.linkText_Women.click();
		return new LandingPage();
	}
	public LandingPage navigateToLinkText_Dresses() throws IOException {
		landingPage.linkText_Dresses.click();
		return new LandingPage();
	}
	public LandingPage navigateToLinkText_TShirts() throws IOException {
		landingPage.linkText_TShirts.click();
		return new LandingPage();
	}
	public LandingPage navigateToCategories_WomenLink() throws IOException {
		landingPage.Categories_WomenLink.click();
		return new LandingPage();
	}
	public LandingPage getInformation_SpecialsLink() throws IOException {
		landingPage.Information_SpecialsLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToInformation_NewproductsLink() throws IOException {
		landingPage.Information_NewProductsLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToInformation_BestSellersLink() throws IOException {
		landingPage.Information_BestSellersLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToInformation_OurStoresLink() throws IOException {
		landingPage.Information_OurStoresLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToInformation_ContactUsLink() throws IOException {
		landingPage.Information_ContactUsLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToInformation_TermsandconditionsOfUseLink() throws IOException {
		landingPage.Information_TermsandconditionsOfUseLink.click();
		return new LandingPage();
	}
	public LandingPage getInformation_AboutUsLink() throws IOException {
		landingPage.Information_AboutUsLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToInformation_SitemapLink() throws IOException {
		landingPage.Information_SitemapLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToMyAaccountLink() throws IOException {
		landingPage.MyAaccountLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToMyOrdersLink() throws IOException {
		landingPage.MyOrdersLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToMyCreditSlipsLink() throws IOException {
		landingPage.MyCreditSlipsLink.click();
		return new LandingPage();
	}
	public LandingPage navigateToMyAddressesLInk() throws IOException {
		landingPage.MyAddressesLInk.click();
		return new LandingPage();
	}
	public LandingPage navigateToMyPersonalInfo() throws IOException {
		landingPage.MyPersonalInfo.click();
		return new LandingPage();
	}
	public LandingPage navigateToEmailLink() throws IOException {
		landingPage.emailLink.click();
		return new LandingPage();
	}
	public String NewsletterEnterYourEmailLink() {

		return NewsletterEnterYourEmailLink();
	}

	// HomePageSlider// ContainerPopular//FooterBaners//

	public LandingPage navigateToHomePageSlider() throws IOException {
		landingPage.HomePageSlider.isDisplayed();
		landingPage.HomePageSlider.click();
		return new LandingPage();
	}
	public LandingPage navigateToContainerPopular() throws IOException {
		landingPage.ContainerPopular.isDisplayed();
		landingPage.ContainerPopular.click();
		return new LandingPage();
	}
	public LandingPage navigateToFooterBaners() throws IOException {
		landingPage.FooterBaners.isDisplayed();
		landingPage.FooterBaners.click();
		return new LandingPage();
	}

}
