package tests.smoke;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObject.BaseTest;
import pageObject.wildberries.AddressPage;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.HomePage;
import pageObject.wildberries.SearchPage;
import pageObject.wildberries.elements.HeaderElement;

public class EndToEndTests extends BaseTest {
    HomePage homePage;
    HeaderElement headerElement;
    SearchPage searchPage;
    BasketPage basketPage;
    AddressPage addressPage;

    @BeforeTest
    public void precondition() {
        homePage = new HomePage();
        homePage.open("https://www.wildberries.ru/");
        headerElement = new HeaderElement();
        searchPage = new SearchPage();
        basketPage = new BasketPage();
        addressPage = new AddressPage();
    }

    @Test(priority = 1)
    public void productSearch() {
        headerElement.enterValue("Футболка");
        searchPage.checkingProductsFound();
    }

    @Test(priority = 2)
    public void addToCart() {
        searchPage
                .addButtonProductToBasket(1)
                .callPopupMessage();
    }

    @Test(priority = 3)
    public void cart() {
        headerElement.clickButtonBasket();
        basketPage.compareProductWithCart("Футболка");
    }

    @Test(priority = 4)
    public void addAddress() {
        basketPage.clickDeliveryAddress();
        addressPage
                .sendKeysSearch("Минск, Минск, улица Червякова, 5")
                .clickAddress()
                .clickButtonSelect();
        basketPage.eqAddress("Минск, Минск, улица Червякова, 5");
    }
}
