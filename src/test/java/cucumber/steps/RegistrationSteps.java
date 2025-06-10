package cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationSteps {

    public static WebDriver driver;
    static Javalin app;

    @Before
    public void setUp() {
        // Comment these lines on your local machine, and uncomment before push
        app = Javalin.create().start(7000);

        ChromeOptions options = getChromeOptions();
        // Stop comment

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        } else {
            // у Linux (наприклад, GitHub Actions) ChromeDriver уже встановлено в PATH
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        }

        driver = new ChromeDriver(options); // Comment options on local machine, and uncomment before push
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Comment these lines on your local machine, and uncomment before push
    @NotNull
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disk-cache-size=1");
        options.addArguments("--media-cache-size=1");
        options.addArguments("--incognito");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--aggressive-cache-discard");
        return options;
    }
    // Stop comment

    @Given("I navigate to the Registration page")
    public void openSignup() {
        System.out.println("Navigating to page: Registration");

        driver.get("http://127.0.0.1:7000/");

        WebElement signup = new WebDriverWait(driver, Duration.ofSeconds(120)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("header__link--primary")));
        signup.click();
    }

    @When("I fill in name with {string}")
    public void fillName(String firstName) {
        System.out.println("Filling first name");
        driver.findElement(By.id("sign-up__first-name")).sendKeys(firstName);
    }

    @And("I fill in surname with {string}")
    public void fillSurname(String lastName) {
        System.out.println("Filling last name");
        driver.findElement(By.id("sign-up__last-name")).sendKeys(lastName);
    }

    @And("I fill in email with {string}")
    public void fillEmail(String email) {
        System.out.println("Filling email");
        driver.findElement(By.id("sign-up__email")).sendKeys(email);
    }

    @And("I fill in password with {string}")
    public void fillPassword(String password) {
        System.out.println("Filling password");
        driver.findElement(By.id("sign-up__password")).sendKeys(password);
    }

    @And("I fill in confirm password with {string}")
    public void fillConfirmation(String confirmation) {
        System.out.println("Filling confirmation of password");
        driver.findElement(By.id("sign-up__confirm-password")).sendKeys(confirmation);
    }

    @And("I click on the Sign Up button")
    public void clickSignUp() {
        System.out.println("Clicking Sign up");
        driver.findElement(By.cssSelector(".form__button")).click();
    }

    @Then("I should be successfully registered")
    public void checkRegistrationSuccess() {
        System.out.println("Checking successful registration indication");

        WebElement successAlert = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("alert--visible")));
        Assertions.assertTrue(successAlert.isDisplayed());
    }

    @And("I should land on the Home page")
    public void checkRedirectToHome() {
        System.out.println("Checking landing page");

        new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.urlContains("/"));
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl != null;
        Assertions.assertTrue(currentUrl.contains("/"));
    }

    @And("I should see success message as {string}")
    public void checkSuccessMessage(String expectedMessage) {
        System.out.println("Checking success message");

        WebElement message = driver.findElement(By.className("alert__heading"));
        Assertions.assertEquals(expectedMessage, message.getText().trim());

        WebElement okButton = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".alert__button")));
        okButton.click();
    }

    @And("I should see Dashboard and Logout links")
    public void checkDashboardAndLogoutLinks() {
        System.out.println("Checking presence of Dashboard and Logout links");

        WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown__button")));
        new Actions(driver).moveToElement(dropdown).perform();

        WebElement dashboardLink = driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/div/ul/li[2]/a"));
        WebElement logoutLink = driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/div/ul/li[3]/form/button"));

        Assertions.assertTrue(dashboardLink.isDisplayed());
        Assertions.assertTrue(logoutLink.isDisplayed());

        logoutLink.click();
    }

    @Then("I should see {string} message on Registration page on {string}")
    public void checkFormErrorMessage(String expectedError, String xpath) {
        System.out.println("Checking form error message or Sign up page");

        WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        Assertions.assertEquals(expectedError, errorElement.getText().trim());
    }

    @Then("I should not be able to submit the Registration form")
    public void checkFormNotSubmitted() {
        System.out.println("Checking that form is not submitted");

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl != null;
        Assertions.assertTrue(currentUrl.contains("/sign-up"));
    }
}