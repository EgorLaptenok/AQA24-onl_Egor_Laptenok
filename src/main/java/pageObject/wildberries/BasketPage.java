package pageObject.wildberries;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObject.baseObject.BasePage;
import pageObject.wildberries.elements.HeaderElement;

import java.util.Arrays;

public class BasketPage extends BasePage {
    private final By listProduct = By.xpath("//div[@class='list-item__wrap']");
    private final By inputCount = By.xpath("//input[contains(@class,'count__numeric')]");
    private final By buttonClearBasket = By.xpath("//button[contains(@class,'j-basket-item-del')]");
    private final By massageEmptyBasket = By.xpath("//h1[contains(@class,'basket-empty__title')]");
    private final By buttonValuePlus = By.xpath("//*[contains(@class,'count__plus')]");
    private final By buttonValueMinus = By.xpath("//*[contains(@class,'count__minus')]");
    private final By priceWithOutWallet = By.xpath("//*[contains(@class,'price-new wallet')]");
    private final By selectDeliveryAddress = By.xpath("//*//span[@class='b-link']");
    private final By infoAddress = By.xpath("//span[contains(@class,'address-item__name')]");
    private final By editAddress = By.xpath("//*[contains(@class,'basket-order__edit')]");
    private final By closeEdit = By.xpath("//a[contains(@class,'popup__close')]");
    private final By newAddress = By.xpath("//*[contains(@class,'list-address__btn--link')]");

    public BasketPage listProductVisibility() {
        waitForElementToBeVisible(listProduct);
        elementDisplayed(listProduct);
        return this;
    }

    public BasketPage clickDeliveryAddress() {
        click(selectDeliveryAddress);
        return this;
    }

    public BasketPage clickClearBasket() {
        while (!elementDisplayed(buttonClearBasket)) {
            moveToClick(buttonClearBasket);
        }
        return this;
    }

    public BasketPage emptyBasket() {
        Assert.assertEquals(getText(massageEmptyBasket), "В корзине пока пусто", "Basket is not empty");
        return this;
    }

    public BasketPage clickValuePlus() {
        waitUntilPageLoader();
        int count = Integer.parseInt(getAttribute(inputCount));
        int price = priceExtraction();
        int countClick = new HeaderElement().counterCart();
        click(buttonValuePlus);
        Assert.assertTrue(Integer.parseInt(getAttribute(inputCount)) > count, "Wrong value");
        Assert.assertTrue(price < priceExtraction(), "Wrong price");
        Assert.assertEquals(new HeaderElement().counterCart(), countClick + 1, "Wrong count");
        return this;
    }

    public BasketPage clickValueMinus() {
        waitUntilPageLoader();
        int count = Integer.parseInt(getAttribute(inputCount));
        int price = priceExtraction();
        if (Integer.parseInt(getAttribute(inputCount)) > 1) {
            int countClick = new HeaderElement().counterCart();
            click(buttonValueMinus);
            Assert.assertTrue(Integer.parseInt(getAttribute(inputCount)) < count, "Wrong value");
            Assert.assertTrue(price > priceExtraction(), "Wrong price");
            Assert.assertEquals(new HeaderElement().counterCart(), countClick - 1, "Wrong count");
        } else {
            specialLogging(inputCount, "the value is less than acceptable and cannot be reduced");
        }
        return this;
    }

    public BasketPage enterValue(Integer value) {
        waitUntilPageLoader();
        int price = priceExtraction();
        waitUntilPageLoader();
        sendKeys(inputCount, String.valueOf(value));
        waitUntilPageLoader();
        Assert.assertEquals(Integer.parseInt(getAttribute(inputCount)), value, "Wrong value");
        Assert.assertEquals(priceExtraction(), price * value, "Wrong price");
        return this;
    }

    public Integer priceExtraction() {
        return Integer.parseInt(getText(priceWithOutWallet)
                .replace("\u00a0", "")
                .replace(" ", "")
                .replace("₽", "")
                .trim());
    }

    public String[] productText() {
        int count = 1;
        int count2 = 0;
        String[] arrText = new String[new HeaderElement().counterCart()];
        while (count <= new HeaderElement().counterCart()) {
            waitUntilPageLoader();
            arrText[count2] = getText(By.xpath("(//*//span[@class='good-info__good-name'])[" + count + "]"));
            count++;
            count2++;
        }
        return arrText;
    }

    public BasketPage compareProductWithCart(String search) {
        boolean containsWorld = Arrays.stream(productText()).anyMatch(product -> product.toLowerCase().contains(search.toLowerCase()));
        Assert.assertTrue(containsWorld, "Wrong product");
        return this;
    }

    public BasketPage eqAddress(String address) {
        click(editAddress);
        waitUntilPageLoader();
        Assert.assertEquals(getText(infoAddress), address, "Wrong address");
        click(closeEdit);
        return this;
    }

    public BasketPage newAddAddress() {
        click(editAddress);
        waitUntilPageLoader();
        click(newAddress);
        return this;
    }
}
