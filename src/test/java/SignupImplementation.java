import com.thoughtworks.gauge.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SignupImplementation {

    private WebDriver driver;

    @BeforeScenario
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterScenario
    public void tearDown() {
        driver.quit();
    }

    @Step("Open the <address> page.")
    public void openHomepage(String address) {
        driver.get(address);
    }

    @Step("Check if there is a greeting object with <xpath> path")
    public void checkGreeting(String xpath) {
        boolean exists;
        try {
            driver.findElement(By.xpath(xpath));
            exists = true;
        } catch (NoSuchElementException e) {
            exists = false;
        }
        assertTrue(exists, "Element with xpath '" + xpath + "' not found on page");
    }

    @Step("Open <signupXPath> page and check the object with <id> id")
    public void openSignupAndCheckObject(String signupXPath, String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement signup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(signupXPath)
        ));
        signup.click();

        boolean exists;
        try {
            driver.findElement(By.id(id));
            exists = true;
        } catch (NoSuchElementException e) {
            exists = false;
        }
        assertTrue(exists, "Element with id '" + id + "' not found on page");
    }

    @Step("Enter the data into fields <signupData>")
    public void enterData(Table signupData) {
        for (TableRow row : signupData.getTableRows()) {
            String firstName = row.getCell("name");
            String lastName = row.getCell("surname");
            String email = row.getCell("email");
            String password = row.getCell("password");
            String confirmation = row.getCell("confirmation");

            driver.findElement(By.id("sign-up__first-name")).sendKeys(firstName);
            boolean isVisible;
            try {
                WebElement element = driver.findElement(By.xpath("/html/body/div[1]/main/div/form/div[2]/div[1]/p"));
                isVisible = element.isDisplayed();
            } catch (NoSuchElementException e) {
                // Елемент не знайдено в DOM — це теж означає "не видно"
                isVisible = false;
            }
            assertFalse(isVisible, "Element with target xpath is visible, but should not be.");

            driver.findElement(By.id("sign-up__last-name")).sendKeys(lastName);
            try {
                WebElement element = driver.findElement(By.xpath("/html/body/div[1]/main/div/form/div[2]/div[2]/p"));
                isVisible = element.isDisplayed();
            } catch (NoSuchElementException e) {
                // Елемент не знайдено в DOM — це теж означає "не видно"
                isVisible = false;
            }
            assertFalse(isVisible, "Element with target xpath is visible, but should not be.");

            driver.findElement(By.id("sign-up__email")).sendKeys(email);
            try {
                WebElement element = driver.findElement(By.xpath("/html/body/div[1]/main/div/form/div[3]/p"));
                isVisible = element.isDisplayed();
            } catch (NoSuchElementException e) {
                // Елемент не знайдено в DOM — це теж означає "не видно"
                isVisible = false;
            }
            assertFalse(isVisible, "Element with target xpath is visible, but should not be.");

            driver.findElement(By.id("sign-up__password")).sendKeys(password);
            try {
                WebElement element = driver.findElement(By.xpath("/html/body/div[1]/main/div/form/div[4]/p"));
                isVisible = element.isDisplayed();
            } catch (NoSuchElementException e) {
                // Елемент не знайдено в DOM — це теж означає "не видно"
                isVisible = false;
            }
            assertFalse(isVisible, "Element with target xpath is visible, but should not be.");

            driver.findElement(By.id("sign-up__confirm-password")).sendKeys(confirmation);
            try {
                WebElement element = driver.findElement(By.xpath("/html/body/div[1]/main/div/form/div[5]/p"));
                isVisible = element.isDisplayed();
            } catch (NoSuchElementException e) {
                // Елемент не знайдено в DOM — це теж означає "не видно"
                isVisible = false;
            }
            assertFalse(isVisible, "Element with target xpath is visible, but should not be.");
        }
    }

    @Step("Click Signup and check the greeting message")
    public void verifySignup() {
        driver.findElement(By.cssSelector(".form__button")).click();

        boolean isVisible;
        try {
            WebElement modalWindow = driver.findElement(By.xpath("/html/body/div[2]"));
            isVisible = modalWindow.isDisplayed();
        } catch (NoSuchElementException e) {
            isVisible = false;
        }
        assertTrue(isVisible, "Element should be visible.");

        WebElement okButton = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".alert__button")));
        okButton.click();

        clickDropdownItemXPath(driver, "/html/body/header/nav/ul/li[2]/div/ul/li[3]/form/button");
    }

    public void openDropdown(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown__button")));
        new Actions(driver).moveToElement(dropdown).perform();
    }

    public void clickDropdownItemXPath(WebDriver driver, String text) {
        openDropdown(driver);
        //System.out.println("OK dropdown");
        WebElement item = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
        //System.out.println("OK " + text);
        item.click();
    }

}
