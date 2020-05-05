package SeleniumPractise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasket 
{
	public static void main(String[] args) throws InterruptedException 
	{

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver80.exe");
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		JavascriptExecutor js =(JavascriptExecutor) driver;
		WebDriverWait wait=new WebDriverWait(driver,100);
		WebElement menu_ShopByCategory = driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']");
		mouse.moveToElement(menu_ShopByCategory).perform();
		WebElement menu_FO = driver.findElementByLinkText("Foodgrains, Oil & Masala");
		mouse.moveToElement(menu_FO).perform();
		WebElement menu_Rice = driver.findElementByLinkText("Rice & Rice Products");
		menu_Rice.click();
		driver.findElementByXPath("//div[@class='col-md-3 hidden-sm hidden-xs wid-fix desktop-filter']//span[text()='Boiled & Steam Rice']").click();
		driver.findElementByXPath("//div[@class='col-md-3 hidden-sm hidden-xs wid-fix desktop-filter']//span[text()='bb Royal']/parent::label/span[@class='cr']").click();
		WebElement waitImg = driver.findElementByXPath("//div[@class='pd-overlay']//img");
		wait.until(ExpectedConditions.invisibilityOf(waitImg));
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div//i[@class='fa fa-caret-down'])[1]").click();
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div//ul[@class='dropdown-menu drop-select']//li//span[text()='5 kg'])[1]").click();
		String productPriceOne = driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div//span[@class='discnt-price']//span)[1]").getText();
		System.out.println("Price: "+productPriceOne);
		//driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div//button[text()='Add '])[1]").click();
		js.executeScript("arguments[0].click();",driver.findElementByXPath(("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div//button[text()='Add '])[1]")));
		driver.findElementByLinkText("Change Location").click();
		driver.findElementByXPath("//div[@class='container top-header locpops']//input[@qa='areaInput']").sendKeys("Medavakkam");
		//driver.findElementByXPath("//div[@class='container top-header locpops']//input[@qa='areaInput']").sendKeys(Keys.ARROW_DOWN);
		driver.findElementByXPath("//div[@class='container top-header locpops']//input[@qa='areaInput']").sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER));
		driver.findElementByXPath("(//button[text()='Continue'])[1]").click();
		/*
		 * WebElement txt_SuccessMsg = driver.findElementByClassName("toast-title");
		 * String SuccessMsg = txt_SuccessMsg.getText(); if(SuccessMsg.
		 * equals("Successfully added Ponni Boiled Rice - Super Premium 5 kg to the basket"
		 * )) { System.out.println("SuccessMessage is displayed"); }
		 * wait.until(ExpectedConditions.invisibilityOf(txt_SuccessMsg));
		 */
		driver.findElementByXPath("//div[@class='input-group']//input[@qa='searchBar']").sendKeys("Dal",Keys.ENTER);
		//String productPricetwo = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div//span[@class='discnt-price']//span)[1]").getText();
		//System.out.println("Price: "+productPricetwo);
		Thread.sleep(1000);
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div//i[@class='fa fa-caret-down'])[1]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div//ul[@class='dropdown-menu drop-select']//li//span[text()='2 kg'])[1]").click();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div//div[@qa='qty']/input)[1]").clear();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div//div[@qa='qty']/input)[1]").sendKeys("2");
		String productPricetwo = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div//span[@class='discnt-price']//span)[1]").getText();
		System.out.println("Price: "+productPricetwo);
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::div//button[text()='Add '])[1]").click();

		 WebElement txt_SuccessMsg = driver.findElementByClassName("toast-title");
		 String SuccessMsg = txt_SuccessMsg.getText();
		 if(SuccessMsg.equals("Successfully added Ponni Boiled Rice - Super Premium 5 kg to the basket")) 
		 { 
			 System.out.println("SuccessMessage is displayed"); 
		 }
		 wait.until(ExpectedConditions.invisibilityOf(txt_SuccessMsg));
		 
		mouse.moveToElement(driver.findElementByXPath("//a//span[@title='Your Basket']")).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@qa='subTotalMB']")));
		String[] temp = driver.findElementByXPath("//span[@qa='subTotalMB']").getText().split("\\.");
		String subTotal=temp[0];
		System.out.println(subTotal);
		if(Integer.parseInt(productPriceOne)+(Integer.parseInt(productPricetwo)*2)==Integer.parseInt(subTotal))
		{
			System.out.println("SubTotal is as expected");
		}
		try {
			driver.findElementByXPath("//a[text()='bb Popular Toor/Arhar Dal 2 kg Pouch']/following::div//button[@qa='decQtyMB']").click();
		} catch (StaleElementReferenceException e) {
			driver.findElementByXPath("//a[text()='bb Popular Toor/Arhar Dal 2 kg Pouch']/following::div//button[@qa='decQtyMB']").click();
			e.printStackTrace();
		}
		String qty = driver.findElementByXPath("//a[text()='bb Popular Toor/Arhar Dal 2 kg Pouch']/following::div[@class='btn-counter row']/input").getAttribute("value");
		if(qty.equals("1"))
		{
			System.out.println("Quantity is reduced");
		}
		 temp = driver.findElementByXPath("//span[@qa='subTotalMB']").getText().split("\\.");
		 subTotal=temp[0];
		if(Integer.parseInt(productPriceOne)+Integer.parseInt(productPricetwo)==Integer.parseInt(subTotal))
		{
			System.out.println("SubTotal is as expected");
		}
		driver.close();
		
	}

}
