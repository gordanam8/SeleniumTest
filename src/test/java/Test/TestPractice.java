package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPractice extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginPageURL);
    }

    @Test(priority = 10)
    public void successfulLogin() throws InterruptedException {

        //Sign in using data from excel file
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();

        // Check if user is signed in

        Assert.assertTrue(secureAreaPage.getLogoutButton().isDisplayed());
        Assert.assertTrue(secureAreaPage.getWelcomeMessage().isDisplayed());


        //check if we are on expected page
        String expUrl = "https://the-internet.herokuapp.com/secure";
        Assert.assertTrue(expUrl.equals(secureAreaPage.getUrlSecureAreaPage()));
    }

    @Test(priority = 20)
    public void successfulLogoutAfterSuccessfulLoginWithValidCredentials() {
        //login with valid credential using data from excel file
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();
        //Sign out and returning to LoginPage
        secureAreaPage.clickOnLogoutButton();

        //confirm that Login Button exists
        Assert.assertTrue(elementVisibility(loginPage.getLoginButton()));

        //confirm that we are on expected url of Login Page
        String expUrl = "https://the-internet.herokuapp.com/login";
        Assert.assertTrue(expUrl.equals(loginPageURL));

        //confirm that Logout button doesn't exist
        boolean check = true;
        try {
            check = secureAreaPage.getLogoutButton().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);

    }

    @Test(priority = 30)
    public void unsuccessfulLoginWithInvalidUsernameAndValidPassword() {
        //read invalid username and valid password from excel file
        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login", i, 2);
            loginPage.insertUsername(invalidUsername);
            String validPassword = excelReader.getStringData("Login", 1, 1);
            loginPage.insertPassword(validPassword);
            loginPage.clickOnLoginButton();
        }
        Assert.assertTrue(loginPage.getUsername().isDisplayed());
        Assert.assertTrue(loginPage.getPassword().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        //check error message text
        String expMessage = "Your username is invalid!\n" +
                "×";
        System.out.println("1 " + loginPage.getErrorMessage().getText());
        Assert.assertTrue(expMessage.equals(loginPage.getErrorMessage().getText()));

        //confirm that we are on expected url of Login Page
        String expUrl = "https://the-internet.herokuapp.com/login";
        Assert.assertTrue(expUrl.equals(loginPageURL));


    }

    @Test(priority = 40)
    public void unsuccessfulLoginWithValidUsernameAndInvalidPassword() {
        //read valid username and invalid password from excel file
        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String validUsername = excelReader.getStringData("Login", 1, 0);
            loginPage.insertUsername(validUsername);
            String invalidPassword = excelReader.getStringData("Login", i, 3);
            loginPage.insertPassword(invalidPassword);
            loginPage.clickOnLoginButton();
        }
        //chek that all fields are still present
        Assert.assertTrue(loginPage.getUsername().isDisplayed());
        Assert.assertTrue(loginPage.getPassword().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        //check that error message is displayed
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());

        //confirm that we are on expected url of Login Page
        String expUrl = "https://the-internet.herokuapp.com/login";
        Assert.assertTrue(expUrl.equals(loginPageURL));

        //check error message text
        String expMessage = "Your password is invalid!\n" +
                "×";
        System.out.println("1 " + loginPage.getErrorMessage().getText());
        Assert.assertTrue(expMessage.equals(loginPage.getErrorMessage().getText()));

    }

    @Test(priority = 50)
    public void unsuccessfulLoginWithInvalidUsernameAndInvalidPassword() {
        //read invalid username and invalid password from excel file
        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login", i, 2);
            loginPage.insertUsername(invalidUsername);
            String invalidPassword = excelReader.getStringData("Login", i, 3);
            loginPage.insertPassword(invalidPassword);
            loginPage.clickOnLoginButton();
        }
        //chek that all fields are still present
        Assert.assertTrue(loginPage.getUsername().isDisplayed());
        Assert.assertTrue(loginPage.getPassword().isDisplayed());
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        //check that error message is displayed
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());

        //confirm that we are on expected url of Login Page
        String expUrl = "https://the-internet.herokuapp.com/login";
        Assert.assertTrue(expUrl.equals(loginPageURL));

        //check error message text
        String expMessage = "Your username is invalid!\n" +
                "×";
        System.out.println("1 " + loginPage.getErrorMessage().getText());
        Assert.assertTrue(expMessage.equals(loginPage.getErrorMessage().getText()));

    }

}
