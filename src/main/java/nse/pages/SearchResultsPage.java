package nse.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	public SearchResultsPage(WebDriver d){ this.driver = d; }


	public String get52WeekHigh(){
		WebElement element = driver.findElement(
		By.xpath("(//span[text()='52 Week High ']/parent::td/following::td)[1]//span"));
		wait.until(ExpectedConditions.visibilityOf(element));
		String text = element.getText();
		if(text.length() <= 1 && !text.isEmpty())
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
		return element.getText();
	}
	
	public String get52WeekLow(){
		WebElement element = driver.findElement(
		By.xpath("(//span[text()='52 Week Low']/parent::td/following::td)[1]//span"));
		return element.getText();
	}

	public double calculateProfitLoss(double buy, double current){
		return current - buy;
	}
}