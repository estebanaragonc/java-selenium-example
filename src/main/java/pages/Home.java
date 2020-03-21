package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.BasePage;

public class Home extends BasePage{

	//private WebDriverWait wait;
    
    @FindBy(id= "cdc-destinations") private WebElement sailTo;
    @FindBy(id= "cdc-ports") private WebElement SailFrom;
    @FindBy(id= "cdc-dates") private WebElement dates;
    @FindBy(id= "cdc-durations") private WebElement duration;
    @FindBy(xpath= "//a[@ng-click='model.onSearchCTAClick()']/parent::li/a") private WebElement searchCruises;
    @FindBy(xpath= "//a[@class='vifp-no']") private WebElement closeModal;
    @FindBy(xpath= "//*[@id='MainBody']/div[3]/div/div[10]") private WebElement closeX;
    @FindBy(xpath= "//*[@class='vifp-sweeps-modal-button']") private WebElement signUp;
    @FindBy(xpath= "//body[@id='MainBody']") private WebElement outsideModal;

    
    
	public Home(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void dismissModals()
	{
		try {
			System.out.println("Checking modal");
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@id='MainBody']")));
			
			WebElement body = driver.findElement(By.xpath("//body[@id='MainBody']"));
			
			if (body.isDisplayed())
			{
				WebElement wb = driver.findElement(By.xpath("//div[@class='vifp-close']"));
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click();", wb);
				System.out.println("Modal was closed [ok]");
			}

		} catch (Exception e) {
			System.out.println("No close modal button detected...just continue, nothing happened");
		}
	}
	
	public void clickSailTo()
	{
		clickElement(sailTo);
		//wait.until(ExpectedConditions.visibilityOf(sailTo)).click();
		//System.out.println();
	}
	
	public void clickSailFrom()
	{
		clickElement(SailFrom);
		//wait.until(ExpectedConditions.visibilityOf(SailFrom)).click();		
	}
	
	public void clickDates()
	{
		clickElement(dates);
		//wait.until(ExpectedConditions.visibilityOf(dates)).click();		
	}
	
	public void clickDuration()
	{
		clickElement(duration);
		//wait.until(ExpectedConditions.visibilityOf(duration)).click();		
	}
	
	public void clickSearchCruises()
	{
		clickElement(searchCruises);
		//wait.until(ExpectedConditions.visibilityOf(searchCruises)).click();		
	}
	
	public void clickCloseModal()
	{
		clickElement(closeModal);
		//wait.until(ExpectedConditions.visibilityOf(closeModal)).click();		
	}


	public void selectSailOptionByText(String option) {
		System.out.println("Selection option by text [" + option + "]");		
		try {
			scrollUp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> options = driver.findElements(By.xpath("//ul[@class='cdc-filter-cols-wrapper']/li"));
		System.out.println("Total options loaded: [" + options.size() + "]");
		for (WebElement myOption : options)
		{
		    if (myOption.getText().contains(option))
		    {
		    	clickElement(myOption);
		    	System.out.println("Option selected '" + option + "' [ok]");
		        break;
		    }
		}		
	}

	public void selectSailFromOptionByText(String option) {
		System.out.println("Selection option by text [" + option + "]");	
		WebElement container = driver.findElement(By.xpath("//ul[@class='cdc-filter-cols-wrapper']"));
		List<WebElement> options = container.findElements(By.tagName("li"));
		for (WebElement myOption : options)
		{
		    if (myOption.getText().equals(option))
		    {
		    	myOption.click(); // click the desired option
		    	System.out.println("Option selected '" + option + "' [ok]");
		        break;
		    }
		}		
	}

	public void selectRandomEnabledSailFromOption() {
		int count = 0;
		List<WebElement> options = driver.findElements(By.xpath("//ul[@class='cdc-filter-cols-wrapper']/li/button"));
		List<Integer> list = new ArrayList<Integer>();
		for (WebElement myOption : options)
		{
			if (myOption.isEnabled())
			{
				list.add(count);
			}
			count = count + 1;
		}
		Random  myRandom= new Random();
		int randomValue = myRandom.nextInt(list.size());
		options.get(randomValue).click();
	}

	public void selectAvailableMonthsFromYear(String date) {
		System.out.println("Select available year   [" + date + "]");	
		WebElement container = driver.findElement(By.xpath("//p[contains(text()," + date + ")]/parent::ul"));
		List<WebElement> options = container.findElements(By.tagName("li"));
		for (WebElement myOption : options)
		{
		    if (myOption.getText().equals("Jan") || myOption.getText().equals("Dec"))
		    {
		    	myOption.click(); // click the desired option
		    }
		}				
		System.out.println("All option months in year [" + date + "] were selected [ok]");
	}

	public void selectDurationByRange(String range) {	
		System.out.println("Selection range by text [" + range + "]");	
		WebElement container = driver.findElement(By.xpath("//ul[@class='cdc-filter-cols-wrapper']"));
		List<WebElement> options = container.findElements(By.tagName("li"));
		for (WebElement myOption : options)
		{
		    if (myOption.getText().equals(range))
		    {
		    	myOption.click(); // click the desired option
		    	System.out.println("Select range [" + range + "] [ok]");	
		        break;
		    }
		}	
	}
}
