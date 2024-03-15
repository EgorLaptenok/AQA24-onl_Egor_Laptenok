package tests.uiTests;

import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObject.BaseTest;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.HomePage;
import pageObject.wildberries.SearchPage;
import pageObject.wildberries.elements.HeaderElement;
import pageObject.wildberries.elements.MessageElement;

@Log4j
public class PositiveTests extends BaseTest {
    HomePage homePage;
    HeaderElement headerElement;
    SearchPage searchPage;
    BasketPage basketPage;
    MessageElement messageElement;

    @BeforeTest
    public void precondition() {
        homePage = new HomePage();
        homePage.open();
        headerElement = new HeaderElement();
        searchPage = new SearchPage();
        basketPage = new BasketPage();
        messageElement = new MessageElement();
    }

    @Test(priority = 1)
    public void minCharValueTest() {
        headerElement.enteringMinCharValue();
        searchPage.checkingProductsFound();
    }

    @Test(priority = 2)
    public void maxCharValueTest() {
        headerElement.enteringMaxCharValue();
        searchPage.checkingProductsNotFound();
        headerElement.returnHomePage();
    }

    @Test(priority = 3)
    public void popupTest() {
        homePage.addButtonProductToBasket(1, 1);
        homePage.callPopupMessage();
    }

    @Test(priority = 4)
    public void creatingAnEntityTest() {
        headerElement.clickButtonBasket();
        basketPage
                .listProductVisibility()
                .clickClearBasket()
                .emptyBasket();
        headerElement.returnHomePage();
    }

    @Test(priority = 5)
    public void displayingDialogBox() {
        messageElement
                .clickSupportChat()
                .appearedHelperMessage()
                .clickCloseChat();
    }

    @Test(priority = 6)
    public void uploadFileTest() {
        headerElement.uploadFileToSearch("testImg.png");
        searchPage.checkingPhotoProductsFound();
    }

    @Test(priority = 7)
    public void basketTest() {
        headerElement.returnHomePage();
        homePage.addButtonProductToBasket(1, 1);
        headerElement.clickButtonBasket();
        basketPage
                .clickValuePlus()
                .clickValueMinus()
                .enterValue(10);
    }
}
