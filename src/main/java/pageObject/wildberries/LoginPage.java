package pageObject.wildberries;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObject.BasePage;

public class LoginPage extends BasePage {
    private final By inputPhone = By.xpath("//*//input[@class='input-item']");
    private final By buttonCode = By.xpath("//*//button[@id='requestCode']");
    private final By errorMassage = By.xpath("//*//span[contains(@class,'j-error-full-phone')]");
    private final By nextPage = By.xpath("//*//h2[contains(text(),'Введите')]");

    public LoginPage enterPhone(String value) {
        waitUntilPageLoader();
        sendKeys(inputPhone, value);
        return this;
    }

    public LoginPage clickButtonCode() {
        click(buttonCode);
        return this;
    }

    protected String getErrorMassage() {
        return getText(errorMassage);
    }

    public LoginPage correctErrorMessage() {
        Assert.assertEquals(getErrorMassage(), "Некорректный формат номера", "Wrong massage");
        return this;
    }

    public LoginPage successfullyTrimmedNumber() {
        waitUntilPageLoader();
        Assert.assertEquals(getText(nextPage), "Введите код с картинки", "Wrong action");
        return this;
    }
}
