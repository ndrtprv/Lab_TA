package seleniumwebdriver.tests;

import com.google.api.Service;
import io.javalin.Javalin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumwebdriver.pages.SignupPage;
import seleniumwebdriver.utils.ScreenshotUtil;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupTest {
    private WebDriver driver;
    static Javalin app;

    // Comment these lines on your local machine, and uncomment before push
    @BeforeAll
    public static void startServer() {
        app = Javalin.create().start(7000);
    }
    // Stop comment

    @BeforeEach
    public void setUp() {
        // Comment these lines on your local machine, and uncomment before push
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
        // Stop comment

        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");

        driver = new ChromeDriver(options); // Comment options on local machine, and uncomment before push
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void successfulSignup() {
        driver.get("http://127.0.0.1:7000/");

        ScreenshotUtil.take(driver, "home_page");

        WebElement signup = new WebDriverWait(driver, Duration.ofSeconds(120)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("header__link--primary")
        ));
        signup.click();

        SignupPage signupPage = new SignupPage(driver);
        signupPage.login("asdf", "asdf","asdf.asdf@example.com", "Asdf@1234", "Asdf@1234");

        ScreenshotUtil.take(driver, "signup_success");

        WebElement alertElement = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("alert--visible"))
        );

        assertTrue(alertElement.isDisplayed());

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