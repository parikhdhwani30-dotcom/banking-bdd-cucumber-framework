package com.banking.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.banking.stepdefinitions", "com.banking.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        tags = "@smoke or @regression",
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Overriding scenarios() with parallel = true enables parallel execution
     * of individual BDD Scenarios across multiple threads.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

