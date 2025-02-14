package Elements;

import Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FaqjaKryesoreElements {

    public FaqjaKryesoreElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }


    @FindBy(xpath = "//div[@class='card mt-4 top-card']")
    @CacheLookup
    public List<WebElement> faqetEtestimit;

    @FindBy(xpath = "(//li[@id='item-0'])[2]")
    @CacheLookup
    public WebElement practiceForm;

    @FindBy(css = "#firstName")
    public WebElement nameForm;
    @FindBy(css = "#lastName")
    public WebElement lastNameForm;

    @FindBy(css = "#userEmail")
    public WebElement emailForm;

    @FindBy(xpath = "(//div[@class='custom-control custom-radio custom-control-inline'])[1]")
    public WebElement maleGenderForm;
    @FindBy(xpath = "(//div[@class='custom-control custom-radio custom-control-inline'])[2]")
    public WebElement femaleGenderForm;
    @FindBy(xpath = "(//div[@class='custom-control custom-radio custom-control-inline'])[3]")
    public WebElement otherGenderForm;

    @FindBy(css = "#userNumber")
    public WebElement mobileNumberForm;

    @FindBy(xpath = "//input[@id='dateOfBirthInput']")
    @CacheLookup
    public WebElement birthForm;

    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    public WebElement monthBirthForm;

    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    public WebElement yearBirthForm;

    @FindBy(xpath = "//div[@aria-label='Choose Wednesday, June 5th, 2002']")
    public WebElement dayBithForm;

    @FindBy(css = "#subjectsWrapper .col-md-9.col-sm-12 input")
    public WebElement subjectForm;

    @FindBy(css = "label[for='hobbies-checkbox-1']")
    public WebElement sportsHobbies;
    @FindBy(css = "label[for='hobbies-checkbox-2']")
    public WebElement readingHobbies;
    @FindBy(css = "label[for='hobbies-checkbox-3']")
    public WebElement musicHobbies;

     @FindBy(css="#uploadPicture")
     public WebElement uploadPicture;

     @FindBy(css = "#currentAddress")
     public WebElement addressField;

     @FindBy(xpath = "(//div[contains(@class,'css-1wy0on6')])[2]")
     public WebElement selectState;

    @FindBy(xpath = "(//div[contains(@class,'css-1wy0on6')])[3]")
    public WebElement selectCity;
    @FindBy(id = "submit")
    public WebElement submitButton;


    @FindBy(css = "#example-modal-sizes-title-lg")
    public WebElement confirmTitle;

    @FindBy(id ="closeLargeModal")
    public WebElement closeButton;






    public void genderPick(String gender) {
        if (gender.contains("F")) {
             femaleGenderForm.click();
        } else if (gender.contains("O")) {
             otherGenderForm.click();
        } else if (gender.contains("M")) {
             maleGenderForm.click();
        }
    }
}


