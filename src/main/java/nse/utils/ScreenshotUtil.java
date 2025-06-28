package nse.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.*;
import java.util.UUID;

public class ScreenshotUtil {
	public static String captureScreenshot(WebDriver d, String name){
		File src = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		String randomText = UUID.randomUUID().toString();
		name = name + randomText.substring(0,5);
		String path = "screenshots/" + name + ".png";
		try { FileUtils.copyFile(src,new File(path)); }
		catch(IOException e){ e.printStackTrace(); }
		return path;
	}
}