import Elements.LoginPageElements;
import Utilities.BaseInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private final WebDriver driver = BaseInformation.getDriver();
    LoginPageElements page = new LoginPageElements();

    @BeforeClass
    public void setUp(){
        driver.get("https://al.ebileta.al/biglietteria/anag/richiestaAnag.do?nextUrl=%2flistaEventiPub.do");
      page.cookiesButton.click();
    }

    @Test(priority = 1)
    public void inputData(){
        page.emailTextBox.sendKeys("topiarbi@gmail.com");
        page.passwordTextBox.sendKeys("Arbi1234");
    }
    @Test(priority = 2)
    public void clickingCaptcha(){
        Actions actions = new Actions(driver);
        actions
                .sendKeys("\t")
                .sendKeys("\n")
                .build()
                .perform();
    }


    @Test(priority = 3)
    public void login() throws InterruptedException {
        Thread.sleep(5000);
        page.loginButton.click();
    }

    @Test
    public void apiTestinCaptcha(){

    }
}
