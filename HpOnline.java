package SeleniumPractise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HpOnline 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		WebElement link_Laptops = driver.findElementByXPath("//a[@class='level-top ui-corner-all has-dropdown-menu']//span[text()='Laptops']");
		mouse.moveToElement(link_Laptops).perform();
		WebElement lnk_Pavilon = driver.findElementByXPath("//a[@class='ui-corner-all']//span[text()='Pavilion']");
		lnk_Pavilon.click();
		@SuppressWarnings("deprecation")
		WebDriverWait w=new WebDriverWait(driver,600);
		try
		{
			w.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("inside_notify_content")));
			driver.findElementByClassName("inside_notify_content");
			mouse.moveByOffset(100 ,100).click().perform();
			driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']").click();
		}
		catch(Exception e)
		{
			driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']").click();
		}

		WebElement option_processor=driver.findElementByXPath("(//dt[@aria-expanded='false']//span[text()='Processor'])[1]");
		w.until(ExpectedConditions.elementToBeClickable(option_processor));
		mouse.moveToElement(option_processor);
		option_processor.click();
		//w.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Intel Core i7']/preceding-sibling::input)[last()]")));
		WebElement option_i7 = driver.findElementByXPath("(//span[text()='Intel Core i7']/preceding-sibling::input)[last()]");
		mouse.moveToElement(option_i7).perform();
		option_i7.click();
		WebElement img_loading = driver.findElementByXPath("//img[@src='https://in-media.apjonlinecdn.com/static/version1586989238/frontend/HPOLS/default/en_US/images/loader-2.gif']");
		w.until(ExpectedConditions.invisibilityOf(img_loading));
		WebElement option_1TB = driver.findElementByXPath("(//span[text()='More than 1 TB']/preceding::input)[last()]");
		mouse.moveToElement(option_1TB).perform();
		option_1TB.click();
		w.until(ExpectedConditions.invisibilityOf(img_loading));
		WebElement drpdwn_sort = driver.findElementById("sorter");
		Select sort=new Select(drpdwn_sort);
		sort.selectByVisibleText("Price : Low to High");
		w.until(ExpectedConditions.invisibilityOf(img_loading));
		List<WebElement> productName = driver.findElementsByXPath("//strong[@class='product name product-item-name']//a");
		System.out.println("productName: "+productName.get(0).getText());
		List<WebElement> productPrice = driver.findElementsByXPath("//span[@data-price-type='finalPrice']//span[@class='price']");
		String product = productPrice.get(0).getText().replaceAll("\\D", "");
		System.out.println("Product Price: "+product);
		List<WebElement> btn_AddToCart = driver.findElementsByXPath("//span[text()='Add To Cart']");
		btn_AddToCart.get(0).click();
		w.until(ExpectedConditions.invisibilityOf(img_loading));
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//a//span[text()='View and edit cart']").click();
		driver.findElementByName("pincode").sendKeys("600100");
		driver.findElementByXPath("//button[text()='check']").click();
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='change']"))));
		if(driver.findElementByXPath("//span[@class='available' and text()='In stock']").isDisplayed())
		{
			System.out.println("delivery is available");
			Thread.sleep(1000);
			String finalPrice = driver.findElementByXPath("//td[@data-th='Order Total']//span").getText().replaceAll("\\D", "");
			if(finalPrice.equals(product))
			{
				System.out.println("Final Price is as expecected");
				driver.findElementByXPath("//li[@class='item']//button//span[text()='Proceed to Checkout']").click();
			}
			else
			{
				System.out.println("Final Price is not as expected");
			}
			w.until(ExpectedConditions.invisibilityOf(img_loading));
			
			driver.findElementByXPath("//div[@class='place-order-primary']//span[text()='Place Order']").click();
			
			List<WebElement> errorMsgs = driver.findElementsByXPath("//*[text()='This is a required field.']");
			if(errorMsgs.size()==9)
			{
				System.out.println("Error Message is displayed : "+errorMsgs.get(0).getText());
			}
			else
			{
				System.out.println("Error message is not dispalyed");
			}
		}	
		else
		{
			System.out.println("delivery is not available");
		}
		driver.close();
	}
		
}
