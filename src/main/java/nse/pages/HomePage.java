package nse.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	public HomePage(WebDriver d){ this.driver = d; }
	
	public void searchStock(String stock){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'quote-search')]//input"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		element.sendKeys(stock);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		element.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//span[text()='"+stock+"']")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement stockElement = driver.findElement(By.xpath("//a[text()='"+stock+"']"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(stockElement));
	}
}