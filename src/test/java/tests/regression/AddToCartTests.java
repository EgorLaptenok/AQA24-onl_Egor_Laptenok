package tests.regression;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObject.BaseTest;
import pageObject.wildberries.HomePage;
import pageObject.wildberries.ProductPage;

public class AddToCartTests extends BaseTest {
    HomePage homePage;
    ProductPage productPage;

    @BeforeTest
    public void precondition() {
        homePage = new HomePage();
        homePage.open();
        productPage = new ProductPage();
    }

    @Test(priority = 1)
    public void addOneProductTest() {
        homePage.addButtonProductToBasket(1, 1);
    }

    @Test(priority = 2)
    public void addTenProductsTest() {
        homePage.addButtonProductToBasket(10, 2);
    }

    @Test(priority = 3)
    public void addProductTest() {
        homePage.clickOnProduct(12);
        productPage.clickButtonAdd();
    }
}
