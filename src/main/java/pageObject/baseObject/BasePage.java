package pageObject.baseObject;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

import static driver.DriverCreation.getDriver;
import static java.io.File.separator;

@Log4j
public abstract class BasePage {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected final String FILE_PATH = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "resources" + separator + "fileForTests" + separator;

    {
        driver = getDriver();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
    }

    protected void navigateTo(String url) {
        log.info("Navigate to :: " + url);
        driver.get(url);
        waitUntilPageLoader();
    }

    public void waitUntilPageLoader() {
        waitUntil(1);
        String preloader = "//*[@class='general-preloader']";
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath(preloader))));
    }

    protected void waitUntil(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void click(String xpath) {
        click(By.xpath(xpath));
    }

    protected void click(By by) {
        log.info("Click on element :: " + by);
        waitUntilElementToBeClickable(by);
        click(driver.findElement(by));
    }

    protected void click(WebElement element) {
        log.info("Click to :: " + element);
        waitUntilElementToBeClickable(element);
        element.click();
    }

    protected void moveToClick(By by) {
        moveToClick(driver.findElement(by));
    }

    protected void moveToClick(WebElement element) {
        log.info("Move to click :: " + element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    protected void sendKeys(By by, CharSequence... charSequences) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sendKeys(driver.findElement(by), charSequences);
    }

    protected void sendKeys(WebElement element, CharSequence... charSequences) {
        log.info("Enter in element :: " + element + ", next value :: " + Arrays.toString(charSequences));
        element.click();
        element.clear();
        element.sendKeys(charSequences);
        element.sendKeys(Keys.ENTER);
    }

    protected String getText(By by) {
        WebElement element = driver.findElement(by);
        String text = element.getText();
        log.info("Get text :: " + by + " next value :: " + text);
        return text;
    }

    protected String getAttribute(By by) {
        WebElement element = driver.findElement(by);
        String text = element.getAttribute("value");
        log.info("Get text :: " + by + " next value :: " + text);
        return text;
    }

    protected void uploadFile(By by, String filePatch) {
        log.info("Upload file :: " + filePatch);
        WebElement fileInput = driver.findElement(by);
        fileInput.sendKeys(filePatch);
    }

    protected void waitForElementToBeVisible(By by) {
        log.info("Wait to element :: " + by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected boolean elementDisplayed(By by) {
        log.info("Element is displayed :: " + by);
        boolean isEmpty = !driver.findElements(by).isEmpty();
        return !isEmpty;
    }

    protected void waitUntilElementToBeClickable(String xpath) {
        waitUntilElementToBeClickable(By.xpath(xpath));
    }

    protected void waitUntilElementToBeClickable(By by) {
        log.info("Wait until element to be clickable  :: " + by);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitUntilElementToBeClickable(WebElement element) {
        log.info("Wait until element to be clickable  :: " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    protected void waitUntilElementToBeNotClickable(By by) {
        waitUntilElementToBeNotClickable(driver.findElement(by));
    }

    protected void waitUntilElementToBeNotClickable(WebElement element) {
        log.info("Wait until element not to be clickable  :: " + element);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
    }

    protected void specialLogging(By by, String... text) {
        log.info("This element :: " + by + Arrays.toString(text));
    }

    protected void failed() {
        try {
            waitUntilPageLoader();
        } catch (TimeoutException e) {
            log.info("Error: The element did not become visible within the specified time");
        }
    }
}
