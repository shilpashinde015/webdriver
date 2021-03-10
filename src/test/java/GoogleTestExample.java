
import org.openqa.selenium.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GoogleTest {
    WebDriver driver;
    @Test
    @Ignore
    @Parameters("browser")
    public void verifyTitle(String browsername) throws Exception{
        String expectedName = "Welcome: Mercury Tours";
        String actualName = "";
        driver = initModule.launch(browsername);
        driver.get(initModule.getBaseUrl());
        Thread.sleep(2000);
        actualName = driver.getTitle();
        String tagName = driver.findElement(By.name("userName")).getTagName();
        System.out.println("tagName : " + tagName);
       // System.out.println("actualName" + actualName);
        Assert.assertEquals(expectedName,actualName);

        initModule.closeBrowser(driver);
    }

    @Test
    @Ignore
    @Parameters("browser")
    public void verifyPopup(String browsername) throws Exception{
        driver = initModule.launch(browsername);
        //driver.wait(1000);
        Thread.sleep(2000);
        driver.get("http://www.popuptest.com/popuptest2.html");
        driver.quit();
    }
    @Test
    @Ignore
    @Parameters("browser")
    public void cssSelector(String browsername) throws Exception {
        driver = initModule.launch(browsername);
        driver.get("http://www.facebook.com");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("//input[@name='email']")).sendKeys("username");

    }
    @Test
    @Parameters("browser")
    public void automateIndiaToday(String browsername) throws Exception {
        driver = initModule.launch(browsername);
        driver.get("https://www.indiatoday.in");
        // driver.manage().window().maximize();
       // Random r = new Random();

        driver.findElement(By.xpath("//button[@class='izooto-optin--cta izooto-optin--cta-later izooto-optin--mr-10'][@onclick='_izooto.closeDialog();']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.className("all-menu")).click();
        List<WebElement> menu_item = driver.findElements(By.xpath("/html/body/div[1]/header/section/div/div[2]/div[2]/nav/div/ul[1]/li/a"));
        //clickItem(menu_item, menu_item.size());
        int selectNum = random_fun(menu_item.size());
        Thread.sleep(1000);
        menu_item.get(selectNum).click();

        // int selectNum = r.nextInt(menu_item.size());
        // System.out.println("SelectNUm :" + selectNum);
        //System.out.println("Selected Item" + menu_item.get(selectNum).getText());
        // Thread.sleep(1000);

        // menu_item.get(selectNum).click();
        boolean status = checkStaleelement(menu_item.get(random_fun(menu_item.size())));
        if (status) {
            driver.navigate().back();
            int Num = random_fun(menu_item.size());
            System.out.println("Num" + Num);
            Thread.sleep(1000);

            menu_item.get(Num).click();

           // clickItem(menu_item, num);
            try {
                String ps = driver.getPageSource().substring(0, 100);
                File file = new File("/home/shilpa/IdeaProjects/Testfirefox");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(ps);
                bw.close();

                System.out.println("Done");
                String pgurl = driver.getCurrentUrl();
                sendMail(ps, pgurl);
            } catch (IOException e) {
                e.printStackTrace();


            }



/*
        List<WebElement> menu_item=   driver.findElements(By.xpath("/html/body/div[1]/header/section/div/div[2]/div[2]/nav/div/ul[1]/li/a"));
        for(int i = 0;i<menu_item.size();i++){
            int selectNum = r.nextInt(menu_item.size());
            //WebElement element = menu_item.get(i);
            System.out.println("selectNum: " + selectNum);
          //  WebElement element = menu_item.get(selectNum);
            //System.out.println("elements: " + element.getText());
            //String innerHtml = element.getAttribute("innerHTML");
            //System.out.println("Values from dropdown: " + innerHtml);
            //System.out.println("");
            //Thread.sleep(1000);

          menu_item.get(selectNum).click();*/


            // System.out.println("getText" + element.getText());
            // System.out.println("elements" + element);
            // String innerHtml = element.getAttribute("innerHTML");
            //System.out.println("Values from dropdown " + innerHtml);
            //Thread.sleep(1000);

            // }


            initModule.closeBrowser(driver);
        }


    }

    private void sendMail(String ps, String pgurl) {

    }

    private void clickItem(List<WebElement> menu_item, int size) {
        int selectNum = random_fun(size);
        menu_item.get(selectNum).click();
    }

    private int random_fun(int size) {
        Random r = new Random();
        int num = r.nextInt(size);
        return num;
    }

    private boolean checkStaleelement(WebElement webElement) {
        try{
            webElement.isEnabled();
            return false;
        }catch(StaleElementReferenceException e){
            return true;
        }
    }
}
