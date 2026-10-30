package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By transferFundsLink = By.linkText("Transfer Funds");
    private final By amountInput = By.id("amount");
    private final By fromAccountDropdown = By.id("fromAccountId");
    private final By toAccountDropdown = By.id("toAccountId");
    private final By transferSubmitButton = By.cssSelector("input[value='Transfer']");
    private final By confirmationHeader = By.cssSelector("#showResult .title");
    private final By confirmationBody = By.xpath("//*[@id='showResult']/p[1]");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openTransferFunds() {
        wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink)).click();
    }

    public void transfer(String amount, String fromAccount, String toAccount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput)).sendKeys(amount.replace("$", ""));
        
        Select fromSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountDropdown)));
        if (!fromSelect.getOptions().isEmpty()) {
            fromSelect.selectByIndex(0);
        }

        Select toSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(toAccountDropdown)));
        if (!toSelect.getOptions().isEmpty()) {
            toSelect.selectByIndex(0);
        }

        driver.findElement(transferSubmitButton).click();
    }

    public String getConfirmationHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationHeader)).getText();
    }

    public String getConfirmationBody() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationBody)).getText();
    }
}

