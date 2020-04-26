package SeleniumPractise;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PepperFry 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver80.exe");
		RemoteWebDriver driver=new ChromeDriver();
		WebDriverWait Wait=new WebDriverWait(driver, 100);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		Wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='popup-box gb-modal reg_modal']//a[@class='popup-close']")));
		driver.findElementByXPath("//div[@class='popup-box gb-modal reg_modal']//a[@class='popup-close']").click();
		mouse.moveToElement(driver.findElementByLinkText("Furniture")).perform();
		driver.findElementByLinkText("Office Chairs").click();
		driver.findElementByXPath("//div//h5[text()='Executive Chairs']").click();
		WebElement maxHeight = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[2]");
		maxHeight.clear();
		maxHeight.sendKeys("50");
		WebElement imgLoading = driver.findElementById("loaderOverlay");
		Wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Poise Executive Chair in Black Colour']")));
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElementByXPath("//a[text()='Poise Executive Chair in Black Colour']/ancestor::div[2]/following-sibling::div/div[2]/a"));
		Wait.until(ExpectedConditions.invisibilityOf(imgLoading));
		WebElement element = driver.findElementByXPath("//li//a[text()='Homeware']");
		js.executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(6000);
		Wait.until(ExpectedConditions.visibilityOf(element));
		mouse.moveToElement(element).build().perform();
		Wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByLinkText("Pressure Cookers")));
		mouse.moveToElement(driver.findElementByLinkText("Pressure Cookers")).perform();
		js.executeScript("arguments[0].click();",driver.findElementByLinkText("Pressure Cookers"));
		driver.findElementByXPath("//label[text()='Prestige']/parent::li").click();
		Wait.until(ExpectedConditions.invisibilityOf(imgLoading));
		driver.findElementByXPath("//label[text()='1 Ltr - 3 Ltr']/parent::li");
		Wait.until(ExpectedConditions.invisibilityOf(imgLoading));
		js.executeScript("arguments[0].click();",driver.findElementByXPath("//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']/ancestor::div[2]/following-sibling::div/div[2]/a"));
		WebElement icon_wishlist = driver.findElementByXPath("//div[@class='wishlist_bar']//a");
		mouse.moveToElement(icon_wishlist);
		Wait.until(ExpectedConditions.visibilityOf(icon_wishlist));
		js.executeScript("arguments[0].click();",icon_wishlist);
		js.executeScript("arguments[0].click();",driver.findElementByXPath("(//div[@class='minicart-wrap']//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr')]/following::div/a[@class='addtocart_icon'])[1]"));
		driver.findElementByClassName("srvc_pin_text").sendKeys("600128");
		driver.findElementByClassName("check_available").click();
		try {
			if(driver.findElementByXPath("//div[contains(text(),'Sorry! We don’t deliver')]").isDisplayed())
			{
				System.out.println("Cannot be delivered");
			}
			else
			{
				driver.findElementByLinkText("Proceed to pay securely ").click();
			}
		} catch (Exception e) 
		{
			driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		}
		//Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ck-sku-details']//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By Prestige']")));
		try {
			mouse.moveToElement(driver.findElementByXPath("//div[@class='ck-sku-details']//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By Prestige']")).perform();
		} catch (StaleElementReferenceException e) {
			Thread.sleep(6000);
			mouse.moveToElement(driver.findElementByXPath("//div[@class='ck-sku-details']//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By Prestige']")).perform();
		}
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File snap=new File("./snap/snap1.png");
		FileUtils.copyFile(screenshot, snap);
		driver.close();
		
	}

}
