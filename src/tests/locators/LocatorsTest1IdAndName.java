package tests.locators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class LocatorsTest1IdAndName {

	private static WebDriver driver;
	private String websiteUrl;
	private String elementId;
	private String idExpectedText;
	private String linkName;
	private String linkUrlExpected;
	private String partialLinkName;
	private String partialTitleExpected;

	public LocatorsTest1IdAndName(String websiteUrl, String elementId, String idExpectedText, String linkName,
			String linkUrlExpected, String partialLinkName, String partialTitleExpected) {
		this.websiteUrl = websiteUrl;
		this.elementId = elementId;
		this.idExpectedText = idExpectedText;
		this.linkName = linkName;
		this.linkUrlExpected = linkUrlExpected;
		this.partialLinkName = partialLinkName;
		this.partialTitleExpected = partialTitleExpected;
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "https://www.wikipedia.org/", "js-link-box-de", "https://de.wikipedia.org/",
						"Für Android auf Google Play herunterladen",
						"https://play.google.com/store/apps/details?id=org.wikipedia&referrer=utm_source%3Dportal%26utm_medium%3Dbutton%26anid%3Dadmob",
						"Das freie", "Wiktionary" } });
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Legt den Browser fest, der beim Aufruf der Tests verwendet werden soll
		System.setProperty("webdriver.gecko.driver",
				"lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Before
	public void goTo() {
		// Legt die Webseite fest, die vor jedem Test aufgerufen und getestet werden
		// soll
		driver.get(websiteUrl);
	}

	@Test
	public void idTest() throws Exception {
		assertEquals(idExpectedText, driver.findElement(By.id(elementId)).getAttribute("href"));
		this.sleep();
	}

	@Test
	public void linkNameTest() throws Exception {
		/*
		driver.get(driver.findElement(By.linkText(linkName)).getAttribute("href"));
		assertEquals(linkUrlExpected, driver.getCurrentUrl());
		this.sleep();
		 */
	}

	@Test
	public void partialLinkNameTest() throws Exception {
		/*
		WebElement element = driver.findElement(By.partialLinkText(partialLinkName));
		element.click();
		assertEquals(partialTitleExpected, driver.getTitle());
		this.sleep();
		 */
	}

	@Test
	public void otherNameTests() throws Exception {
		// WebElement element = driver.findElement(By.name("btn"));
		// WebElement element2 = driver.findElement(By.tagName("tagName"));

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	private void sleep() throws InterruptedException {
		Thread.sleep(1000);
	}

}
