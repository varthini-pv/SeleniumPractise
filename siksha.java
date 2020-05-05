package SeleniumPractise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class siksha 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver80.exe");
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://studyabroad.shiksha.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions mouse=new Actions(driver);
		JavascriptExecutor js =(JavascriptExecutor) driver;
		WebDriverWait wait=new WebDriverWait(driver,100);
		mouse.moveToElement(driver.findElementByXPath("//div[@class='main-menu cf']//label[text()='Colleges ']")).perform();
		driver.findElementByLinkText("MS in Computer Science &Engg").click();
		driver.findElementByXPath("//div[@class='cokkie-box']//a[text()='OK']").click();
		WebElement option_GRE = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//p[text()='GRE']/parent::label//span")));
		option_GRE.click();
		
		
		WebElement option_fee=null;
		try 
		{
			 option_fee = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Max 10 Lakhs']/parent::label//span")));
			option_fee.click();
			
		} 
		catch (Exception e) 
		{
			Thread.sleep(3000);
			option_fee = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Max 10 Lakhs']/parent::label//span")));
			option_fee.click();
		}
		try {
			WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='USA']/ancestor::label//span")));
			country.click();
		} catch (Exception e) {
			Thread.sleep(4000);
			WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='USA']/ancestor::label//span")));
			country.click();
		}
		WebElement drpdwn_Sort = driver.findElementById("categorySorter");
		Select sort=new Select(drpdwn_Sort);
		sort.selectByVisibleText("Low to high 1st year total fees");
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//p[text()='USA']")));
		List<WebElement> details = driver.findElementsByXPath("//div[@class='uni-course-details flLt']");
		int size=0;
		for(int i=0;i<details.size();i++)
		{
			int j=i+1;
			size = driver.findElementsByXPath("(//div[@class='uni-course-details flLt'])["+j+"]//span[@class='tick-mark']").size();
			if(size==3)
			{
				try {
					driver.findElementByXPath("(//div[@class='course-touple clearwidth'])["+j+"]//p[text()='Add to compare']").click();
				} catch (Exception e) {
					driver.findElementByXPath("(//div[@class='course-touple clearwidth'])["+j+"]//p[text()='Add to compare']").click();
				}
				break;
			}
		}
		
		driver.findElementByXPath("(//ul[@class='sticky-suggestion-list']//a)[1]").click();
		driver.findElementByXPath("//a//*[text()='Compare Colleges >']").click();
		driver.findElementByXPath("//p//*[text()='2021']").click();
		driver.findElementByXPath("//div[@class='sp-frm selectCountryField signup-fld invalid ']").click();
		driver.findElementByXPath("//label[contains(@for,'USA')]//span").click();
		mouse.moveToElement(driver.findElementByClassName("ok-btn")).perform();
		driver.findElementByClassName("ok-btn").click();
		driver.findElementByXPath("//p//*[text()='Masters']").click();
		driver.findElementByXPath("//div[@class='signUp-child-wrap courseDiv']").click();
		driver.findElementByXPath("//li[text()='MS']").click();
		driver.findElementByXPath("//div[@class='sp-frm selectField signup-fld invalid  filled']").click();
		driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();
		driver.findElementById("signup").click();
		List<WebElement> errormessages = driver.findElementsByXPath("//div[contains(@class,'errorField')]");
		for(WebElement eachErrorMessage:errormessages)
		{
			System.out.println(eachErrorMessage.getText());
		}
		
		
	}

}
