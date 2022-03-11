package otherFunctionality;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

import main.ScreenshotClass;

@RunWith(Parameterized.class)
public class OtherFunctionalityTest {

	private static WebDriver driver;
	private final String websiteUrl;
	private final List<String> linkList = new ArrayList<String>();
	private int recursionCount = 0;

	public OtherFunctionalityTest(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { { "https://www.google.de" }, { "https://www.uni-marburg.de" },
				{ "https://wikipedia.de/" } });
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
		recursionCount = 0;
	}

	@Test
	public void websiteLinksTest() throws Exception {
		List<WebElement> websiteElementsWithLinks = driver.findElements(By.tagName("a"));

		for (WebElement oneWebsiteElementWithALink : websiteElementsWithLinks) {
			String linkString = oneWebsiteElementWithALink.getAttribute("href");
			System.out.println(linkString);
			linkList.add(linkString);
		}
		assertTrue(linkList.size() != 0);
		// assertTrue(linkList.contains("https://blog.wikimedia.de/")||
		// linkList.contains(other));
		// this.sleep();
	}

	@Test
	public void screenshotTest() throws Exception {
		File screenshotFolder = new File("src/screenshots/");
		for (File file : Objects.requireNonNull(screenshotFolder.listFiles())) {
			if (file.delete()) {
				System.out.println("Deleted the file: " + file.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
		}
		ScreenshotClass.takeScreenshot(driver);
	}


	@Test
	public void pageSourceTest() throws Exception {
		System.out.println(driver.getPageSource());
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
