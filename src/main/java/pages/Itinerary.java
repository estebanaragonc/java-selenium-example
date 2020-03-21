package pages;

import java.nio.channels.DatagramChannel;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.BasePage;

public class Itinerary extends BasePage{

	@FindBy(xpath= "//li[@id='sm-booking-btn']")private WebElement btnBookNow;
	@FindBy(xpath= "//div[@class='itinerary-menu']")private WebElement menuSection;
	@FindBy(xpath= "//div[@class='daily-tiles']/div[2]")private WebElement daysSection;
	@FindBy(xpath= "//section[@id='dining-activities']")private WebElement activitiesSection;
	@FindBy(xpath= "//section[@id='dining-activities']")private WebElement diningSection;
	@FindBy(xpath= "//section[@id='section-rooms']")private WebElement stateRoomSection;
	@FindBy(xpath= "//div[@class='ipg-content']")private WebElement moreDaysSection;
	@FindBy(xpath= "//div[@class='tile']/div/h3")private List<WebElement> allDaysCards;
	@FindBy(xpath= "//button[@ng-click='closeDayDetails()']") private WebElement btnCloseDayOpened;
	@FindBy(xpath= "//div[@class='slide-heading-wrapper']/h3") private WebElement titleCardExpanded;
	
	
	public Itinerary(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	public boolean isBookNowPresentInPage() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(btnBookNow));
		
		if (btnBookNow.isDisplayed())
		{
			return true;
		}
		return false;
	}

	public boolean isItineraryMenuDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(menuSection));
		
		if (menuSection.isDisplayed())
		{
			return true;
		}
		return false;
	}

	public boolean isItineraryDaysSectionDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(daysSection));
		
		if (daysSection.isDisplayed())
		{
			return true;
		}
		return false;
	}

	public boolean isItineraryActivitiesSectionDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(activitiesSection));
		
		if (activitiesSection.isDisplayed())
		{
			return true;
		}
		return false;
	}

	public boolean isItineraryDiningSectionDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(diningSection));
		
		if (diningSection.isDisplayed())
		{
			return true;
		}
		return false;
	}

	public boolean isItineraryStateRoomSectionDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(stateRoomSection));
		
		if (stateRoomSection.isDisplayed())
		{
			return true;
		}
		return false;
	}

	public boolean isItineraryMoreDatesSectionDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(moreDaysSection));
		
		if (moreDaysSection.isDisplayed())
		{
			return true;
		}
		return false;
	}

	public boolean expandAllDaysSection() {
		
		boolean status = false;
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfAllElements(allDaysCards));
		
		int dayCardPosition = 3; // position 3 means first position
		int iteration = 1;		
		
		String title = "";
		for ( int i = 1 ; i < allDaysCards.size() ; i++) 
		{ 			
			// obtain current day text
			title = allDaysCards.get(i).getText();
			//click in the 'read more' link for card selected
			if (iteration%2==0) 
			{
				//wait until read more is available and then click it
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='details']/div[3]/div[" + dayCardPosition + "]/div[2]/div/div/button")));
				WebElement readMore = driver.findElement(By.xpath("//*[@id='details']/div[3]/div[" + dayCardPosition + "]/div[2]/div/div/button"));
				clickElement(readMore);
			}
			else
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='details']/div[3]/div[" + dayCardPosition + "]/div[1]/div/div/button")));
				WebElement readMore = driver.findElement(By.xpath("//*[@id='details']/div[3]/div[" + dayCardPosition + "]/div[1]/div/div/button"));
				clickElement(readMore);
			}
			scrollUp();
     		//get the text of the cruise when day is opened
			wait.until(ExpectedConditions.visibilityOf(titleCardExpanded));
			//WebElement titleCardOpened = driver.findElement(By.xpath("//div[@class='slide-heading-wrapper']/h3"));   		
			String textTitleCardOpened = titleCardExpanded.getText();	
			// comparte if title obtained when card is opened is the same obtained in the card information
			System.out.println("Comparing [" + title + "] with [" + textTitleCardOpened + "]" );
			if (title.equals(textTitleCardOpened))
			{
				status = true;
			}
			else
			{
				status = false;
				break;
			}
			// close the day opened
			scrollUp();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait.until(ExpectedConditions.elementToBeClickable(btnCloseDayOpened));
			btnCloseDayOpened.click();
			System.out.println("Close X was clicked");
			//wait until day opened card is completely closed
			WebElement element_closed = driver.findElement(By.xpath("//div[@class='slide-heading']"));
			wait.until(ExpectedConditions.invisibilityOf(element_closed));
			dayCardPosition = dayCardPosition + 1;
			iteration = iteration + 1;
		}
		return status;
	}
}
