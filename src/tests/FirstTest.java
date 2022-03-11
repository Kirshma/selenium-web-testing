package tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTest {

	@Test
	public void test() throws InterruptedException {
		// Erster Test, um zu sehen, ob WebDriver richtig eingerichtet ist
		System.setProperty("webdriver.gecko.driver",
				"lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.de");
		String s = driver.getTitle();
		System.out.println(s);
		Thread.sleep(1000);
		driver.quit();
	}

}
