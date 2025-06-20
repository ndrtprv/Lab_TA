package com.fluffy.universe.controllers;

// Generated by Selenium IDE
import io.javalin.Javalin;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

@Disabled("Тимчасово вимкнений через нестабільність у CI")
public class TestUniverseTest {

  private WebDriver driver;
  JavascriptExecutor js;
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
    options.addArguments("--remote-allow-origins=*");
    // Stop comment

    driver = new ChromeDriver(options); // Comment options on local machine, and uncomment before push
    js = (JavascriptExecutor) driver;
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testUniverse() {
    driver.get("http://127.0.0.1:7000/");
    driver.manage().window().setSize(new Dimension(1920, 1040));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    WebElement signup = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/header/nav/ul/li[2]/a")
    ));
    signup.click();
    driver.findElement(By.id("sign-up__first-name")).click();
    driver.findElement(By.id("sign-up__first-name")).sendKeys("Andrii");
    driver.findElement(By.id("sign-up__last-name")).click();
    driver.findElement(By.id("sign-up__last-name")).sendKeys("Toporov");
    driver.findElement(By.id("sign-up__email")).click();
    driver.findElement(By.id("sign-up__email")).sendKeys("ndrtprv@gmail.com");
    driver.findElement(By.id("sign-up__password")).click();
    driver.findElement(By.id("sign-up__password")).sendKeys("tOR1917Len$");
    driver.findElement(By.id("sign-up__confirm-password")).click();
    driver.findElement(By.id("sign-up__confirm-password")).sendKeys("tOR1917Len$");
    driver.findElement(By.cssSelector(".form__button")).click();

    WebElement okButton = new WebDriverWait(driver, Duration.ofSeconds(120))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".alert__button")));
    okButton.click();

    clickDropdownItemText(driver, "My profile");

    driver.findElement(By.id("account__gender")).click();
    {
      WebElement dropdown = driver.findElement(By.id("account__gender"));
      dropdown.findElement(By.xpath("//option[. = 'Male']")).click();
    }
    driver.findElement(By.id("account__address")).click();
    driver.findElement(By.id("account__address")).sendKeys("Sumy");
    driver.findElement(By.cssSelector(".container--flex")).click();
    driver.findElement(By.cssSelector(".button--fluid")).click();

    WebElement okButton1 = new WebDriverWait(driver, Duration.ofSeconds(120))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".alert__button")));
    okButton1.click();

    clickDropdownItemText(driver, "Dashboard");

    driver.findElement(By.xpath("/html/body/header/nav/ul/li[1]/a")).click();
    driver.findElement(By.cssSelector(".post-form__title")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".post-form__title"));
      js.executeScript("if(arguments[0].contentEditable === 'true') {arguments[0].innerText = 'Прогрес щодо роботи'}", element);
    }
    driver.findElement(By.cssSelector(".post-form__description")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".post-form__description"));
      js.executeScript("if(arguments[0].contentEditable === 'true') {arguments[0].innerText = 'Мій прогрес щодо виконання лабораторних з автоматизації тестування: зроблено 5 лабораторних, 1 лабораторна в процесі, 8 лабораторних лишається.'}", element);
    }
    driver.findElement(By.cssSelector(".post-form__button:nth-child(2)")).click();

    WebElement okButton2 = new WebDriverWait(driver, Duration.ofSeconds(120))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".alert__button")));
    okButton2.click();

    driver.findElement(By.xpath("/html/body/main/div/div[1]/div/a")).click();
    driver.findElement(By.cssSelector(".comment-form__textarea")).click();
    driver.findElement(By.cssSelector(".comment-form__textarea")).sendKeys("Гарна робота!");
    driver.findElement(By.cssSelector(".comment-form__button:nth-child(1)")).click();
    driver.findElement(By.cssSelector(".comment-form__textarea")).click();
    driver.findElement(By.cssSelector(".comment-form__textarea")).sendKeys("Продовжуй далі!");
    driver.findElement(By.cssSelector(".comment-form__button:nth-child(1)")).click();

    clickDropdownItemXPath(driver, "/html/body/header/nav/ul/li[2]/div/ul/li[2]/a");

    driver.findElement(By.linkText("Прогрес щодо роботи")).click();

    clickDropdownItemXPath(driver, "/html/body/header/nav/ul/li[2]/div/ul/li[3]/form/button");
  }

  public void openDropdown(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown__button")));
    new Actions(driver).moveToElement(dropdown).perform();
  }

  public void clickDropdownItemText(WebDriver driver, String text) {
    openDropdown(driver);
    //System.out.println("OK dropdown");
    WebElement item = new WebDriverWait(driver, Duration.ofSeconds(120))
            .until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
    //System.out.println("OK " + text);
    item.click();
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