package tests.regression;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObject.BaseTest;
import pageObject.wildberries.AddressPage;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.HomePage;
import pageObject.wildberries.elements.HeaderElement;

public class AddAddressTests extends BaseTest {
    HomePage homePage;
    HeaderElement headerElement;
    BasketPage basketPage;
    AddressPage addressPage;

    @BeforeTest
    public void precondition() {
        homePage = new HomePage();
        headerElement = new HeaderElement();
        basketPage = new BasketPage();
        homePage.open();
        homePage.addButtonProductToBasket(1, 1);
        headerElement.clickButtonBasket();
        basketPage.clickDeliveryAddress();
        addressPage = new AddressPage();
    }

    @Test(priority = 1)
    public void addressAddedSuccessfullyTest() {
        addressPage.sendKeysSearch("Минск, Минск, улица Червякова, 5")
                .clickAddress()
                .clickButtonSelect();
        basketPage.eqAddress("Минск, Минск, улица Червякова, 5");
    }

    @Test(priority = 2)
    public void nonExistentAddressTest() {
        basketPage.newAddAddress();
        addressPage.sendKeysSearch("ПаМинМоСанкт")
                .errorWrongAddress();
    }

    @Test(priority = 3)
    public void specialCharactersTest() {
        addressPage.sendKeysSearch("*^#^#")
                .errorWrongAddress();
    }
}
