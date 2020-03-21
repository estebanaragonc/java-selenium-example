package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class BaseTest {

    private WebDriver driver;
    private String url =  "https://www.carnival.com/";

    @BeforeClass
    public void beforeSuite() {
    	//set up driver
		System.setProperty("headless", "false"); // You can set this property elsewhere		
		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
       
		options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);
        
		driver.manage().window().maximize();		             
    }
    
    @BeforeMethod
    public void beforeMethod()
    {
    	System.out.println("************* Opening Browser *************");
    	driver.get(url);
    }
    
   
    @AfterClass
    public void afterSuite() {
    	//kill driver and end test execution in suite
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    //get driver
    public WebDriver getDriver() {
        return driver;
    }
}
