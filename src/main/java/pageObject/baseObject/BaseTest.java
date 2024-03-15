package pageObject.baseObject;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import propertyUtils.InvokedListener;
import testngUtils.Listener;

import static driver.DriverCreation.*;
import static driver.DriverTypes.CHROME;
import static driver.DriverTypes.valueOf;
import static propertyUtils.PropertyReader.getProperties;

@Listeners({Listener.class, InvokedListener.class})
public abstract class BaseTest {
    @BeforeTest
    public void setUp() {
        creationDriver(System.getProperties().containsKey("config")
                ? valueOf(getProperties().getProperty("browser").toUpperCase())
                : CHROME
        );
    }

    @AfterTest
    public void tearDown() {
        quitDriver();
    }
}
