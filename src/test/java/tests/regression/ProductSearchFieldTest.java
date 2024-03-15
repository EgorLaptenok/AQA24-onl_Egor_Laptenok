package tests.regression;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseObject.BaseTest;
import pageObject.wildberries.HomePage;
import pageObject.wildberries.ProductPage;
import pageObject.wildberries.SearchPage;
import pageObject.wildberries.elements.HeaderElement;

public class ProductSearchFieldTest extends BaseTest {
    HeaderElement headerElement;
    SearchPage searchPage;
    HomePage homePage;
    ProductPage productPage;

    @BeforeTest()
    public void precondition() {
        headerElement = new HeaderElement();
        searchPage = new SearchPage();
        homePage = new HomePage();
        homePage.open();
        productPage = new ProductPage();
    }

    @Test(priority = 1)
    public void searchByProductNameTest() {
        headerElement.enterValue("Футболка");
        searchPage.productFoundSuccessfully("Футболка");
    }

    @Test(priority = 2)
    public void searchByProductArticleTest() {
        headerElement.enterValue("26298238");
        productPage.foundCodeProduct("26298238");
    }

    @Test(priority = 3)
    public void minAcceptableValueTest() {
        headerElement.enteringMinCharValue();
        searchPage.checkingProductsFound();
    }

    @Test(priority = 4)
    public void maxAcceptableValueTest() {
        headerElement.enteringMaxCharValue();
        searchPage.checkingProductsNotFound();
    }

    @Test(priority = 5)
    public void specialCharactersTest() {
        headerElement.enterValue("*@_");
        searchPage.checkingProductsNotFound();
    }
}
