package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(outsideModal)).click();	
			if (outsideModal.isDisplayed())
			{
				WebElement wb = driver.findElement(By.xpath("//div[@class='vifp-close']"));
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click();", wb);
				System.out.println("Modal was closed [ok]");
			}

		} catch (Exception e) {
			System.out.println("**** Catch -> no close button detected...");
		}
	}
	
	public void clickSailTo()
	{
		wait.until(ExpectedConditions.visibilityOf(sailTo)).click();		
	}
	
	public void clickSailFrom()
	{
		wait.until(ExpectedConditions.visibilityOf(SailFrom)).click();		
	}
	
	public void clickDates()
	{
		wait.until(ExpectedConditions.visibilityOf(dates)).click();		
	}
	
	public void clickDuration()
	{
		wait.until(ExpectedConditions.visibilityOf(duration)).click();		
	}
	
	public void clickSearchCruises()
	{
		wait.until(ExpectedConditions.visibilityOf(searchCruises)).click();		
	}
	
	public void clickCloseModal()
	{
		wait.until(ExpectedConditions.visibilityOf(closeModal)).click();		
	}


	public void selectSailOptionByText(String option) {
		WebElement container = driver.findElement(By.xpath("//ul[@class='cdc-filter-cols-wrapper']"));
		wait.until(ExpectedConditions.visibilityOf(container));
		List<WebElement> options = container.findElements(By.tagName("li"));
		for (WebElement myOption : options)
		{
		    if (myOption.getText().equals(option))
		    {
		    	myOption.click(); // click the desired option
		        break;
		    }
		}		
	}

	public void selectSailFromOptionByText(String option) {
		WebElement container = driver.findElement(By.xpath("//ul[@class='cdc-filter-cols-wrapper']"));
		List<WebElement> options = container.findElements(By.tagName("li"));
		for (WebElement myOption : options)
		{
		    if (myOption.getText().equals(option))
		    {
		    	myOption.click(); // click the desired option
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
		
		WebElement container = driver.findElement(By.xpath("//p[contains(text()," + date + ")]/parent::ul"));
		List<WebElement> options = container.findElements(By.tagName("li"));
		for (WebElement myOption : options)
		{
		    if (myOption.getText().equals("Jan") || myOption.getText().equals("Dec"))
		    {
		    	myOption.click(); // click the desired option
		    }
		}				
	}

	public void selectDurationByRange(String range) {		
		WebElement container = driver.findElement(By.xpath("//ul[@class='cdc-filter-cols-wrapper']"));
		List<WebElement> options = container.findElements(By.tagName("li"));
		for (WebElement myOption : options)
		{
		    if (myOption.getText().equals(range))
		    {
		    	myOption.click(); // click the desired option
		        break;
		    }
		}	
	}
}
