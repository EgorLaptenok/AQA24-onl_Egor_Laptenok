package pageObject.wildberries.elements;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObject.BasePage;

public class MessageElement extends BasePage {
    private final By buttonSupportChat = By.xpath("//*[contains(@aria-label,'Онлайн')]");
    private final By helperMessage = By.xpath("//*[@class='message__text']");
    private final By buttonCloseChat = By.xpath("//*[@class='chat__btn-close']");

    public MessageElement clickSupportChat() {
        click(buttonSupportChat);
        return this;
    }

    public MessageElement appearedHelperMessage() {
        waitForElementToBeVisible(helperMessage);
        Assert.assertTrue(getText(helperMessage).contains("виртуальный помощник службы поддержки"), "Wrong massage");
        return this;
    }

    public MessageElement clickCloseChat() {
        click(buttonCloseChat);
        return this;
    }
}
