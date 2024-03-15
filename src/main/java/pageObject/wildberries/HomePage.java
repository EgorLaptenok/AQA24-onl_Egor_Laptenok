package pageObject.wildberries;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObject.BasePage;
import pageObject.wildberries.elements.HeaderElement;

import static propertyUtils.PropertyReader.getProperties;

public class HomePage extends BasePage {
    private final By popupMessage = By.xpath("//div[contains(@class, 'action-notification')]");
    private final By popupListOfSizes = By.xpath("//div[contains(@class,'popup-list-of-sizes')]");
    private final String popupModal = "//*[contains(@class,'popup popup-list')]";
    private final String popupAddToBasket = popupModal.concat("//*[@class='sizes-list__item']");
    private final By product = By.xpath("//a[contains(@class,'product-card__link')]");

    public HomePage open(String url) {
        navigateTo(url);
        return this;
    }

    public HomePage open() {
        navigateTo(getProperties().getProperty("url"));
        return this;
    }

    public HomePage callPopupMessage() {
        waitForElementToBeVisible(popupMessage);
        Assert.assertEquals(getText(popupMessage), "Товар добавлен в корзину", "Wrong massage");
        return this;
    }

    public HomePage addButtonProductToBasket(Integer count, Integer beginWith) {
        int countClick = beginWith - 1;
        for (int i = beginWith, j = 1; j <= count; i++, j++) {
            waitUntilPageLoader();
            click(By.xpath("(//p/a[contains(@class, 'product-card__add-basket')])[" + i + "]"));
            countClick++;
            waitUntilPageLoader();
            if (!elementDisplayed(popupListOfSizes)) {
                click(popupAddToBasket);
            }
        }
        Assert.assertEquals(new HeaderElement().counterCart(), countClick, "Wrong count");
        return this;
    }

    public HomePage clickOnProduct(Integer posProduct) {
        click(By.xpath("(//a[contains(@class,'product-card__link')])[" + posProduct + "]"));
        return this;
    }
}
