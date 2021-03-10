import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class initModule {
    public static WebDriver driver;
    private static String baseurl = "http://demo.guru99.com/test/newtours/";
    @BeforeTest
    public static WebDriver launch(String browser) throws Exception{
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "/home/shilpa/Downloads/geckodriver");
            driver = new FirefoxDriver();
        }else{
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
    public static void closeBrowser(WebDriver driver){
        driver.quit();
    }
    public static String getBaseUrl(){
        return baseurl;
    }
    public void setBaseurl(String baseurl){
        this.baseurl = baseurl;
    }
}
