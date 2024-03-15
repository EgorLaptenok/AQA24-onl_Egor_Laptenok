package pageObject.wildberries;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObject.BasePage;
import pageObject.wildberries.elements.HeaderElement;

public class ProductPage extends BasePage {
    private final By productCode = By.xpath("//span[@id='productNmId']");
    private final By buttonAddToCart = By.xpath("(//div[@class='order'])[2]");
    private final By popupListOfSizes = By.xpath("//div[contains(@class,'popup-list-of-sizes')]");
    private final String popupModal = "//*[contains(@class,'popup popup-list')]";
    private final String popupAddToBasket = popupModal.concat("//*[@class='sizes-list__item']");

    public ProductPage foundCodeProduct(String code) {
        waitUntilPageLoader();
        Assert.assertEquals(getText(productCode), code, "Not found");
        return this;
    }

    public ProductPage clickButtonAdd() {
        waitUntilPageLoader();
        int countClick = new HeaderElement().counterCart();
        click(buttonAddToCart);
        waitUntilPageLoader();
        if (!elementDisplayed(popupListOfSizes)) {
            click(popupAddToBasket);
        }
        waitUntilPageLoader();
        Assert.assertEquals(new HeaderElement().counterCart(), countClick + 1, "Wrong count");
        return this;
    }
}
