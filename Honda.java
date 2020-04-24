package SeleniumPractise;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Honda 
{
	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver80.exe");
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		driver.findElementByClassName("close").click();
		driver.findElementByLinkText("Scooter").click();
		WebDriverWait wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//div[@id='scooter']//div[@class='owl-item'])[1]//a")));
		driver.findElementByXPath("(//div[@id='scooter']//div[@class='owl-item'])[1]//a").click();
		driver.findElementByLinkText("Specifications").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByLinkText("ENGINE")));
		mouse.moveToElement(driver.findElementByLinkText("ENGINE")).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span")));
		String displacementOne = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText().replaceAll("\\D", "");;
		System.out.println(displacementOne);
		driver.findElementByLinkText("Scooter").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//div[@id='scooter']//div[@class='owl-item'])[3]//a")));
		driver.findElementByXPath("(//div[@id='scooter']//div[@class='owl-item'])[3]//a").click();
		driver.findElementByLinkText("Specifications").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByLinkText("ENGINE")));
		mouse.moveToElement(driver.findElementByLinkText("ENGINE")).perform();;
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span")));
		String displacementTwo = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText().replaceAll("\\D", "");;
		System.out.println(displacementTwo);
		if(Integer.parseInt(displacementTwo)>=Integer.parseInt(displacementOne))
		{
			System.out.println("Activa 125 have better displacement");
		}
		else
		{
			System.out.println("Dio have better displacement");
		}
		driver.findElementByLinkText("FAQ").click();
		driver.findElementByLinkText("Activa 125 BS-VI").click();
		driver.findElementByLinkText("Vehicle Price").click();
		Select vType=new Select(driver.findElementByName("SegmentID"));
		WebElement selectedType = vType.getFirstSelectedOption();
		Select sType=new Select(driver.findElementByName("ModelID"));
		WebElement selectedScooter = sType.getFirstSelectedOption();
		if(selectedType.getAttribute("value").equals("2"))
		{
			if(selectedScooter.getAttribute("value").equals("31"))
			{
				driver.findElementByXPath("//form[@id='formPriceMaster']//button[text()='Submit']").click();
				driver.findElementByPartialLinkText("Click here to know the price").click();
				Set<String> windowHandles = driver.getWindowHandles();
				List<String> winList=new LinkedList<String>(windowHandles);
				driver.switchTo().window(winList.get(1));
				Select state=new Select(driver.findElementById("StateID"));
				state.selectByVisibleText("Tamil Nadu");
				Select city=new Select(driver.findElementById("CityID"));
				city.selectByVisibleText("Chennai");
				driver.findElementByXPath("//button[text()='Search']").click();
				WebElement resultTable = driver.findElementByXPath("//table[@id='gvshow']//tbody");
				Map<String,String> print=new LinkedHashMap<String,String>();
				List<WebElement> rows = resultTable.findElements(By.tagName("tr"));
				List<WebElement> coloumn=null;
				for(WebElement eachRow:rows)
				{
					coloumn = eachRow.findElements(By.tagName("td"));
					print.put( coloumn.get(coloumn.size()-2).getText(), coloumn.get(coloumn.size()-1).getText());
		
				}
				for(Entry<String, String> eachset:print.entrySet())
				{
					System.out.println(eachset.getKey()+":"+eachset.getValue());
				}

			}
		}
		
	driver.quit();	
	}
	

}
