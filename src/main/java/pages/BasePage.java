package pages;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.google.common.collect.Ordering;

import utils.Constant;
import utils.DriverFactory;

	public class BasePage extends DriverFactory{
		protected WebDriverWait wait;
		protected JavascriptExecutor jsExecutor;
		private static String screenshotName;
		public static Logger log = LogManager.getLogger(BasePage.class.getName());
		private JavascriptExecutor js;

		
		
		public BasePage() throws IOException {
			this.wait = new WebDriverWait(driver, Constant.explicitWait);
			jsExecutor = ((JavascriptExecutor) driver);
	
	}

	/**********************************************************************************
	 **CLICK METHODS
	 * @throws IOException 
	 **********************************************************************************/
		public void waitAndClickElement(WebElement element) throws InterruptedException, IOException {
			boolean clicked = false;
			int attempts = 0;
			while (!clicked && attempts < Constant.explicitWait) {
				try {
					this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
					log.info("Log4j2 Confirms a Successful click on the WebElement: " + "<" + element.toString() + ">");
					System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
					clicked = true;
				} catch (Exception e) {
					log.info("Log4j2 says It was Unable or FAILED to wait and click on WebElement, Exception: " + e.getMessage());
					System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
					Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
				}
				attempts++;
			}
		}

	public void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < Constant.getTentiming()) {
			try {
				this.wait.until(ExpectedConditions.elementToBeClickable(by)).click();
				System.out.println("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
				log.info("Log4j2 Confirms a Successful clicked on the element using by locator:" + "=>" + "<" + by.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				log.error("Log4j2 Confirms Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
				System.out.println("Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
				Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<"+ by.toString() + ">");
			}
			attempts++;
		}
	}

	public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, Constant.explicitWait);
		try {
			tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
			list.sendKeys(textToSearchFor);
			list.sendKeys(Keys.ENTER);
			log.info("Log4j2 confirms a Success a sending the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
			System.out.println("Successfully sent the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
		} catch (Exception e) {
			log.error("Log4j2 confirms Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
			System.out.println("Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
			Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
		}
	}


	public void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
		try {
			final WebDriverWait customWait = new WebDriverWait(driver, timeout);
			customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
			locator.click();
			System.out.println("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"+ ", using a custom Timeout of: " + timeout);
		} catch (Exception e) {
			System.out.println("Unable to click on the WebElement, using locator: " + "<" + locator + ">" + ", using a custom Timeout of: " + timeout);
			Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
		}
	}
	
	/**********************************************************************************/
	//Bhanu Generic wait
	/**********************************************************************************/

	

	public String readValueFromInput(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		String value = element.getAttribute("value");
		log.info("weblement valus is.."+value);
		return value;
	}
	

	
	protected String getDisplayText(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		return element.getText();
	}
	

	public static synchronized String getElementText( WebElement element) {
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			log.info("Element not found " + ex);
		
		}
		return elementText;
	}
	
	 /**********************************************************************************
	 **from Automationpractice.com
	 **********************************************************************************/
	public static boolean isPresent(WebDriver webdriver, By selector) {
		// try to find element by specified selector
		try {
			webdriver.findElement(selector);
		} catch (NoSuchElementException e) {
			// if element not exist return false
			return false;
		}
		return true;
	}
	
	public static WebElement waitToBeClickable(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.elementToBeClickable(selector));
		return element;
	}
	
	public static WebElement waitForElementPresence(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.presenceOfElementLocated(selector));
		return element;
	}
	
	public static void waitForTitle(WebDriver driver, String title, int waitInterval){
		 (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.titleIs(title));
	}

	 /**********************************************************************************
	 **ACTION METHODS
	 **********************************************************************************/

	public void actionMoveAndClick(WebElement element) throws Exception {
		Actions ob = new Actions(driver);
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			ob.moveToElement(element).click().build().perform();
			System.out.println("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
			if (elementPresent == true) {
				ob.moveToElement(elementToClick).click().build().perform();
				System.out.println("(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
		} catch (Exception e) {
			System.out.println("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public void actionMoveAndClickByLocator(By element) throws Exception {
		Actions ob = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (elementPresent == true) {
				WebElement elementToClick = driver.findElement(element);
				ob.moveToElement(elementToClick).click().build().perform();
				System.out.println("Action moved and clicked on the following element, using By locator: " + "<" + element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(element);
			ob.moveToElement(elementToClick).click().build().perform();
			System.out.println("(Stale Exception) - Action moved and clicked on the following element, using By locator: "+ "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to Action Move and Click on the WebElement using by locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	
	/**********************************************************************************
	 **SEND KEYS METHODS /
	 **********************************************************************************/
	public void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
			try {
			this.WaitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			log.info("Log4j2 confirms Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"+ element.toString() + ">");
			System.out.println("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"+ element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: " + textToSend);
			log.info("Log4j2 confirms Unable to send keys to WebElement, Exception: " + "<" + element.toString() + "> and send the following keys: " + textToSend);
			Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}

	/*****************************LetKODEit @return ***************************************/
	

    /**
     * Refresh the current browser session
     */
    public void refresh() {
        driver.navigate().refresh();
        log.info("The Current Browser location was Refreshed...");
        //Util.sleep(Constant.explicitWait00, "The Current Browser location was Refreshed...");
    }

    /**
     * @return Returns the Current Page Title
     */
    public String getTitle() {
        String title = driver.getTitle();
        log.info("Title of the page is :: " + title);
        return title;
    }

    /**
     * @return Current Browser URL
     */
    public String getURL() {
        String url = driver.getCurrentUrl();
        log.info("Current URL is :: " + url);
        return url;
    }

    /**
     * Navigate browser back
     */
    public void navigateBrowserBack() {
        driver.navigate().back();
        log.info("Navigate back");
    }

    /**
     * Navigate browser forward
     */
    public void navigateBrowserForward() {
        driver.navigate().back();
        log.info("Navigate back");
    }

    /***
     * Builds the By type with given locator strategy
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @return Returns By Type
     */
    public By getByType(String locator) {
        By by = null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];
        try {
            if (locatorType.contains("id")) {
                by = By.id(locator);
            } else if (locatorType.contains("name")) {
                by = By.name(locator);
            } else if (locatorType.contains("xpath")) {
                by = By.xpath(locator);
            } else if (locatorType.contains("css")) {
                by = By.cssSelector(locator);
            } else if (locatorType.contains("class")) {
                by = By.className(locator);
            } else if (locatorType.contains("tag")) {
                by = By.tagName(locator);
            } else if (locatorType.contains("link")) {
                by = By.linkText(locator);
            } else if (locatorType.contains("partiallink")) {
                by = By.partialLinkText(locator);
            } else {
                log.info("Locator type not supported");
            }
        } catch (Exception e) {
            log.error("By type not found with: " + locatorType);
        }
        return by;
    }

    /**
     * Builds The WebElement By given locator strategy
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return WebElement
     */
    public WebElement getElement(String locator, String info) {
        WebElement element = null;
        By byType = getByType(locator);
        try {
            element = driver.findElement(byType);
        } catch (Exception e) {
            log.error("Element not found with: " + locator);
            e.printStackTrace();
        }
        return element;
    }

    /***
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return
     */
    public List<WebElement> getElementList(String locator, String info) {
        List<WebElement> elementList = new ArrayList<WebElement>();
        By byType = getByType(locator);
        try {
            elementList = driver.findElements(byType);
            if (elementList.size() > 0) {
                log.info("Element List found with: " + locator);
            } else {
                log.info("Element List not found with: " + locator);
            }
        } catch (Exception e) {
            log.error("Element List not found with: " + locator);
            e.printStackTrace();
        }
        return elementList;
    }

    /***
     * Check if element is present
     * @param locator locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @return boolean if element is present or not
     */
    public boolean isElementPresent(String locator, String info) {
        List<WebElement> elementList = getElementList(locator, info);
        int size = elementList.size();
        if (size > 0) {
            log.info("Element " + info + " Present with locator " + locator);
            return true;
        } else {
            log.info("Element " + info + " Not Present with locator " + locator);
            return false;
        }
    }

    /**
     * Click element with information about element and
     * time to wait in seconds after click
     *
     * @param element - WebElement to perform Click operation
     * @param info    - information about element
     */
    public void elementClick(WebElement element, String info, long timeToWait) {
        try {
            element.click();
            if (timeToWait == 0) {
                log.info("Clicked On :: " + info);
            } else {
                sleep(timeToWait, "Clicked on :: " + info);
            }
        } catch (Exception e) {
            log.error("Cannot click on :: " + info);
            takeScreenshot("Click ERROR", "");
        }
    }

    /**
     * Click element with no time to wait after click
     *
     * @param element - WebElement to perform Click operation
     * @param info    - information about element
     */
    public void elementClick(WebElement element, String info) {
        elementClick(element, info, 0);
    }

    /**
     * Click element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info
     * @param timeToWait
     * @return
     */
    public void elementClick(String locator, String info, long timeToWait) {
        WebElement element = this.getElement(locator, info);
        elementClick(element, info, timeToWait);
    }

    /**
     * Click element with locator and no time to wait
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element
     * @return
     */
    public void elementClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, 0);
    }

    /***
     * Click element using JavaScript
     * @param element - WebElement to perform Click operation
     * @param info - Information about element
     */
    public void javascriptClick(WebElement element, String info) {
        try {
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked on :: " + info);
        } catch (Exception e) {
            log.error("Cannot click on :: " + info);
        }
    }

    /***
     * Click element using JavaScript
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element
     */
    public void javascriptClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        try {
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked on :: " + info);
        } catch (Exception e) {
            log.error("Cannot click on :: " + info);
        }
    }

    /***
     * Click element when element is clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
    public void clickWhenReady(By locator, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebElement element = null;
            log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, Constant.getFifteentiming());
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator));
            element.click();
            log.info("Element clicked on the web page");
            driver.manage().timeouts().implicitlyWait(Constant.getThreetiming(), TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Constant.getThreetiming(), TimeUnit.SECONDS);
        }
    }

    /***
     * Send Keys to element
     * @param element - WebElement to send data
     * @param data - Data to send
     * @param info - Information about element
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(WebElement element, String data, String info, Boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            //Util.sleep(1000, "Waiting Before Entering Data");
            element.sendKeys(data);
            log.info("Send Keys on element :: " + info + " with data :: " + data);
        } catch (Exception e) {
            log.error("Cannot send keys on element :: " + info + " with data :: " + data);
        }
    }

    /***
     * Send Keys to element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(String locator, String data, String info, Boolean clear) {
        WebElement element = this.getElement(locator, info);
        sendData(element, data, info, clear);
    }

    /***
     * Send Keys to element with clear
     * @param element - WebElement to send data
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(WebElement element, String data, String info) {
        sendData(element, data, info, true);
    }

    /***
     * Send Keys to element with locator and clear
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(String locator, String data, String info) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }

    /**
     * Get text of a web element
     *
     * @param element - WebElement to perform click action
     * @param info    - Information about element
     */
    public String getText(WebElement element, String info) {
        System.out.println("Getting Text on element :: " + info);
        String text = null;
        text = element.getText();
        if (text.length() == 0) {
            text = element.getAttribute("innerText");
        }
        if (!text.isEmpty()) {
            log.info("The text is : " + text);
        } else {
            log.error("Text Not Found");
        }
        return text.trim();
    }

    /**
     * Get text of a web element with locator
     * @param locator
     * @param info
     * @return
     */
    public String getText(String locator, String info) {
        WebElement element = this.getElement(locator, info);
        return this.getText(element, info);
    }

    /**
     * Check if element is enabled
     * @param element
     * @param info
     * @return
     */
    public Boolean isEnabled(WebElement element, String info) {
        Boolean enabled = false;
        if (element != null) {
            enabled = element.isEnabled();
            if (enabled)
                log.info("Element :: " + info + " is Enabled");
            else
                log.info("Element :: " + info + " is Disabled");
        }
        return enabled;
    }

    /***
     * Check if element is enabled with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isEnabled(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isEnabled(element, info);
    }

    /**
     * Check if element is displayed
     * @param element
     * @param info
     * @return
     */
    public Boolean isDisplayed(WebElement element, String info) {
        Boolean displayed = false;
        if (element != null) {
            displayed = element.isDisplayed();
            if (displayed)
                log.info("Element :: " + info + " is displayed");
            else
                log.error("Element :: " + info + " is NOT displayed");
        }
        return displayed;
    }

    /***
     * Check if element is displayed with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
    public Boolean isSelected(WebElement element, String info) {
        Boolean selected = false;
        if (element != null) {
            selected = element.isSelected();
            if (selected)
                log.info("Element :: " + info + " is selected");
            else
                log.info("Element :: " + info + " is already selected");
        }
        return selected;
    }

    /**
     * @param locator
     * @param info
     * @return
     */
    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    /**
     * Selects a check box irrespective of its state
     *
     * @param element
     * @param info
     */
    public void Check(WebElement element, String info) {
        if (!isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is checked");
        } else
            log.info("Element :: " + info + " is already checked");
    }

    /**
     * Selects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    /**
     * UnSelects a check box irrespective of its state
     *
     * @param element
     * @param info
     * @return
     */
    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is unchecked");
        } else
            log.info("Element :: " + info + " is already unchecked");
    }

    /**
     * UnSelects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            log.info("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }

    /**
     * @param locator
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    /**
     * @param element
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * @param locator
     * @param timeout
     * @return
     */
    public WebElement waitForElement(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            log.info("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Constant.getThreetiming(), TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Constant.getThreetiming(), TimeUnit.SECONDS);
        }
        return element;
    }

    /***
     * Wait for element to be clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
    public WebElement waitForElementToBeClickable(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, 15);
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(byType));
            log.info("Element is clickable on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return element;
    }

    /**
     *
     */
    public boolean waitForLoading(String locator, long timeout) {
        By byType = getByType(locator);
        boolean elementInvisible = false;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            elementInvisible = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(byType));
            log.info("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return elementInvisible;
    }

    /**
     * Mouse Hovers to an element
     *
     * @param locator
     */
    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    /**
     * @param element
     * @param optionToSelect
     */
    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        log.info("Selected option : " + optionToSelect);
    }

    /**
     * Selects a given option in list box
     *
     * @param locator
     * @param optionToSelect
     */
    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    /**
     * get Selected drop down value
     *
     * @param element
     * @return
     */
    public String getSelectDropDownValue(WebElement element) {
        Select sel = new Select(element);
        return sel.getFirstSelectedOption().getText();
    }

    /**
     * @param element
     * @param optionToVerify
     */
    public boolean isOptionExists(WebElement element, String optionToVerify) {
        Select sel = new Select(element);
        boolean exists = false;
        List<WebElement> optList = sel.getOptions();
        for (int i = 0; i < optList.size(); i++) {
            String text = getText(optList.get(i), "Option Text");
            if (text.matches(optionToVerify)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            log.info("Selected Option : " + optionToVerify + " exist");
        } else {
            log.info("Selected Option : " + optionToVerify + " does not exist");
        }
        return exists;
    }

    /***
     *
     * @param methodName
     * @param browserName
     * @return
     */
    public String takeScreenshot(String methodName, String browserName) {
        String fileName = getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            log.info("Screen Shot Was Stored at: "+ path);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public void DoubleClick(WebElement element, String info) {
        Actions action = new Actions(driver);
        action.doubleClick(element);
        log.info("Double Clicked on :: " + info);
        action.perform();
    }

    /**
     * Right Click a WebElement
     *
     * @param locator
     */
    public void rightClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        log.info("Double Clicked on :: " + info);
    }

    /**
     * Right click a WebElement and select the option
     *
     * @param elementLocator
     * @param itemLocator
     */
    public void selectItemRightClick(String elementLocator, String itemLocator) {
        WebElement element = getElement(elementLocator, "info");
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        WebElement itemElement = getElement(itemLocator, "info");
        elementClick(itemElement, "Selected Item");
    }

    /**
     * @param key
     */
    public void keyPress(Keys key, String info) {
        Actions action = new Actions(driver);
        action.keyDown(key).build().perform();
        log.info("Key Pressed :: " + info);
    }
    public static void sleep(long msec, String info) {
        if (info != null) {
            System.out.println("Waiting " + (msec * .001) + " seconds :: " + info);
        }
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Sleep for specified number of milliseconds
     * @param msec
     */
    public static void sleep(long msec) {
        sleep(msec, null);
    }

    /***
     *
     * @param methodName
     * @param browserName
     * @return
     */
    public static String getScreenshotName(String methodName, String browserName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder().append(browserName)
                                                .append("_")
                                                .append(methodName)
                                                .append("_")
                                                .append(localDateTime)
                                                .append(".png");
        return name.toString();
    }

    /***
     * Get Random number within specified range
     * @param min
     * @param max
     * @return a random number
     */
    public static int getRandomNumber(int min, int max) {
        int diff = max - min;
        int randomNum = (int)(min + Math.random() * diff);
        System.out.println("Random Number :: " + randomNum +
                " within range :: " + min + " and :: " + max);
        return randomNum;
    }

    /***
     * Get Random number within specified range
     * @param number
     * @return a random number
     */
    public static int getRandomNumber(int number) {
        return getRandomNumber(1, number);
    }

    /***
     * Get random unique string with specified length
     * @param length
     * @return a unique string
     */
    public static String getRandomString(int length) {
        StringBuilder sbuilder = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i<length; i++) {
            int index = (int) (Math.random() * chars.length());
            sbuilder.append(chars.charAt(index));
        }
        String randomString = sbuilder.toString();
        System.out.println("Random string with length :: "
                + length + " is :: " + randomString);
        return randomString;
    }

    /***
     * Get random unique string with 10 characters length
     * @return a unique string
     */
    public static String getRandomString() {
        return getRandomString(10);
    }

    /***
     * Get simple date as string in the specified format
     * @param format MMddyy MMddyyyy
     * @return date as string type
     */
    public static String getSimpleDateFormat(String format){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        System.out.println("Date with format :: "
                + format + " :: " + formattedDate);
        return formattedDate;
    }

    /***
     * Get simple date time as string
     * @return date time as string type
     */
    public static String getCurrentDateTime(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        System.out.println("Date and Time :: " + date);
        return date;
    }

    /**
     * Checks whether actual String contains expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be checked with actual text
     * @return boolean result
     */
    public boolean verifyTextContains(String actualText, String expText) {
        if (actualText.toLowerCase().contains(expText.toLowerCase())){
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification Contains !!!");
            return true;
        }
        else{
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification DOES NOT Contains !!!");
            return false;
        }

    }

    /**
     * Checks whether actual string matches with expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be matched with actual text
     * @return
     */
    public boolean verifyTextMatch(String actualText, String expText) {
        if (actualText.equals(expText)){
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification MATCHED !!!");
            return true;
        }else{
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification DOES NOT MATCH !!!");
            return false;
        }
    }

    /**
     * Verify actual list contains items of the expected list
     *
     * @param actList
     * @param expList
     * @return
     */
    public Boolean verifyListContains(List<String> actList, List<String> expList) {
        int expListSize = expList.size();
        for (int i = 0; i < expListSize; i++) {
            if (!actList.contains(expList.get(i))) {
                return false;
            }
        }
        System.out.println("Actual List Contains Expected List !!!");
        return true;
    }

    /***
     * Verify actual list matches expected list
     * @param actList
     * @param expList
     * @return
     */
    public Boolean verifyListMatch(List<String> actList, List<String> expList) {
        boolean found = false;
        int actListSize = actList.size();
        int expListSize = expList.size();
        if (actListSize != expListSize) {
            return false;
        }

        for (int i = 0; i < actListSize; i++) {
            found = false;
            for (int j = 0; j < expListSize; j++) {
                if (verifyTextMatch(actList.get(i), expList.get(j))) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            System.out.println("Actual List Matches Expected List !!!");
            return true;
        }
        else {
            System.out.println("Actual List DOES NOT Match Expected List !!!");
            return false;
        }
    }

    /**
     * Verifies item is present in the list
     * @param actList
     * @param item
     * @return boolean result
     */
    public Boolean verifyItemPresentInList(List<String> actList, String item){
        int actListSize = actList.size();
        for (int i = 0; i < actListSize; i++) {
            if (!actList.contains(item)) {
                System.out.println("Item is NOT present in List !!!");
                return false;
            }
        }
        System.out.println("Item is present in List !!!");
        return true;
    }

    /**
     * Verify if list is in ascending order
     * @param list
     * @return boolean result
     */
    public boolean isListAscendingOrder(List<Long> list){
        boolean sorted = Ordering.natural().isOrdered(list);
        return sorted;
    }

	
	/**********************************************************************************/

	
	/**********************************************************************************
	 **JS METHODS & JS SCROLL
	 **********************************************************************************/
	public void scrollToElementByWebElementLocator(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
			System.out.println("Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
		}
	}

	public void jsPageScroll(int numb1, int numb2) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
			System.out.println("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
		} catch (Exception e) {
			System.out.println("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
			Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
		}
	}

	public void waitAndclickElementUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			System.out.println("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement staleElement = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
			if (elementPresent == true) {
				js.executeScript("arguments[0].click();", elementPresent);
				System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
			Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	/**********************************************************************************/
	// scrollToElemet executeScript scrollToElemetAndClick scrollDownVertically, ZoomInBypercentage
	/**********************************************************************************/
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

	public void scrollToElementAndClick(WebElement element) {
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

	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
	/**********************************************************************************/
	//helper wait
	/**********************************************************************************/
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		log.info(timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	public void waitForElement(WebDriver driver, WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element found..."+element.getText());
	}
	
	public WebElement waitForElement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**********************************************************************************/
		//assert  assertText assertExists assertVisibility  checkLinkByText
	/**********************************************************************************/
	public void assertExists(By by) {
		WebElement el = null;
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			el = driver.findElement(by);
		} catch (Exception e) {
			
		}
		Assert.assertNotNull("Failed to find element: "+by.toString(), el);
	}

	public void assertNotExists(By by) {
		WebElement el = null;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			el = driver.findElement(by);
		} catch (Exception e) {

		}
		Assert.assertNull("Element exists: "+by.toString(), el);
	}

    public void assertVisibility(By by, boolean visible) {
        WebElement el = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            el = driver.findElement(by);
        } catch (Exception e) {

        }
        if (visible) {
			Assert.assertNotNull("Element should be visible but is not: " + by.toString(), el);
		} else {
			Assert.assertNull("Element should not be visible but is: " + by.toString(), el);
		}
    }
	
	/**
	 * Validate an element has the specified text by a selenium selector
	 * 
	 * @param by selenium selector of the target element
	 * @param text the expected text value
	 */
	public void assertText(By by, String text) {
		WebElement el = null;
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			el = driver.findElement(by);
		} catch (Exception e) {
			Assert.fail("Failed to find element "+by.toString());
		}
		Assert.assertNotNull("Text is not correct", el.getText().equals(text));
	}
	
	/**
	 * Validate a link created using the List component
	 * 
	 * @param s The link text to search for
	 */
	public void checkLinkByText(String s) {
		Assert.assertNotNull("Couldn't find link "+s, driver.findElement(By.xpath("//a/h4[text()='"+s+"']")));
	}
	
	/**
	 * Validate a link has the specified text by a selenium selector
	 * 
	 * @param link The link text to search for
	 */
	public void assertLinkText(String link) {
		Assert.assertNotNull("Couldn't find link "+link, driver.findElement(By.linkText(link)));
	}
	
	/**
	 * Switches to an iframe based on a selenium selector
	 * @param by The selector to use to locate the iframe
	 */
	public void switchToFrame(By by) {
		WebElement el = driver.findElement(by);
		driver.switchTo().frame(el);
	}
	
	/**
	 * Tests the width and height of a component
	 * 
	 * @param parsysName Parent parsys of the element
	 * @param width      The expected width
	 * @param height     The expected height
	 */
	public void checkWidthAndHeightOfComponent(String parsysName, String width, String height) {
		try {
		List<WebElement> dropTargets = driver.findElements(By.xpath("//*[@id='OverlayWrapper']/div/div")); //.findElements(By.xpath("//div[@class='cq-droptarget']"));
		for (WebElement el : dropTargets) {
			if (el.getAttribute("data-path") != null) {
				if (el.getAttribute("data-path").endsWith("/"+parsysName.toLowerCase().replace(" ", ""))) {
					WebElement childDiv = el.findElement(By.tagName("div"));
					Assert.assertTrue("Width doens't match "+width, childDiv.getAttribute("width").equals(width));
					Assert.assertTrue("Height doens't match "+height, childDiv.getAttribute("height").equals(height));
					break;
				}
			}
		}
		} catch (Exception e) {
			Assert.fail("Failed to find component section "+parsysName);
		}
	}
	
	/**
	 * Tests the breadcrumb of a page to validate it matches the content hierarchy
	 */
	public void validateBreadcrumbs() {
		List<WebElement> breadcrumbLinks = driver.findElements(By.xpath("//*[contains(@class,'breadcrumb')]//a"));
		String[] parentPages = driver.getCurrentUrl().split("/");
		int breadcrumbLinkCount = 0;
		List<String> missingLinks = new ArrayList<String>();
		for (int i = parentPages.length; i > 0; i--) {
			if ( i == parentPages.length) {
				continue;
			}
			for (WebElement link : breadcrumbLinks) {
				if (link.getText() == parentPages[i]) {
					breadcrumbLinkCount++;
					missingLinks.add(parentPages[i]);
					break;
				}
			}
		}
		String listString = "";

		for (String s : missingLinks)
		{
		    listString += s + "\t";
		}
		Assert.assertTrue("Breadcrumb missing links "+ listString, breadcrumbLinkCount == parentPages.length-2);
	}
	
	/**
	 * Waits for an element and clicks it.&nbsp;Found by a selenium selector
	 * 
	 * @param by The selenium selector of the element
	 */
	public void clickBy(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		driver.findElement(by).click();
	}
	
	/**
	 * Extracts text from an element
	 * @param by The selenium selector of the element to extract text from
	 * @return String value of the elements text
	 */
	public String getTextFromElement(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return driver.findElement(by).getText();
	}
	
	/**
	 * Waits for and finds a list of elements by their selenium selector
	 * @param by Selenium selector of the elements
	 * @return List of all the found elements
	 */
	public List<WebElement> getElements(By by) {
		try {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		List<WebElement> retVal = driver.findElements(by);
		return retVal;
		} catch (Exception e) {
			return new ArrayList<WebElement>();
		}
	}
	
	/**
	 * Extracts text from an html input field
	 * @param by The selenium selector of the element to extract text from
	 * @return String value of the input fields text
	 */
	public String getTextFromInput(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return driver.findElement(by).getAttribute("value");
	}
	
	/**
	 * Extracts text from an html input field
	 * @param ele The element to extract text from
	 * @return String value of the input fields text
	 **/
	public String getTextFromInput(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
		return ele.getAttribute("value");
	}

	/**********************************************************************************/
	/**********************************************************************************/
	
	/**********************************************************************************/
	// accept any kind of By object.http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=3879315
	/**********************************************************************************/
	public WebElement findElement(By obj){
		   return driver.findElement(obj);
		}
	

	/**********************************************************************************/
	//SelectUsing Value Index VisibleText getAllDropDownValues
	/**********************************************************************************/
	public void SelectUsingVisibleValue(WebElement element,String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		log.info("Locator : " + element + " Value : " + visibleValue);
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	public void SelectUsingIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Locator : " + element + " Value : " + index);
	}
	
	public void SelectUsingVisibleText(WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		log.info("Locator : " + element + " Value : " + text);
	}
	
	
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
	
	/**********************************************************************************
	 **WAIT METHODS
	 **********************************************************************************/
	public boolean WaitUntilWebElementIsVisible(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element));
			log.info("Log4j2 confirms WebElement is visible using locator: " + "<" + element.toString() + ">");
			System.out.println("WebElement is visible using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			log.info("Log4j2 confirms WebElement is NOT visible at this time, using locator: " + "<" + element.toString() + ">");
			System.out.println("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			System.out.println("Element is visible using By locator: " + "<" + element.toString() + ">");
			log.info("Log4j2 shows Element is visible using By locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			log.info("Log4j2 tells that WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
			System.out.println("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementClickable(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("WebElement is clickable using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
			return false;
		}
	}


	public boolean waitUntilPreLoadElementDissapears(By element) {
		return this.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**********************************************************************************/
	//Alert methods
	/**********************************************************************************/
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

	public boolean isAlertPresents() {
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
		log.info("Accepting Alert If Present......");
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		log.info("Accepted Alert............");
	}
	
	public void AcceptPrompt(String text) {
		
		if (!isAlertPresent())
			return;
		
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
	}
	
	/**********************************************************************************
	 **PAGE METHODS
	 **********************************************************************************/
	public BasePage loadUrl(String url) throws Exception {
		log.info("Log4j2 Url is about to be intiatlised : " + "<" + url.toString() + ">");
		driver.get(url);
		log.info("Log4j2 Url intiatlised : " + "<" + url.toString() + ">");
		return new BasePage();
	}


	public String getCurrentURL() {
		try {
			String url = driver.getCurrentUrl();
			log.info("Log4j2 Found and initialised and got the following URL: " + url);
			System.out.println("Found(Got) the following URL: " + url);
			return url;
			} catch (Exception e) {
			log.info("Log4j2 proves Unable to locate (Get) the current URL, Exception: " + e.getMessage());
			System.out.println("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	public String waitForSpecificPage(String urlToWaitFor) {
		try {
			String url = driver.getCurrentUrl();
			this.wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
			System.out.println("The current URL was: " + url + ", " + "navigated and waited for the following URL: "+ urlToWaitFor);
			return urlToWaitFor;
		} catch (Exception e) {
			System.out.println("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
			return e.getMessage();
		}
	}
	
	/**********************************************************************************/
	//Synchronised boolean verifyElement
	/**********************************************************************************/
	public static synchronized boolean verifyElementPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 isDispalyed= element.isDisplayed();
			 log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			log.error("Element not found " + ex);
		}
		
		return isDispalyed;
	}
	
	public static synchronized boolean verifyElementNotPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 element.isDisplayed();
			 log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			log.error("Element not found " + ex);
			isDispalyed = true;
		}
		
		return isDispalyed;
	}
	
	public static synchronized boolean verifyTextEquals( WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText();
			if(actualText.equals(expectedText)) {
				log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag=true;
			}
			else {
				log.error("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag;
			}
		}
		catch(Exception ex) {
			log.error("actualText is :"+element.getText()+" expected text is: "+expectedText);
			log.info("text not matching" + ex);
			return flag;
		}
		
	}
	/**********************************************************************************
	 **ALERT & POPUPS METHODS
	 **********************************************************************************/
	public void closePopups(By locator) throws InterruptedException {
		try {
			List<WebElement> elements = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			for (WebElement element : elements) {
				if (element.isDisplayed()) {
					element.click();
					this.wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
					System.out.println("The popup has been closed Successfully!");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception! - could not close the popup!, Exception: " + e.toString());
			throw (e);
		}
	}

	public boolean checkPopupIsVisible() {
		try {
			@SuppressWarnings("unused")
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("A popup has been found!");
			return true;
		} catch (Exception e) {
			System.err.println("Error came while waiting for the alert popup to appear. " + e.getMessage());
		}
		return false;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void closeAlertPopupBox() throws AWTException, InterruptedException {
		try {
			Alert alert = this.wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		} catch (UnhandledAlertException f) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			System.out.println("Unable to close the popup");
			Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
		}
	}
	/**********************************************************************************/
	public void selectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public void selectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	public void selectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	
	public void deSelectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	
	public void deSelectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		select.deselectByIndex(index);
	}
	
	public void deSelectUsingVisibleText(WebElement element, String visibleText){
		Select select = new Select(element);
		select.deselectByVisibleText(visibleText);
	}
	
	public List<String> getAllDropDownData(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for(WebElement ele: elementList){
			valueList.add(ele.getText());
		}
		return valueList;
	}
	
	
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}
	
	public boolean isDisplayed(WebElement element){
		try{
			element.isDisplayed();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean isNotDisplayed(WebElement element){
		try{
			element.isDisplayed();
			return false;
		}
		catch(Exception e){
			return true;
		}
	}
	
	public String readValueFromElement(WebElement element){
		if(null == element){
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			return element.getText();
		}
		else{
			return null;
		}
	}
	public String getText(WebElement element){
		if(null == element){
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			return element.getText();
		}
		else{
			return null;
		}
	}
	
	
	public String getTitle1(){
		return driver.getTitle();
	}
	

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will make sure invisibilityOf element
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		return status;
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	
	public void pageLoadTime(long timeout, TimeUnit unit){
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
	
		}

	/**********************************************************************************/
	
	/***EXTENT REPORT****************************************************************/
	public static String returnDateStamp(String fileExtension) {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
		return date;
	}
	
	public static void captureScreenshot() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		screenshotName = returnDateStamp(".jpg");
	
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\extentReportOutput\\imgs\\" + screenshotName));
		
		Reporter.addStepLog("Taking a screenshot!");
		Reporter.addStepLog("<br>");
		Reporter.addStepLog("<a target=\"_blank\", href="+ returnScreenshotName() + "><img src="+ returnScreenshotName()+ " height=300 width=400></img></a>");
	}
	
	public static String returnScreenshotName() {
		return (System.getProperty("user.dir") + "\\extentReportOutput\\imgs\\" + screenshotName).toString();
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
		} finally {
			is.close();
			os.close();
		}
	}
	
	public static void copyLatestExtentReport() throws IOException {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_");
		File source = new File(System.getProperty("user.dir") + "\\extentReportOutput\\extentreport.html");
		File dest = new File(System.getProperty("user.dir") + "\\extentReportOutput\\" + date.toString() + ".html");
		copyFileUsingStream(source, dest);
	}
}
