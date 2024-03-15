package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import propertyUtils.PropertyReader;

import java.util.HashMap;

import static java.io.File.separator;

public class DriverCreation {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static void creationDriver(DriverTypes types) {
        if (webDriver.get() == null) {
            switch (types) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    String property = PropertyReader.getProperties().getProperty("browser.option");
                    if (property != null && !property.isEmpty()) {
                        chromeOptions.addArguments(property.split(";"));
                        chromeOptions.setExperimentalOption("prefs", new HashMap<>() {{
                            put("profile.default_content_setting.popups", 0);
                            put("download.default_directory", System.getProperty("user.dir") + separator + "target");
                        }});
                    } else {
                        chromeOptions.addArguments("start-maximized");
                    }
                    webDriver.set(new ChromeDriver(chromeOptions));
                    break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("start-maximized");
                    webDriver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("start-maximized");
                    webDriver.set(new EdgeDriver(edgeOptions));
                    break;
            }
        }
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}
