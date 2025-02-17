package Elements;

import Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderElements {

    public SliderElements(){
        PageFactory.initElements(BaseInformation.getDriver(),this);
    }
    @FindBy(css=".button.is-primary")
    public WebElement getCountriesButton;

    @FindBy(css = ".has-text-primary-light")
    public WebElement textBox;

    @FindBy(css = "h1[class='subtitle has-text-info']")
    public WebElement numberText;

    @FindBy(css = "#generate")
    public WebElement slider;
}
