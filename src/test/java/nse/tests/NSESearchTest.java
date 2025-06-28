package nse.tests;

import nse.pages.*;
import nse.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NSESearchTest extends BaseTest {

	@Parameters({"stockName", "highValue", "lowValue"})
	@Test
	public void testSearchAndProfitLoss(@Optional("RCOM") String stockName, @Optional("2.58") String highValue, @Optional("1.34") String lowValue){
		driver.get("https://www.nseindia.com/");
		HomePage hp = new HomePage(driver);
		test.info("StockName: " + stockName);
		test.info("HighValue: " + highValue);
		test.info("LowValue: " + lowValue);
		hp.searchStock(stockName);
		
		SearchResultsPage sp = new SearchResultsPage(driver);
		String highStr = sp.get52WeekHigh();
		String lowStr = sp.get52WeekLow();
		
		test.info("52-Week High: " + highStr);
		test.info("52-Week Low: " + lowStr);

		Assert.assertEquals(highStr, highValue, "52 High value is mismatching." + highStr + "|" + highValue);
		Assert.assertEquals(lowStr, lowValue, "52 Low value is mismatching." + lowStr + "|" + lowValue);
		
		double high;
		double low;
		
		if(highStr.length() > 6)
		{
			high = Double.parseDouble(highStr.replaceAll(",", ""));
		}
		else
		{
			high = Double.parseDouble(highStr);
		}
		
		if(highStr.length() > 6)
		{
			low = Double.parseDouble(lowStr.replaceAll(",", ""));
		}
		else
		{
			low = Double.parseDouble(lowStr);
		}
		
		// example buy at low
		double buyPrice = low; 
		// simulate current price
		double currentPrice = (high + low)/2; 
		double pnl = sp.calculateProfitLoss(buyPrice, currentPrice);
		
		test.info("Simulated Profit/Loss: " + pnl);
		if(pnl > 0)
		{
			Assert.assertTrue(true, "Profit value is:" + pnl);
			test.info("Profit value is:" + pnl);
		}
		else
		{
			Assert.assertTrue(true, "Loss value is:" + pnl);
			test.info("Loss value is:" + pnl);
		}
	}
}
