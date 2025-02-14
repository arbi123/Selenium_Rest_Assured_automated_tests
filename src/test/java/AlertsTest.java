import Elements.AlertsElements;
import Utilities.BaseInformation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class AlertsTest {
    private final WebDriver driver = BaseInformation.getDriver();
   AlertsElements page = new AlertsElements();

   @BeforeClass
public void goToPage() throws InterruptedException {

       Thread.sleep(2000);
       page.framedropbox.click();
       Thread.sleep(2000);
       page.Alerts.click();

}

    @Test
    public void clickAlert() throws InterruptedException {
        Thread.sleep(3000);
        page.alertButton.click();
        Alert alert = driver.switchTo().alert();


        alert.accept();
    }
    @Test(dependsOnMethods = "clickAlert")
    public void delayedAlert(){
        page.alertTimerButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();


        WebElement footer = page.promtButton;
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
    }
    @Test(dependsOnMethods = "delayedAlert")
    public void confirmAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        page.confirmButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        Assert.assertTrue(page.confirmMessage.isDisplayed(), "Mesazhi nuk u shfaq!");
        System.out.println(page.confirmMessage.getText());
        Assert.assertTrue(page.confirmMessage.getText().contains("Cancel"), "Mesazhi nuk përmban 'Cancel'!");

        page.confirmButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertTrue(page.confirmMessage.isDisplayed(), "Mesazhi nuk u shfaq!");
        System.out.println(page.confirmMessage.getText());
        Assert.assertTrue(page.confirmMessage.getText().contains("Ok"), "Mesazhi nuk përmban 'Ok'!");
    }
    @Test(dependsOnMethods = "confirmAlert")
    public void promtAlert(){
        page.promtButton.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("arbi");
        alert.accept();
        Assert.assertTrue(page.promtMessage.getText().contains("arbi"));

    }

}
