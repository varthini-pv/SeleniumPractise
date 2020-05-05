package SeleniumPractise;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JusTDial 
{
	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver80.exe");
		ChromeOptions op=new ChromeOptions();
		op.addArguments("--disable-notifications");
		RemoteWebDriver driver=new ChromeDriver(op);
		WebDriverWait wait=new WebDriverWait(driver, 100);
		driver.get("https://www.justdial.com//");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		WebElement txtbx_City = driver.findElementById("city");
		txtbx_City.clear();
		txtbx_City.sendKeys("Chennai",Keys.TAB);
		driver.findElementByXPath("//li//a//span[text()='Auto care']").click();
		WebElement txt_carRepair = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//ul[@class='mm-listview mm-lstex']//span[text()='Car Repair']")));
		txt_carRepair.click();
		WebElement txt_hundai = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//ul[@class='mm-listview']//span[text()='Hyundai'])[1]")));
		txt_hundai.click();
		WebElement txt_carType = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Hyundai Accent'])[1]")));
		txt_carType.click();
		/*
		 * WebElement icon_ClosePopUP =
		 * wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath(
		 * "//section[@id='best_deal_div']//span[@class='jcl']")));
		 * icon_ClosePopUP.click(); driver.findElementByLinkText("Location").click();
		 * driver.findElementByXPath("//section[@id='jsbd']//input[@id='sortbydist']").
		 * sendKeys("Porur",Keys.ENTER); driver.findElementByXPath(
		 * "//section[@id='jsbd']//button[@id='sortbydistbtn']").click();
		 * driver.findElementByXPath("//a//span[text()='Distance ']").click();
		 * driver.findElementByXPath("//span[@class='drpwn dn']//a[text()='1 km']").
		 * click();
		 */
		String name=null;
		Map<String,String> topShops=new LinkedHashMap<String,String>();
		//List<WebElement> rating = driver.findElementsByXPath("//div[@class=' col-sm-5 col-xs-8 store-details sp-detail paddingR0']//p[@class='newrtings ']//span[@class='green-box']");
		List<WebElement> names = driver.findElementsByXPath("//div[@class=' col-sm-5 col-xs-8 store-details sp-detail paddingR0']//h2[@class='store-name']//span[@class='lng_cont_name']");
		for(int i=0;i<names.size();i++)
		{
			int j=i+1;
			String rating = driver.findElementByXPath("(//div[@class=' col-sm-5 col-xs-8 store-details sp-detail paddingR0'])["+j+"]//span[@class='green-box']").getText();
			float ratingInFloat = Float.parseFloat(rating);
			if((4.5<=ratingInFloat)&&(5.0>=ratingInFloat))
			{
				name = names.get(i).getText();
			
			String mobNum=null;
			List<WebElement> mobnum = driver.findElementsByXPath("(//div[@class=' col-sm-5 col-xs-8 store-details sp-detail paddingR0'])["+j+"]//p[@class='contact-info ']//span[contains(@class,'mobilesv')]");
			for(WebElement eachDigit:mobnum)
			{
				System.out.println(eachDigit.getAttribute("value"));
				mobNum = mobNum.concat(eachDigit.getAttribute("value"));
			}
			topShops.put(name, mobNum);
			}
		}
		System.out.println(topShops);
	}

}
