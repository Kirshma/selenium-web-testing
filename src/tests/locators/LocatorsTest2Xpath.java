package locators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class LocatorsTest2Xpath {

	private static WebDriver driver;
	private String websiteUrl;
	private String xPathAbsolute;
	private String xPathAbsoluteExpected;
	private String xPathRelative;
	private String xPathRelativeExpected;

	public LocatorsTest2Xpath(String websiteUrl, String xPathAbsolute, String xPathAbsoluteExpectedTitle,
			String xPathRelative, String xPathRelativeExpectedTitle) {
		this.websiteUrl = websiteUrl;
		this.xPathAbsolute = xPathAbsolute;
		this.xPathAbsoluteExpected = xPathAbsoluteExpectedTitle;
		this.xPathRelative = xPathRelative;
		this.xPathRelativeExpected = xPathRelativeExpectedTitle;
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "https://www.google.com/", "/html/body/div[1]/div[1]/div/div/div/div[1]/div/div[2]/a",
						"Google Bilder", "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/div/div/div[7]/span",
						"Google-Doodles" },
				{ "https://www.wikipedia.org/", "//*[@id=\"js-link-box-fr\"]", "Wikipédia, l'encyclopédie libre",
						"//span[contains(text(),'Commons')]", "Wikimedia Commons" } });
	}

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

	@Before
	public void goTo() {
		// Legt die Webseite fest, die vor jedem Test aufgerufen und getestet werden
		// soll
		driver.get(websiteUrl);
	}

	@Test
	public void xPathAbsoluteTest() {
		driver.findElement(By.xpath(xPathAbsolute)).click();
		assertEquals(xPathAbsoluteExpected, driver.getTitle());
	}

	/*
	@Test
	public void xPathRelativeTest() {
		driver.findElement(By.xpath(xPathRelative)).click();
		assertEquals(xPathRelativeExpected, driver.getTitle());
	}
	 */

	@Test
	public void xPathTextTest() throws InterruptedException {
		if (websiteUrl.equals("https://www.google.com/")) {
			if (driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/button[2]/div")).isDisplayed()) {
				driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/button[2]/div")).click();
			}
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[1]")).click();
			assertEquals("Google - Info", driver.getTitle());
			this.sleep();
		}

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	private void sleep() throws InterruptedException {
		Thread.sleep(0000);
	}

}
