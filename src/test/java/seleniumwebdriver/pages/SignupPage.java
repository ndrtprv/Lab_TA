package seleniumwebdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumwebdriver.utils.ScreenshotUtil;

public class SignupPage {
    WebDriver driver;

    @FindBy(id = "sign-up__first-name")
    WebElement firstNameField;
    @FindBy(id = "sign-up__last-name")
    WebElement lastNameField;
    @FindBy(id = "sign-up__email")
    WebElement emailField;
    @FindBy(id = "sign-up__password")
    WebElement passwordField;
    @FindBy(id = "sign-up__confirm-password")
    WebElement confirmationField;
    @FindBy(className = "button--primary")
    WebElement signupButton;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String name, String surname, String email, String pass, String confirmation) {
        ScreenshotUtil.take(driver, "empty_fields");

        firstNameField.sendKeys(name);
        lastNameField.sendKeys(surname);
        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        confirmationField.sendKeys(confirmation);

        ScreenshotUtil.take(driver, "entered_data");

        signupButton.click();
    }
}