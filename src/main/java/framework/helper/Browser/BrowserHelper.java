
package framework.helper.Browser;

import java.util.LinkedList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DriverFactory;

/**
 * 
 * @author bsingh5
 *
 */
public class BrowserHelper  extends DriverFactory{

	private WebDriver driver;


	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("BrowserHelper : " + this.driver.hashCode());
	}

	public void goBack() {
		driver.navigate().back();
		log.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		log.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		log.info("");
	}

	public Set<String> getWindowHandlens() {
		log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());

		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsId.get(index));
		log.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		log.info("");
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}
	
	public void switchToFrame(By locator) {
		driver.switchTo().frame(getElement(locator));
		log.info(locator);
	}
	
	private String getElement(By locator) {

		return null;
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		log.info(nameOrId);
	}

}
