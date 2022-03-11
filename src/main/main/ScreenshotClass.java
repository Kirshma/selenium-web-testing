package main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class ScreenshotClass {

	public static void takeScreenshot(WebDriver driver) {

		String screenshotPath = "src/screenshots/" + UUID.randomUUID() + ".png";

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			Files.copy(screenshotFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
