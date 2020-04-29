package pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartSummaryPage extends BasePage{
	
	public ShoppingCartSummaryPage() throws IOException {
		super();

	}

	@FindBy(xpath="//*[@id='columns']/div[1]/span[2]")
	WebElement yourShoppingCart;
	
	@FindBy(xpath="//*[@id='columns']/div[1]/span[2]")
	List<WebElement> quantity_delete;
	
	@FindBy(xpath="//*[contains(text(),'Your shopping cart is empty')]")
	WebElement emptyShoppingCartMsg;
	
	public void ShoppinCartSummaryPage() {
	
	}
	
	public void verifyProduct(String prod){
		log.info("selecting product.."+prod);
		

	}
	
	public void delectProductFromShoppingCart() throws InterruptedException {
		// Delete all items from cart
		log.info("Deleting all products from cart..");
		List<WebElement> deletes = quantity_delete;
		Iterator<WebElement> itr = deletes.iterator();
		while (itr.hasNext()) {
			itr.next().click();
			Thread.sleep(2000);
		}
	}
	
	public boolean verifyEmptyShoppingCartMesssage(){
		try {
			log.info("verifying deleted Shopping Cart Messsage..");
			emptyShoppingCartMsg.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
