package pageObject.wildberries;

import org.openqa.selenium.By;
import org.testng.Assert;

public class AddressPage extends BasketPage {
    private final By searchField = By.xpath("//input[contains(@class,'input__input') and @autocomplete='off']");
    private final By collectionsAddress = By.xpath("(//div[@class='address-item j-poo-option'])[1]");
    private final By errorMessage = By.xpath("//ymaps[contains(@class,'serp__error-msg')]");
    private final By buttonSelect = By.xpath("//button[contains(@class,'self__btn btn-main')]");
    private final By infoAddress = By.xpath("//span[contains(@class,'self__name-text')]");

    public AddressPage sendKeysSearch(String address) {
        sendKeys(searchField, address);
        return this;
    }

    public AddressPage clickAddress() {
        waitUntilPageLoader();
        click(collectionsAddress);
        return this;
    }

    public AddressPage clickButtonSelect() {
        waitUntilPageLoader();
        click(buttonSelect);
        return this;
    }

    public AddressPage errorWrongAddress() {
        waitUntilPageLoader();
        Assert.assertEquals(getErrorMessage(), "Ничего не нашлось", "Wrong massage");
        return this;
    }

    public String getInfoAddress() {
        return getText(infoAddress);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

}
