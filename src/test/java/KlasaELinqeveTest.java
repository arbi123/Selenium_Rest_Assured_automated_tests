import Elements.KlasaELinqeveElements;
import Utilities.BaseInformation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class KlasaELinqeveTest {
    private final WebDriver driver = BaseInformation.getDriver();

    KlasaELinqeveElements page = new KlasaELinqeveElements();

    @BeforeClass
    public void goToPage() throws InterruptedException {
        Thread.sleep(2000);
     page.elementsMenu.click();
     Thread.sleep(2000);
     page.brokenLink.click();
    }

    @Test
    public void gjetjaELinqeveTeThyera() {

        for (WebElement link : page.links) {
            String url = link.getAttribute("href");
          try{
            URL links = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) links.openConnection();
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.connect();
              System.out.println();


            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
            } else {
                System.out.println(url + " - " +httpURLConnection.getResponseCode()+  httpURLConnection.getResponseMessage() + " - " + "is a broken link");
            }
        } catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
        }


    }

@Test
    public void gjetjaELinqeveTeFotove(){

        for (WebElement img : page.images) {
            if (img != null) {
                String imageURL = img.getAttribute("src");

                if (imageURL == null || imageURL.isEmpty()) {
                    System.out.println("Image with empty src attribute found.");
                    continue;
                }

                try {
                    URL url = new URL(imageURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.connect();
                    int responseCode = connection.getResponseCode();

                    if (responseCode >= 400) {
                        System.out.println("Broken Image Found: " + imageURL + " | Status Code: " + responseCode);
                    } else {
                        System.out.println("Valid Image: " + imageURL);
                    }

                } catch (IOException e) {
                    System.out.println("Error checking image: " + imageURL);
                }
            }
        }

    }
@Test
public void testimg(){
        for(WebElement in : page.images) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean loaded = (Boolean) js.executeScript(
                    "return arguments[0].complete && " +
                            "typeof arguments[0].naturalWidth != 'undefined' && " +
                            "arguments[0].naturalWidth > 0", in);
            if(loaded){
            System.out.println("The image is loaded correctly.");
        } else {
        System.out.println("The image is not loaded properly.");
    }}

}


}





