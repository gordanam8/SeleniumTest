package Base;

import Pages.LoginPage;
import Pages.SecureAreaPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wdwait;
    public ExcelReader excelReader;

    public LoginPage loginPage;
    public String loginPageURL;

    public SecureAreaPage secureAreaPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver, wdwait);
        secureAreaPage = new SecureAreaPage(driver, wdwait);
        excelReader = new ExcelReader("TestData.xlsx"); // TODO Add valid file path
        loginPageURL = excelReader.getStringData("URLs", 1, 0);


    }

    public void visibilityWait(WebElement element) {

        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickabilityWait(WebElement element) {

        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean elementVisibility(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @AfterMethod
    public void removeCookies () {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
