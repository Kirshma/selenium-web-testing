package locators;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LocatorsTest3Xpath2 {

	public static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Legt den Browser fest, der beim Aufruf der Tests verwendet werden soll
		System.setProperty("webdriver.gecko.driver",
				"lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Der Driver wartet bis zu 10 Sekunden auf das Ausführen der Webseite
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void xPathContainsTest() {
		// Anpassbare Xpath Suche mit Modifikator contains() und starts-with()
		//driver.get("https://www.google.com/");
		//driver.findElement(By.xpath("//a[contains(@class,'Fx4vi') and contains(text(),'Wie funktioniert')]")).click();
		// driver.get("https://learn.letskodeit.com/p/practice");
		// driver.findElement(By.xpath("//input[contains(@value,'bmw') and
		// contains(@type,'radio')]")).click();
		// driver.findElement(By.xpath("//select[starts-with(@id,'multiple')"));
	}

	@Test
	public void xPathParentSiblingTest() throws InterruptedException {
		// Test von Radio Buttons: Es wird eine Liste an Radio-Buttons erstellt und
		// anschließend jeder einmal betätigt
		driver.get("https://courses.letskodeit.com/practice");
		driver.findElement(By.xpath("//option[@value='apple']//parent::select"));
		List<WebElement> radioButtons = driver.findElements(
				By.xpath("//legend[contains(text(),'Radio')]//following-sibling::label//following-sibling::input"));

		for (WebElement element : radioButtons) {
			Assert.assertFalse(element.isSelected());
			element.click();
			Assert.assertTrue(element.isSelected());
			System.out.println(element.getAttribute("value"));
			this.sleep();
		}
	}

	@Test
	public void xPathWithSelectTest() throws InterruptedException {
		boolean deGefunden = false;
		driver.get("https://www.wikipedia.org/");
		WebElement select = driver.findElement(By.xpath("//select[@id='searchLanguage']"));
		Select languageSelect = new Select(select);
		this.sleep();
		languageSelect.selectByValue("en");
		this.sleep();
		languageSelect.selectByIndex(0);
		this.sleep();
		languageSelect.selectByVisibleText("Español");
		this.sleep();
		// Oder
		List<WebElement> languageList = languageSelect.getOptions();
		for (WebElement concreteLanguage : languageList) {
			System.out.println(concreteLanguage.getAttribute("value"));
			if (concreteLanguage.getAttribute("value").equals("de")) {
				concreteLanguage.click();
				deGefunden = true;
				Assert.assertTrue(concreteLanguage.isSelected());
			}
		}
		this.sleep();
		if (!deGefunden) {
			throw new IllegalStateException();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	private void sleep() throws InterruptedException {
		Thread.sleep(2000);
	}

}
