package com.banking.hooks;

import com.banking.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ApplicationHooks {

    @Before(order = 0)
    public void setUpBrowser() {
        DriverManager.initDriver();
    }

    @After(order = 1)
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed_Screenshot_" + scenario.getName());
        }
    }

    @After(order = 0)
    public void tearDownBrowser() {
        DriverManager.quitDriver();
    }
}

