package Elements;

import Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageElements {

    public LoginPageElements(){
        PageFactory.initElements(BaseInformation.getDriver(),this);
    }
    @FindBy(css = "#useridInput")
    public WebElement emailTextBox;

    @FindBy(css = "#passwordInput")
    public WebElement passwordTextBox;

    @FindBy(css = "#loginbutton")
    public WebElement loginButton;

    @FindBy(css = ".textmiddle")
    public WebElement cookiesButton;
}
