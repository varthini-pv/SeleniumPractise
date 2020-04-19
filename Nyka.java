package SeleniumPractise;


import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;




public class Nyka 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions ops=new ChromeOptions();
		ops.addArguments("--disable-notifications");
		
		ChromeDriver driver=new ChromeDriver(ops);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		
		WebElement lnk_brands = driver.findElementByXPath("//a[text()='brands']");
		mouse.moveToElement(lnk_brands).perform();
		WebElement lnk_Popular = driver.findElementByXPath("//a[text()='Popular']");
		mouse.moveToElement(lnk_Popular).perform();
		driver.switchTo().defaultContent();
		driver.findElementByXPath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList=new LinkedList<>(windowHandles);
		driver.switchTo().window(winList.get(1));
		if(driver.getTitle().contains("L'Oreal Paris"))
		{
			System.out.println("Title is as expected");
		}
		else
		{
			System.out.println("Title is not as expected");
		}
		driver.findElementByXPath("//span[@class='pull-right']/i").click();
		driver.findElementByXPath("//span[text()='customer top rated']/following-sibling::div[@class='control__indicator radio']").click();
		Thread.sleep(1000);
		WebElement tab_category = driver.findElementByXPath("(//div[text()='Category']/following::div//i)[1]");
		tab_category.click();
		WebElement chckbx_Shampoo = driver.findElementByXPath("//div[@class='slide-filter ']//span[contains(text(),'Shampoo')]/following-sibling::div");
		mouse.moveToElement(chckbx_Shampoo);
		chckbx_Shampoo.click();
		if(driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']//li[text()='Shampoo']").isDisplayed())
		{
			System.out.println("Filter is Applied Succesfully");
		}
		else
		{
			System.out.println("Filter is not applied");
		}
		driver.findElementByXPath("//span[text()=\"L'Oreal Paris Colour Protect Shampoo\"]").click();
		windowHandles = driver.getWindowHandles();
		winList=new LinkedList<>(windowHandles);
		driver.switchTo().window(winList.get(2));
		driver.findElementByXPath("//span[text()='175ml']").click();
		String price = driver.findElementByClassName("post-card__content-price-offer").getText().replaceAll("\\D", "");
		System.out.println("Price: "+price);
		driver.findElementByXPath("//div[@class='pull-left']//button[text()='ADD TO BAG']").click();
		try 
		{
			if(driver.findElementByXPath("//span[text()='Product has been added to bag.']").isDisplayed())
			{
				System.out.println("Success Message is  displayed");
			}
			
		} catch (NoSuchElementException e) 
		{
			System.out.println("Success Message is not displayed");
		}
		driver.findElementByClassName("AddBagIcon").click();
		String grandTotal = driver.findElementByXPath("//div[text()='Grand Total']/following-sibling::div").getText().replaceAll("\\D", "");
		System.out.println("Grand Total: "+grandTotal);
		driver.findElementByXPath("//button//span[text()='Proceed']").click();
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		String warningMsg = driver.findElementByClassName("message").getText();
		System.out.println("Warning Displayed: "+warningMsg);
		driver.quit();
	}
	

}
