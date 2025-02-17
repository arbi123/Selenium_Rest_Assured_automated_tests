import Elements.LoginPageElements;
import Elements.SliderElements;
import Utilities.BaseInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SliderTest {

    private final WebDriver driver = BaseInformation.getDriver();
    SliderElements page = new SliderElements();
    List<String> wordList;
    Actions actions = new Actions(driver);


    @BeforeClass
    public void setup(){
    driver.get("https://letcode.in/slider");
    }
    @Test(enabled = false)
    public void getText(){
        page.getCountriesButton.click();

        String bigString =page.textBox.getText();
        wordList = Arrays.asList(bigString.split(" - "));
        Assert.assertEquals(getSliderNumber(),listsize(),"Numri i shteteve esht i ndrryshem me numrin qe dhame tek slider");
        Assert.assertEquals(10,listsize());

        System.out.println(listsize());
        printlist();

    }

    public int listsize(){
        String bigString =page.textBox.getText();
        List<String> wordList = Arrays.asList(bigString.split(" - "));
        return wordList.size();
    }

    public void printlist(){
        for (String word : wordList) {
            System.out.println(word);
        }
    }

    public int getSliderNumber(){
        String text = page.numberText.getText();
        String  numberStr = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberStr);

    }
     @Test(enabled = true)
    public void looptest() throws InterruptedException {
        for(int i=-347;i<360;i=i+15){
            sliderMoveby1(i);
            Thread.sleep(1000);
            page.getCountriesButton.click();
            Assert.assertEquals(getSliderNumber(),listsize());

        }
    }


    public void sliderMoveby1(int x){
        actions.dragAndDropBy(page.slider,x,0)
                .release()
                .build().perform();
        System.out.println(getSliderNumber());



    }
    @AfterClass
    public void quit(){
        driver.quit();
    }
}
