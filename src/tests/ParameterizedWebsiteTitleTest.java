

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class ParameterizedWebsiteTitleTest {

	private static WebDriver driver;
	private String websiteUrl;
	private String expectedTitle;

	public ParameterizedWebsiteTitleTest(String websiteUrl, String expectedTitle) {
		this.websiteUrl = websiteUrl;
		this.expectedTitle = expectedTitle;
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { { "https://www.google.de", "Google" }, { "https://www.bahn.de",
				"DB Fahrplan, Auskunft, Tickets, informieren und buchen - Deutsche Bahn" },
				{ "https://wikipedia.de/", "Wikipedia, die freie Enzyklopädie" } });
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Before
	public void goTo() {
		// Legt die Webseite fest, die vor jedem Test aufgerufen und getestet werden
		// soll; Wird in der statischen @Parameters Methode angegeben
		driver.get(websiteUrl);
	}

	@Test
	public void websiteTitleTest() throws Exception {
		if (websiteUrl.equals("https://wikipedia.de/")) {
			expectedTitle = driver.getTitle();
		}
		System.out.println(driver.getTitle());
		assertEquals(expectedTitle, driver.getTitle());
		// this.sleep();
	}

	@After
	public void sleep() throws InterruptedException {
		Thread.sleep(1000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
