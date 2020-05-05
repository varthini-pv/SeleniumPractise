package SeleniumPractise;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip
{
	public static void main(String[] args) 
	{
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	ChromeOptions ops=new ChromeOptions();
	ops.addArguments("--disable-notifications");
	ChromeDriver driver=new ChromeDriver(ops);
	driver.get("https://www.makemytrip.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElementByXPath("//a//span[text()='Hotels']").click();
	driver.findElementById("city").click();
	driver.findElementByXPath("//div[@class='hsw_autocomplePopup locus autoSuggestPlugin']//input").sendKeys("GOA");
	driver.findElementByXPath("//li[@role='option']//p[contains(text(),'Goa, India')]").click();
	LocalDate today=LocalDate.now();
	String checkinMonth = today.plusMonths(1).getMonth().toString();
	String temp=checkinMonth.substring(1).toLowerCase();
	checkinMonth=checkinMonth.substring(0, 1).concat(temp);
	int checkinDay=today.getDayOfMonth();
	int checkOutDay=checkinDay+4;
	driver.findElementById("checkin").click();
	if(driver.findElementByXPath("//div[@class='DayPicker-Caption']//div[text()='"+checkinMonth+"']").isDisplayed())
	{
		System.out.println("Next Month is displayed in the Date Picker");
		
	}
	else
	{
		System.out.println("Next Month is not  displayed in the Date Picker");
	}
	driver.findElementByXPath("(//div[@class='DayPicker-Body'])[2]//div[@class='DayPicker-Day' and text()='"+checkinDay+"']").click();
	driver.findElementById("checkout").click();
	if(driver.findElementByXPath("//div[@class='DayPicker-Caption']//div[text()='"+checkinMonth+"']").isDisplayed())
	{
		System.out.println("Next Month is displayed in the Date Picker");
		
	}
	else
	{
		System.out.println("Next Month is not  displayed in the Date Picker");
	}
	driver.findElementByXPath("(//div[@class='DayPicker-Body'])[2]//div[@class='DayPicker-Day' and text()='"+checkOutDay+"']").click();
	driver.findElementById("guest").click();
	WebElement txt_2Adults = driver.findElementByXPath("//li[@data-cy='adults-2']");
	if(!txt_2Adults.isSelected())
	{
		txt_2Adults.click();
	}
	driver.findElementByXPath("//li[@data-cy='children-1']").click();
	Select drpdwn_selectAge=new Select(driver.findElementByClassName("ageSelectBox"));
	drpdwn_selectAge.selectByVisibleText("2");
	driver.findElementByXPath("//button[text()='APPLY']").click();
	driver.findElementById("hsw_search_button").click();
	List<WebElement> imgs_Hotel = driver.findElementsByClassName("imgCont");
	WebDriverWait wait =new WebDriverWait(driver, 90);
	wait.until(ExpectedConditions.visibilityOfAllElements(imgs_Hotel));
		 try 
		 { 
			 driver.switchTo().frame(4);
			 driver.findElementByClassName("we_forward").click(); 
		} 
		 catch(NoSuchElementException e) 
		 { 
			 System.out.println("Temp Alert is not Present");
		 }
		driver.switchTo().defaultContent(); 									
		
	driver.findElementByXPath("//ul[@class='filterList']//label[text()='Baga']").click();
	if(driver.findElementByXPath("//ul[@class='appliedFilters']//span[text()='Baga']").isDisplayed())
	{
		System.out.println("Locality is selected");
	}
	imgs_Hotel = driver.findElementsByClassName("imgCont");
	wait.until(ExpectedConditions.visibilityOfAllElements(imgs_Hotel));
	WebElement option_5Star = driver.findElementByXPath("//ul[@class='filterList']//label[text()='5 Star']");
	Actions mouse=new Actions(driver);
	mouse.moveToElement(option_5Star).perform();
	option_5Star.click();
	if(driver.findElementByXPath("//ul[@class='appliedFilters']//span[text()='5 Star hotels']").isDisplayed())
	{
		System.out.println("Star selection is selected");
	}
	imgs_Hotel = driver.findElementsByClassName("imgCont");
	wait.until(ExpectedConditions.visibilityOfAllElements(imgs_Hotel));
	imgs_Hotel.get(0).click();
	Set<String> windowHandles = driver.getWindowHandles();
	List<String> winSet=new LinkedList<>(windowHandles);
	driver.switchTo().window(winSet.get(0));
	System.out.println("Hotel Name: "+driver.findElementById("detpg_hotel_name").getText());
	driver.findElementByXPath("//div[@class='hotelHeader']//span[text()='MORE OPTIONS']").click();
	driver.findElementByXPath("//td[contains(text(),'9')]/following::span[text()='SELECT']").click();
	driver.findElementByClassName("close").click();
	driver.findElementById("detpg_book_combo_btn").click();
	System.out.println("Total Payable Price:"+driver.findElementById("revpg_total_payable_amt").getText().replaceAll("\\D", ""));
	driver.close();
	}

}
