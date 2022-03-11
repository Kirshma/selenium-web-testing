package tests.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NavigationTest {

	static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Before
	public void goTo() {
		driver.get("https://www.wikipedia.org/");
	}

	@Test
	public void navigateTest() throws InterruptedException {
		WebElement select = driver.findElement(By.xpath("//select[@id='searchLanguage']"));
		Select languageSelect = new Select(select);
		languageSelect.selectByValue("de");
		WebElement searchBar = driver.findElement(By.xpath("//input[@id='searchInput']"));
		searchBar.sendKeys("Universität Marburg");
		searchBar.submit();
		this.sleep();
		driver.navigate().back();
		searchBar.clear();
		searchBar = driver.findElement(By.xpath("//input[@id='searchInput']"));
		searchBar.sendKeys("Marburg");
		searchBar.submit();
		this.sleep();
		driver.navigate().back();
		searchBar.clear();
		searchBar = driver.findElement(By.xpath("//input[@id='searchInput']"));
		searchBar.sendKeys("Softwarequalität");
		searchBar.submit();
		this.sleep();
		driver.navigate().back();
		this.sleep();
		driver.navigate().forward();
		this.sleep();
		driver.navigate().to("https://www.bahn.de/");
		this.sleep();
		assertEquals("https://www.bahn.de/", driver.getCurrentUrl());
	}

	@Test
	public void swapWindowsTest() throws InterruptedException {
		WebElement buttonToOpenNewWindow = driver.findElement(By.cssSelector("li.app-badge:nth-child(1)"));
		String startingWindow = driver.getWindowHandle();
		buttonToOpenNewWindow.click();

		Set<String> windowHandles = driver.getWindowHandles();

		for (String window : windowHandles) {
			if (!window.equals(startingWindow)) {
				driver.switchTo().window(window);
				System.out.println("Das Window-Handle dieser Seite ist " + driver.getWindowHandle());
				this.sleep();
				driver.close();
				break;
			}

		}
		this.sleep();
		driver.switchTo().window(startingWindow);
		driver.findElement(By.cssSelector("#searchInput"))
				.sendKeys("Der Driver befindet sich wieder im ersten Fenster");
		;
	}

	@Test
	public void scrollWindowTest() throws InterruptedException {
		driver.get("https://de.wikipedia.org/wiki/Philipps-Universit%C3%A4t_Marburg");
		this.sleep();
		WebElement footerElement = driver.findElement(By.cssSelector("#footer-info-lastmod"));
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", footerElement);
		/*
		 * Actions actions = new Actions(driver);
		 * actions.moveToElement(driver.findElement( By.
		 * cssSelector(".mw-parser-output > ul:nth-child(109) > li:nth-child(1) > a:nth-child(1)"
		 * ))); actions.perform();
		 */
		// actions.
		this.sleep();
	}

	@After
	public void sleep() throws InterruptedException {
		Thread.sleep(2000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
