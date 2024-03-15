package pageObject.wildberries.elements;

import org.openqa.selenium.By;
import pageObject.baseObject.BasePage;

public class HeaderElement extends BasePage {
    private final By searchInput = By.xpath("//input[@id='searchInput']");
    private final By buttonHomePage = By.xpath("//img[contains(@src,'wbbasket.ru') and @alt='Wildberries']");
    private final By buttonUploadFile = By.xpath("//label[contains(@class,'search-catalog__btn')]//*");
    private final By buttonForEnter = By.xpath("//a[contains(@class,'j-main-login')]");
    private final By buttonBasket = By.xpath("//a[contains(@class,'j-wba-header-item') and @href='/lk/basket']");
    private final By itemCounterCart = By.xpath("//*//span[@class='navbar-pc__notify']");

    public HeaderElement enteringMinCharValue() {
        sendKeys(this.searchInput, createMinAndMaxCharacterString(1));
        return this;
    }

    public HeaderElement enteringMaxCharValue() {
        sendKeys(this.searchInput, createMinAndMaxCharacterString(300));
        return this;
    }

    public HeaderElement enterValue(String value) {
        waitUntilPageLoader();
        sendKeys(this.searchInput, value);
        return this;
    }

    public String createMinAndMaxCharacterString(int minAndMaxLength) {
        return "a".repeat(Math.max(0, minAndMaxLength));
    }

    public HeaderElement returnHomePage() {
        click(buttonHomePage);
        return this;
    }

    public HeaderElement clickButtonBasket() {
        click(buttonBasket);
        return this;
    }

    public HeaderElement uploadFileToSearch(String file) {
        waitUntilPageLoader();
        uploadFile(buttonUploadFile, FILE_PATH.concat(file));
        return this;
    }

    public HeaderElement uploadVideoToSearch(String file) {
        waitUntilPageLoader();
        uploadFile(buttonUploadFile, FILE_PATH.concat(file));
        failed();
        return this;
    }

    public HeaderElement clickForEnter() {
        click(buttonForEnter);
        return this;
    }

    public Integer counterCart() {
        waitUntilPageLoader();
        return Integer.parseInt(getText(itemCounterCart));
    }
}
