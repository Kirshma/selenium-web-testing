package testDriven;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;


import org.junit.After;
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
public class TransportWebsiteTest {

	private static WebDriver driver;
	private String websiteUrl;
	private String startOrt;
	private String zielOrt;
	private String startFeldXPath;
	private String zielFeldXPath;
	private String benutzerName;
	private String passwort;
	private String benutzerFeldCssSelector;
	private String passwortFeldCssSelector;

	public TransportWebsiteTest(String websiteUrl, String startOrt, String zielOrt, String startFeldXPath,
			String zielFeldXPath, String benutzerFeldCssSelector, String passwortFeldCssSelector, String benutzerName,
			String passwort) {
		this.websiteUrl = websiteUrl;
		this.startOrt = startOrt;
		this.zielOrt = zielOrt;
		this.startFeldXPath = startFeldXPath;
		this.zielFeldXPath = zielFeldXPath;
		this.benutzerFeldCssSelector = benutzerFeldCssSelector;
		this.passwortFeldCssSelector = passwortFeldCssSelector;
		this.benutzerName = benutzerName;
		this.passwort = passwort;
	}

	@Parameters
	public static List<Object[]> data() {
		// https://www.bahn.de/p/view/index.shtml
		// https://www.fluege.de/
		// https://www.rmv.de/c/de/start/
		return Arrays.asList(new Object[][] { { "https://www.fluege.de/", "Frankfurt", "Berlin",
				"//*[@id=\"f0-dep-location-\"]", "//*[@id=\"f0-arr-location-\"]",
				".js-customer-account-login > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)",
				".js-customer-account-login > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)",
				"Max Mustermann", "Passwort" },
				// { "https://www.bahn.de", "startOrt", "zielOrt", "startFeldXPath",
				// "zielFeldXPath", "benutzerFeldXPath",
				// "passwortFeldXPath", "benutzerName", "passwort" },
				// { "https://www.rmv.de", "startOrt", "zielOrt", "startFeldXPath",
				// "zielFeldXPath", "benutzerFeldXPath",
				// "passwortFeldXPath", "benutzerName", "passwort" }
		});
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Before
	public void goTo() {
		// Legt die Webseite fest, die vor jedem Test aufgerufen und getestet werden
		// soll; Wird in der statischen @Parameters Methode angegeben
		driver.get(websiteUrl);
	}

	@Test
	public void startFeldTest() {
		WebElement startFeld = driver.findElement(By.xpath(startFeldXPath));
		startFeld.sendKeys(startOrt);
		assertTrue(startFeld.getAttribute("value").equals(startOrt));
	}

	@Test
	public void zielFeldTest() {
		WebElement zielFeld = driver.findElement(By.xpath(zielFeldXPath));
		zielFeld.sendKeys(zielOrt);
		assertTrue(zielFeld.getAttribute("value").equals(zielOrt));

	}

	@Test
	public void loginTest() throws InterruptedException {
		if (websiteUrl.equals("https://www.fluege.de/")) {
			this.sleep();
			driver.findElement(By.xpath("//*[@id=\"CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll\"]")).click();
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div[2]/div/span/a[2]")).click();
		}
		this.sleep();
		WebElement benutzerNameFeld = driver.findElement(By.cssSelector(benutzerFeldCssSelector));
		benutzerNameFeld.sendKeys(benutzerName);
		this.sleep();
		WebElement passwortFeld = driver.findElement(By.cssSelector(passwortFeldCssSelector));
		passwortFeld.sendKeys(passwort);
		this.sleep();
		passwortFeld.submit();
		this.sleep();
		/*
		 * WebElement submit = driver.findElement(By.cssSelector(null));
		 * submit.submit();
		 */
	}

	/*
	@Test
	public void integrationTest() throws InterruptedException {
		this.startFeldTest();
		this.zielFeldTest();
		//this.loginTest();
		// assertEquals(Nutzer ist eingeloggt)
	}
	 */

	@After
	public void sleep() throws InterruptedException {
		Thread.sleep(2000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
