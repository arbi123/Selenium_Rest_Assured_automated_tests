import Elements.FaqjaKryesoreElements;
import Utilities.BaseInformation;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FaqjaKryesoreTest {
    private final WebDriver driver = BaseInformation.getDriver();
     FaqjaKryesoreElements page= new FaqjaKryesoreElements();

     @BeforeClass
     public void goToPage() throws InterruptedException {
         driver.get("https://demoqa.com/");
         Thread.sleep(2000);
     }


    @Test
    public void loopTest(){
        WebElement footer = driver.findElement(By.tagName("footer"));
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
        for(WebElement in : page.faqetEtestimit){
            String elemtni = in.getText();
            if(elemtni.contains("Forms")){
                in.click();
                break;
            }
        }
    }

    @Test(dependsOnMethods = "loopTest")
    public void goToPractice() throws InterruptedException {
        Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/forms");
        Assert.assertTrue(page.practiceForm.isDisplayed());
    page.practiceForm.click();
    Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/automation-practice-form");
        WebElement footer = page.nameForm;
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
    }


    @Test(dependsOnMethods  ="goToPractice")
    public void fillForm()  {
       page.nameForm.sendKeys("arbi");
       page.lastNameForm.sendKeys("topi");
       page.emailForm.sendKeys("arbi.topi@gmail.com");
       page.mobileNumberForm.sendKeys("0695654618");
       page.genderPick("Male");
     }


    @Test(dependsOnMethods = "fillForm")
    public void selectingDate(){
         page.birthForm.click();
      Select month = new Select(page.monthBirthForm);
      month.selectByVisibleText("June");
        Select year  = new Select(page.yearBirthForm);
        year.selectByVisibleText("2002");
        page.dayBithForm.click();
    }

    @Test(dependsOnMethods = "selectingDate")
    public void selectingSubject(){
         page.subjectForm.click();
         page.subjectForm.sendKeys("En");
         page.subjectForm.sendKeys(Keys.TAB);
    }

    @Test(dependsOnMethods = "selectingSubject")
    public void clickBoxHobbies()  {
        page.sportsHobbies.click();
        page.readingHobbies.click();
        page.musicHobbies.click();
    }

    @Test(dependsOnMethods = "clickBoxHobbies")
    public void uploadPhoto(){
        String filePath = System.getProperty("user.home") + "\\Downloads\\Order.png"; // Windows
        page.uploadPicture.sendKeys(filePath);
    }

    @Test(dependsOnMethods = "uploadPhoto")
    public void addressFill(){
        String longText = "A".repeat(1000);
        page.addressField.clear();
        page.addressField.sendKeys(longText);
    }
    @Test(dependsOnMethods = "addressFill")
    public void selectStateandCity(){

        Assert.assertFalse(page.selectCity.isSelected());
        page.selectState.click();
        Actions actions = new Actions(driver);

        actions.sendKeys("\uE015")
                .sendKeys("\uE007")
                .perform();

        page.selectCity.click();

        actions.sendKeys("\uE015")
                .sendKeys("\uE007")
                .perform();



    }
    @Test(dependsOnMethods = "selectStateandCity")
    public void clickSubmit(){
         page.submitButton.click();
    }

    @Test(dependsOnMethods = "clickSubmit")
    public void verifyConfirmPage(){
         Assert.assertTrue(page.confirmTitle.isDisplayed());

        WebElement fixedBan = driver.findElement(By.id("fixedban"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='none';", fixedBan);
         page.closeButton.click();
    }

//    @AfterClass
//    public void quitDriver()  {
//       driver.quit();
//    }

}
