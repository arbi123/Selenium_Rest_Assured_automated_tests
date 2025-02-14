package Elements;

import Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsElements {

    public AlertsElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//div[normalize-space()='Alerts, Frame & Windows']")
    public WebElement framedropbox;

    @FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-1']")
    public WebElement Alerts;

    @FindBy(css = "#alertButton")
    public WebElement alertButton;

    @FindBy(css = "#timerAlertButton")
    public WebElement alertTimerButton;

    @FindBy(css ="#confirmButton")
    public WebElement confirmButton;

    @FindBy(xpath = "//span[@id='confirmResult']")
    public WebElement confirmMessage;

    @FindBy(css = "#promtButton")
    public WebElement promtButton;

    @FindBy(xpath = "//span[@id='promptResult']")
    public WebElement promtMessage;


}
