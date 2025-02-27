package Utilities;

import Globals.Globals;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseInformation {
    private static WebDriver driver;
    private static WebDriverWait wait;


    public static BaseInformation getBaseInformation() {
        return new BaseInformation();
    }


    public static WebDriver getDriver() {


        if (driver == null) {
            String browserType = Globals.browserType.toLowerCase();

            switch (browserType) {

                case "chrome" -> {

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
//                    options.setHeadless(true);
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arbi.topi\\IdeaProjects\\firstProject\\src\\main\\resources\\chromedriver.exe");
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().window().maximize();
                    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                }

                case "firefox" -> {
                    System.out.println("Do nothing ");
                }
            }
        }

        return driver;
    }
    // Method to get an available port dynamically


    public String getScreenShot() throws IOException {
        String timeStamp = new SimpleDateFormat("HHmm_ddMM_yyss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+timeStamp+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+ timeStamp +".png";
    }
    public static void waitUntilElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilElementClickable(WebElement el) {
        wait.until(ExpectedConditions.elementToBeClickable(el));
    }
}