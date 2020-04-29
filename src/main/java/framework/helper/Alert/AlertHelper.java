
package framework.helper.Alert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import utils.DriverFactory;

/**
 * 
 * @author bsingh5
 *
 */

public class AlertHelper extends DriverFactory{
	
	
	public AlertHelper() {

		log.debug("AlertHelper : " + getDriver().hashCode());
	}
	
	public Alert getAlert() {
		log.debug("");
		return driver.switchTo().alert();
	}
	
	public void AcceptAlert() {
		log.info("");
		getAlert().accept();
	}
	
	public void DismissAlert() {
		log.info("");
		getAlert().dismiss();
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info(text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			// Ignore
			log.info("false");
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();
		log.info("");
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		log.info("");
	}
	
	public void AcceptPrompt(String text) {
		
		if (!isAlertPresent())
			return;
		
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
	}
}
