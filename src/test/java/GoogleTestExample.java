
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement ;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class GoogleTestExample {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
       System.setProperty("webdriver.gecko.driver",
        "/home/shilpa/Downloads/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public static void testGoogle() throws Exception{
        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        WebElement search  = driver.findElement(By.xpath("//input[@type='text'  and @name = 'q']"));
        search.sendKeys("Selenium java");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Execution complete");
        driver.quit();
    }

}
