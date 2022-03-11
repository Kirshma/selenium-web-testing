package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

public class Main {

	public static void main(String[] args) {
		//Beispielaufruf, um zu testen, ob alles funktioniert
		System.setProperty("webdriver.gecko.driver",
				"lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.de");
		System.out.println("The page title is " + driver.getTitle());
		driver.quit();
	}
}
