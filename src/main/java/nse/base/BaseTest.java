package nse.base;

import nse.utils.*;
import com.aventstack.extentreports.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import java.lang.reflect.Method;
import java.time.Duration;


public class BaseTest {

	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, Method m) {
		extent = ExtentManager.getInstance();
		test = extent.createTest(m.getName());
	
		switch(browser.toLowerCase()){
			case "firefox": driver = new FirefoxDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			default:
				String path = System.getProperty("user.dir") +  "/src/main/resources/drivers/chromedriver.exe";;
				System.setProperty("webdriver.chrome.driver", path);
				driver = new ChromeDriver();
				break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LoggerUtil.info("Launched: " + browser);
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult r){
		if(r.getStatus() == ITestResult.FAILURE){
			String img = ScreenshotUtil.captureScreenshot(driver, r.getName());
			test.fail("Failed! Screenshot: " + img);
		}
		driver.quit();
		extent.flush();
	}
}