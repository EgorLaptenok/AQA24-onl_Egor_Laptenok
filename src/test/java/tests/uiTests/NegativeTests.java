package tests.uiTests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObject.BaseTest;
import pageObject.wildberries.HomePage;
import pageObject.wildberries.LoginPage;
import pageObject.wildberries.elements.HeaderElement;

public class NegativeTests extends BaseTest {
    HomePage homePage;
    HeaderElement headerElement;
    LoginPage loginPage;

    @BeforeTest
    public void precondition() {
        homePage = new HomePage();
        headerElement = new HeaderElement();
        loginPage = new LoginPage();
        homePage.open("https://www.wildberries.ru/");
    }

    @Test(priority = 1)
    public void useIncorrectDataTest() {
        headerElement.clickForEnter();
        loginPage
                .enterPhone("00000")
                .clickButtonCode()
                .correctErrorMessage();
        headerElement.returnHomePage();
    }

    @Test(priority = 2)
    public void inputDataAboveLimit() {
        headerElement.clickForEnter();
        loginPage
                .enterPhone("+37529000000000000000")
                .successfullyTrimmedNumber();
        headerElement.returnHomePage();
    }

    @Test(priority = 3)
    public void reproductionDefect() {
        headerElement.uploadVideoToSearch("testVideo.mp4");
    }
}
