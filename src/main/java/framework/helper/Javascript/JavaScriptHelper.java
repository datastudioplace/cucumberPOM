
package framework.helper.Javascript;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverFactory;


public class JavaScriptHelper extends DriverFactory{

	public JavaScriptHelper(WebDriver driver) {
		
		log.debug("JavaScriptHelper : " + getDriver().hashCode());
	}

	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info(element);
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		log.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info(element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info(element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}
	public void ZoomInBypercentageThirtyPercent() {
		executeScript("document.body.style.zoom='30%'");
	}
	public void ZoomInBypercentageFourtyPercent() {
		executeScript("document.body.style.zoom='40%'");
	}
	
	public void ZoomInBypercentageFiftyPercent() {
		executeScript("document.body.style.zoom='50%'");
	}

	public void ZoomInBypercentageSixtyPercent() {
		executeScript("document.body.style.zoom='60%'");
	}
	public void ZoomInBypercentageSeventyPercent() {
		executeScript("document.body.style.zoom='70%'");
	}
	
	public void ZoomInBypercentageEightytyPercent() {
		executeScript("document.body.style.zoom='80%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
}
