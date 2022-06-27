package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecureAreaPage {

    WebDriver driver;
    WebDriverWait wdwait;

    WebElement logoutButton;

    WebElement welcomeMessage;

    String urlSecureAreaPage;

    WebElement loggedInSecureArea;

    public SecureAreaPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    public WebElement getLogoutButton() {
        return logoutButton = driver.findElement(By.cssSelector(".icon-2x.icon-signout"));
    }

    public WebElement getWelcomeMessage() {

        return welcomeMessage = driver.findElement(By.className("subheader"));
    }

    public String getUrlSecureAreaPage() {

        return urlSecureAreaPage = driver.getCurrentUrl();
    }

    public WebElement getLoggedInSecureArea() {
        return loggedInSecureArea = driver.findElement(By.id("flash"));
    }

    /////// methods

    public void clickOnLogoutButton(){

        this.getLogoutButton().click();
    }


}
