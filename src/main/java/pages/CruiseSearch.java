package pages;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.BasePage;

public class CruiseSearch extends BasePage{

	@FindBy(xpath= "//div[@ng-show='model.showLayoutSwitch()']/a[1]/i[1]") private WebElement switchGrid;
	@FindBy(xpath= "//a[@title='Pricing']")private WebElement linkPricing;
	@FindBy(xpath= "//*[@ng-style='maxPointerStyle']")private WebElement pricingSlider;
	@FindBy(xpath= "//*[@class='vrg-search-unit']")private List<WebElement> cards;
	@FindBy(xpath= "//*[@ng-style='maxPointerStyle']")private WebElement lastCardLoaded;
	@FindBy(xpath= "//span[@ng-bind='model.res.currency']/parent::span") private List<WebElement> allCards;
	@FindBy(linkText= "learn More") private WebElement learnMoreLink;	
	@FindBy(xpath="//a[@data-tealium='vrgf-learn-more']") private List<WebElement> firstCruiseResult;
	@FindBy(xpath="//span[@class='urgBarClose']") private WebElement closeAlert;
	
	
	public CruiseSearch(WebDriver driver) {
		super(driver);		
	}
	
	public boolean isGridSelected()
	{
		Boolean result = false;
		 try {
		        String value = switchGrid.getAttribute("ng-hide");
		        if (value != null){
		            result = true;
		        }
		    } 
		 catch (Exception e) {}

		    return result;
	}
	
	public void clickPricing()
	{
		wait.until(ExpectedConditions.visibilityOf(linkPricing)).click();		
	}

	public int countTotalCruisesResults() {
		return cards.size();
	}

	public int getLastCardAmount() {
		
		List<WebElement> elements =  driver.findElements(By.xpath("//span[@ng-bind='model.res.currency']/parent::span"));
		int totalElement = elements.size();
		String lastElement = elements.get(totalElement - 1).getText();
		lastElement = lastElement.replace("$", "");
		lastElement = lastElement.replace(" ", "");
		lastElement = lastElement.replace("*AVGPP", "");
		     
		return  Integer.parseInt(lastElement);
	}

	public void adjustMaximumPricePerPersonTo(int lastCardAmount) {
		wait.until(ExpectedConditions.visibilityOf(pricingSlider));
		WebElement slider = driver.findElement(By.xpath("//*[@ng-style='maxPointerStyle']"));		
		Actions action= new Actions(driver);
		if (lastCardAmount > 500)
		{
			System.out.println("Amount of last card > 500");
			action.dragAndDropBy(slider, -500, 0).build().perform();;
		}
		else
		{
			System.out.println("Amount of last card v 500");
			action.dragAndDropBy(slider, -800, 0).build().perform();;
		}	    

		//Implicit wait just to wait a little bit while page loads / can be deleted
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isResultListSortedByPrice() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfAllElements(allCards));
		
		boolean status = true;
		List<WebElement> elements =  driver.findElements(By.xpath("//span[@ng-bind='model.res.currency']/parent::span"));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		if(elements.size() == 1)
		{			
			return status;
		}
		else
		{
			Iterator<WebElement> iter = elements.iterator();
			String  current = iter.next().getText();
			//clean the string
			current = cleanString(current);
			String previous = iter.next().getText();
			//clean the string
			previous = cleanString(previous);
			while (iter.hasNext()) {
		        current = iter.next().getText();
		        current = cleanString(current);
		        if (previous.compareTo(current) > 0) {
		        	status = false;
		            return status;
		        }
		        previous = current;
		    }			
		}
		return status;
	}
	
	private String cleanString(String myString)
	{
		String stringToClean; 
		stringToClean=myString.replace("$", "");
		stringToClean=stringToClean.replace(" ", "");
		stringToClean=stringToClean.replace("*AVGPP", "");
		return stringToClean;
	}

	public void seletFirstCruiseInResultList() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfAllElements(firstCruiseResult));
		firstCruiseResult.get(1).click();
		
	}

	public void dismissAlert() {
		System.out.println("Waiting until alert is displayed in itinerary");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='urgBarClose']")));
			System.out.println("Alert found");
			closeAlert.click();
			System.out.println("Alert closed [ok]");
		} catch (Exception e) {
			System.out.println("Alert not found...");
		}
	}

	
	
}
