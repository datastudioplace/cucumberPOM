package pages;


import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class ProductCategoryPage extends BasePage {
	
	public ProductCategoryPage() throws IOException {
		super();
	}

	  // Colour Filter CheckButtons
	public String Black = "Black";
	public String Orange = "Orange";
	public String Yellow = "Yellow";
	@FindBy(xpath="//*[@id='ul_layered_id_attribute_group_3']/li[4]/label/a")
	WebElement OrangecheckBoxFilter;
	@FindBy(xpath="//*[@id='ul_layered_id_attribute_group_3']/li[3]/label/a")
	WebElement BlackcheckBoxFilter;
	@FindBy(xpath="//*[@id='ul_layered_id_attribute_group_3']/li[7]/label/a")
	WebElement YellowcheckBoxFilter;
	
	
	@FindBy(xpath="//*[@id='layered_block_left']/p")
	WebElement catalogTextObj;
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[1]/h2")
	WebElement productAddedSucessfully;
	@FindBy(xpath="//*[@id='center_column']/ul/li[4]/div/div[2]/div[2]/a[1]/span")
	WebElement addToCart;
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	WebElement proceedToCheckOut;
	@FindBy(xpath="//*[@id='center_column']/ul/li")
	List<WebElement> totalProducts;

	public void mouseOverOnProduct(int number){
		String fPart = "//*[@id='center_column']/ul/li[";
		String sPart = "]/div/div[2]/h5/a";
		Actions action = new Actions(driver);
		log.info("doing mouse over on: "+number+"..product");
		action.moveToElement(driver.findElement(By.xpath(fPart+number+sPart))).build().perform();
	}
	
	public void clickOnAddToCart(){
		log.info("clickin on add to cart");
		addToCart.click();
	}
	
	public boolean verifyPoductAddedSuccesfully(){
		return verifyElementPresent(productAddedSucessfully);
	}
	
	public void clickOnProceedTocheckOut(){
		log.info("clickin on :"+proceedToCheckOut.getText());
		proceedToCheckOut.click();
	}
	
	public void selectColor(String data) throws IOException{
		new JavaScriptHelper(getDriver()).scrollIntoView(driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")));
		driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public ProductCategoryPage clickOrangeColorFilter() throws InterruptedException, IOException {
		log.info("clicking Orange Color...........");
		basePage.WaitUntilWebElementIsVisible(OrangecheckBoxFilter);
		basePage.scrollDownVertically();
		basePage.waitAndClickElement(OrangecheckBoxFilter);
		log.info("selected Orange Color...........");
		return new ProductCategoryPage();
	}
	public ProductCategoryPage clickBlackColorFilter() throws InterruptedException, IOException {
		log.info("clicking Black Color...........");
		basePage.waitAndClickElement(BlackcheckBoxFilter);
		log.info("selected Black Color...........");
		return new ProductCategoryPage();
	}
	public ProductCategoryPage clickYellowColorFilter() throws InterruptedException, IOException {
		log.info("clicking Yellow Color...........");
		Assert.assertTrue(YellowcheckBoxFilter.isDisplayed());
		basePage.waitAndClickElement(YellowcheckBoxFilter);
		Assert.assertTrue(YellowcheckBoxFilter.isSelected());
		log.info("selected Yellow Color...........");
		return new ProductCategoryPage();
	}
	public void selectSmallSize() {
		log.info("selecting small size..");
		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
	}
	
	public void selectMediumSize() {
		log.info("selecting Medium size..");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).click();
				log.info("checkbox is checked..");
			}
		} catch (Exception e) {
			log.info("checkbox was already checked..");
		}
	}

	public void selectLSize() {
		log.info("selecting Large size..");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();
				log.info("checkbox is checked..");
			}
		} catch (Exception e) {
			log.info("checkbox was already checked..");
		}
	}
	
	public void selectFirstProduct() {
		Actions obj = new Actions(driver);
		log.info("performning mouse over on first product of page..");
		obj.moveToElement(driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).get(0)).build().perform();
		log.info("clicking on add to basket..");
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
	}
	
	public int getTotalProducts(){
		return totalProducts.size();
	}
}
