package pageObject.wildberries;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObject.BasePage;
import pageObject.wildberries.elements.HeaderElement;

public class SearchPage extends BasePage {
    private final By foundSearch = By.xpath("//span/span[contains(@data-link,'pluralize:pagerModel.totalItems')]");
    private final By notFoundSearch = By.xpath("//h1[contains(@class,'not-found-search__title')]");
    private final By photoSearchResult = By.xpath("//h1[contains(text(),'Поиск по фото')]");
    private final By popupMessage = By.xpath("//div[contains(@class, 'action-notification')]");
    private final By popupListOfSizes = By.xpath("//div[contains(@class,'popup-list-of-sizes')]");
    private final String popupModal = "//*[contains(@class,'popup popup-list')]";
    private final String popupAddToBasket = popupModal.concat("//*[@class='sizes-list__item']");
    private final By findNameProduct = By.xpath("//h1[contains(@class,'searching')]");

    public SearchPage checkingProductsFound() {
        waitForElementToBeVisible(foundSearch);
        Assert.assertTrue(getText(foundSearch).equals("товаров найдено") || getText(foundSearch).equals("товара найдено"), "Wrong massage");
        return this;
    }

    public SearchPage checkingProductsNotFound() {
        waitUntilPageLoader();
        String actual = getText(notFoundSearch);
        String expected = "Ничего не нашлось по запросу";
        Assert.assertTrue(actual.contains(expected), "Wrong massage");
        return this;
    }

    public SearchPage checkingPhotoProductsFound() {
        waitForElementToBeVisible(photoSearchResult);
        Assert.assertEquals(getText(photoSearchResult), "Поиск по фото", "Wrong massage");
        return this;
    }

    public SearchPage callPopupMessage() {
        waitForElementToBeVisible(popupMessage);
        Assert.assertEquals(getText(popupMessage), "Товар добавлен в корзину", "Wrong massage");
        return this;
    }

    public SearchPage addButtonProductToBasket(Integer count) {
        for (int i = 1; i <= count; i++) {
            waitUntilPageLoader();
            click(By.xpath("(//p/a[contains(@class, 'product-card__add-basket')])[" + i + "]"));
            waitUntilPageLoader();
            if (!elementDisplayed(popupListOfSizes)) {
                click(popupAddToBasket);
            }
        }
        Assert.assertEquals(count, new HeaderElement().counterCart(), "Wrong count");
        return this;
    }

    public SearchPage productFoundSuccessfully(String name) {
        waitUntilPageLoader();
        Assert.assertEquals(getText(findNameProduct), name, "Not found");
        checkingProductsFound();
        return this;
    }

}
