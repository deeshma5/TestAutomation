package nse.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	private static ExtentReports extent;
	public synchronized static ExtentReports getInstance(){
		if(extent == null){
			String reportDir = System.getProperty("user.dir") + "/test-reports/";
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String reportFile = "TestReport_" + timestamp + ".html";
			String fullPath = reportDir + reportFile;
			new File(reportDir).mkdirs();
			ExtentHtmlReporter html = new ExtentHtmlReporter(fullPath);
			extent = new ExtentReports();
			extent.attachReporter(html);
		}
		return extent;
	}
}