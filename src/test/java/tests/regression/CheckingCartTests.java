package tests.regression;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObject.BaseTest;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.HomePage;
import pageObject.wildberries.elements.HeaderElement;

public class CheckingCartTests extends BaseTest {
    HomePage homePage;
    HeaderElement headerElement;
    BasketPage basketPage;

    @BeforeTest
    public void precondition() {
        homePage = new HomePage();
        headerElement = new HeaderElement();
        homePage.open();
        homePage.addButtonProductToBasket(1, 1);
        headerElement.clickButtonBasket();
        basketPage = new BasketPage();
    }

    @Test(priority = 1)
    public void increaseQuantityTest() {
        basketPage.clickValuePlus();
    }

    @Test(priority = 2)
    public void reduceQuantityTest() {
        basketPage.clickValueMinus();
    }

    @Test(priority = 3)
    public void enterValueTest() {
        basketPage.enterValue(10);
    }

    @Test(priority = 4)
    public void removeItemsTest() {
        basketPage
                .clickClearBasket()
                .emptyBasket();
    }

}
