package otherFunctionality;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RecursionTest {

    private static WebDriver driver;
    private int recursionCount = 0;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("webdriver.gecko.driver",
                "lib\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Before
    public void goTo() {
        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]")).click();
        recursionCount = 0;
    }

    @Test
    public void recursiveTest() throws Exception {
        sleep();
        sleep();
        WebElement searchField = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        sleep();
        sleep();
        searchField.sendKeys("Recursion");
        sleep();
        sleep();
        searchField.submit();
        recursion();
    }

    private void recursion() throws InterruptedException {
        sleep();
        driver.findElement(By.xpath("/html/body/div[7]/div/div[10]/div[1]/div[1]/div[2]/p/a")).click();
        recursionCount++;
        if (recursionCount <= 10) {
            recursion();
        }
    }


    public void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }

}
