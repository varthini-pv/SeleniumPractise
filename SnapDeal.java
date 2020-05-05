package SeleniumPractise;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SnapDeal 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver80.exe");
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		WebDriverWait wait=new WebDriverWait(driver,120);
		try {
			driver.findElementById("pushDenied").click();
			} 
		catch (Exception e) {
			System.out.println("Not Present");
		}
		WebElement link_Toys = driver.findElementByXPath("//a//span[text()=\"Toys, Kids' Fashion & more\"]");
		mouse.moveToElement(link_Toys).perform();;
		driver.findElementByXPath("//a//span[text()='Educational Toys']").click();
		WebElement chckbx_4PlusRating = driver.findElementByXPath("//label[@for='avgRating-4.0']");
		chckbx_4PlusRating.click();
		if(chckbx_4PlusRating.isSelected())
		{
			System.out.println("Rating filter is selected");
		}
		
		WebElement filter_coupon40_50 = driver.findElementByXPath("//label[@for='discount-40%20-%2050']");
		Thread.sleep(2000);
		filter_coupon40_50.click();
		if(filter_coupon40_50.isSelected())
		{
			System.out.println("Coupon filter is selected");
		}
		driver.findElementByXPath("//div[@class='pincode-enter js-pincode-enter']//input").sendKeys("600100");
		driver.findElementByXPath("//div[@class='pincode-enter js-pincode-enter']//button").click();
		if(driver.findElementByXPath("//div[@class='pincode-serviceability clearfix']//span[text()='Deliver to: ']").isDisplayed())
		{
			System.out.println("Delivery Available");
		}
		Thread.sleep(2000);
		mouse.moveToElement(driver.findElementByXPath("//picture[@class='picture-elem']")).perform();
		driver.findElementByXPath("(//div[@class='clearfix row-disc']//div[contains(text(),'Quick View')])[1]").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@class=' btn btn-theme-secondary prodDetailBtn']")));
		driver.findElementByXPath("//a[@class=' btn btn-theme-secondary prodDetailBtn']").click();
		String price = driver.findElementByClassName("payBlkBig").getText();
		String deliveryCharge = driver.findElementByXPath("//div[@class='ddrPinCheck otoRangeCheck ']//span[@class='availCharges']").getText().replaceAll("\\D", "");
		System.out.println(price);
		System.out.println(deliveryCharge);
		driver.findElementByXPath("//div[@class='pdp-comp ']//div[@id='add-cart-button-id']").click();
		String totalPay = driver.findElementByXPath("//div[@class='you-pay']//span").getText().replaceAll("\\D", "");
		if(Integer.parseInt(totalPay)==Integer.parseInt(price)+Integer.parseInt(deliveryCharge))
		{
			System.out.println("Total Pay is displayed Correctly");
		}
		driver.findElementById("inputValEnter").sendKeys("Sanitizer",Keys.ENTER);
		driver.findElementByXPath("(//div[@class='product-desc-rating '])[1]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList=new LinkedList<>(windowHandles);
		driver.switchTo().window(winList.get(1));
		 price = driver.findElementByClassName("payBlkBig").getText();
		 deliveryCharge = driver.findElementByXPath("//div[@class='ddrPinCheck otoRangeCheck ']//span[@class='availCharges']").getText().replaceAll("\\D", "");
		System.out.println(price);
		System.out.println(deliveryCharge);
		driver.findElementByXPath("//div[@class='pdp-comp ']//div[@id='add-cart-button-id']").click();
		driver.findElementByClassName("cartQuantity").click();
		Thread.sleep(1000);
		String cartPrice = driver.findElementByXPath("//div[@class='cart-bill-wrapper rfloat']//input[contains(@value,'PROCEED TO PAY')]").getAttribute("value").replaceAll("\\D", "");
		System.out.println(cartPrice);
		if(Integer.parseInt(cartPrice)==Integer.parseInt(totalPay)+Integer.parseInt(price)+Integer.parseInt(deliveryCharge))
		{
			System.out.println("Cart Amount is as expected");
		}
		driver.quit();
	}
	

}
