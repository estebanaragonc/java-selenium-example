package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
		System.setProperty("headless", "false"); // You can set this property elsewhere
		
		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
       
		options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);
        
		driver.manage().window().maximize();
		driver.get("https://www.carnival.com/");
        
       
    }

    @AfterSuite
    public void afterSuite() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
