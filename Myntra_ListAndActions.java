package SeleniumPractise;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Myntra_ListAndActions 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions ops=new ChromeOptions();
		ops.addArguments("--disable-notifications");
		
		ChromeDriver driver=new ChromeDriver(ops);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		WebElement link_Women = driver.findElementByXPath("//div[@class='desktop-navLink']//a[text()='Women']");
		
		mouse.moveToElement(link_Women).build().perform();
		mouse.click(driver.findElementByLinkText("Jackets & Coats")).build().perform();
		
		String temptext = driver.findElementByXPath("//span[@class='title-count']").getText();
		
		String titleCount=temptext.replaceAll("\\D", "");
		int totalCount=Integer.parseInt(titleCount);
		int countofCategories=0;
		List<WebElement> categoryCount = driver.findElementsByXPath("//ul[@class='categories-list']//span[@class='categories-num']");
		for(WebElement eachCategoryCount:categoryCount)
		{
			countofCategories =countofCategories+ Integer.parseInt(eachCategoryCount.getText().replaceAll("\\D", ""));
		}
		if(countofCategories==totalCount)
		{
			System.out.println("Count Matches");
		}
		
		/*
		 * @SuppressWarnings("deprecation") WebDriverWait wait=new WebDriverWait(driver,
		 * 20); WebElement chckbx_Coat =
		 * driver.findElementByXPath("//input[@value='Coats']");
		 */
		//wait.until(ExpectedConditions.elementToBeClickable(chckbx_Coat));
		driver.findElementByXPath("//label[text()='Coats']//following-sibling::div").click();
		driver.findElementByClassName("brand-more").click();
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("Mango");
		@SuppressWarnings("deprecation")
		WebDriverWait wait=new WebDriverWait(driver,300);
		driver.findElementByXPath("//label[@class=' common-customCheckbox']//following-sibling::div").click();
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-brand")));
		List<WebElement> productBrand = driver.findElementsByClassName("product-brand");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-product")));
		List<WebElement> productname = driver.findElementsByClassName("product-product");
		Boolean productMatches=true;
		List<String> WrongProducts=new LinkedList<>();
		for(WebElement eachProductBrand:productBrand)
		{
			String wrongProductName=null;
			if(!eachProductBrand.getText().equals("MANGO"))
			{
				productMatches=false;
				wrongProductName = productname.get(productBrand.indexOf(eachProductBrand)).getText();
				WrongProducts.add(wrongProductName);
				
			}
		}
		if(productMatches)
		{
			System.out.println("Brand of the all the products are expected");
		}
		else
		{
			System.out.println("Brand of following products are not as expected");
			System.out.println(WrongProducts);
		}
		WebElement drpdwn_sort = driver.findElementByClassName("sort-sortBy");
	
		
		
		mouse.moveToElement(drpdwn_sort).perform();
		WebElement option_Discount = driver.findElementByXPath("//label[text()='Better Discount']");
		option_Discount.click();
		List<WebElement> productPrice = driver.findElementsByClassName("product-discountedPrice");
		WebElement cheepestProduct = productPrice.get(0);
		System.out.println("Lowest Price: "+cheepestProduct.getText().replaceAll("\\D", ""));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-product")));
		productname = driver.findElementsByClassName("product-product");
		mouse.moveToElement(productname.get(0)).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("(//span[@class='product-launchDate'])[1]").click();
		//mouse.click(wishlist.get(0)).perform();
		if(driver.getTitle().contains("login"))
		{
			System.out.println("Pass");
		}
		driver.close();
		
		
		
	}

}
