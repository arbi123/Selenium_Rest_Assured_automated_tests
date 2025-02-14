package Elements;

import Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KlasaELinqeveElements {

    public KlasaELinqeveElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    @FindBy(xpath = "(//div[@class='header-right'])[1]")
    public WebElement elementsMenu;

    @FindBy(xpath = "//span[normalize-space()='Broken Links - Images']")
    public WebElement brokenLink;

    @FindBy(tagName = "a")
    public List<WebElement> links;

    @FindBy(tagName = "img")
    @CacheLookup
    public List<WebElement> images;

}
