package com.banking.stepdefinitions;

import com.banking.driver.DriverManager;
import com.banking.pages.BankingLoginPage;
import com.banking.pages.TransferFundsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class BankingStepDefinitions {

    private final BankingLoginPage loginPage = new BankingLoginPage(DriverManager.getDriver());
    private final TransferFundsPage transferPage = new TransferFundsPage(DriverManager.getDriver());

    @Given("I navigate to the banking portal login page")
    public void iNavigateToTheBankingPortalLoginPage() {
        loginPage.navigateTo("https://parabank.parasoft.com/parabank/index.htm");
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be navigated to the {string} dashboard")
    public void iShouldBeNavigatedToTheDashboard(String expectedHeading) {
        Assert.assertTrue(loginPage.getPageHeading().contains(expectedHeading), 
                "Expected page heading to contain: " + expectedHeading);
    }

    @And("I should see customer welcome message {string}")
    public void iShouldSeeCustomerWelcomeMessage(String expectedWelcome) {
        Assert.assertTrue(loginPage.getWelcomeMessage().contains(expectedWelcome), 
                "Expected welcome message to contain: " + expectedWelcome);
    }

    @Then("I should see an authentication error message {string}")
    public void iShouldSeeAnAuthenticationErrorMessage(String expectedError) {
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Expected error message: " + expectedError);
    }

    @Given("I am authenticated as {string} with password {string}")
    public void iAmAuthenticatedAsWithPassword(String username, String password) {
        loginPage.navigateTo("https://parabank.parasoft.com/parabank/index.htm");
        loginPage.login(username, password);
    }

    @When("I navigate to the {string} service")
    public void iNavigateToTheService(String serviceName) {
        if (serviceName.equalsIgnoreCase("Transfer Funds")) {
            transferPage.openTransferFunds();
        }
    }

    @And("I transfer {string} from account {string} to account {string}")
    public void iTransferFromAccountToAccount(String amount, String fromAccount, String toAccount) {
        transferPage.transfer(amount, fromAccount, toAccount);
    }

    @Then("I should receive a transfer confirmation message {string}")
    public void iShouldReceiveATransferConfirmationMessage(String expectedHeader) {
        Assert.assertTrue(transferPage.getConfirmationHeader().contains(expectedHeader),
                "Expected confirmation header: " + expectedHeader);
    }

    @And("the transaction details should state {string}")
    public void theTransactionDetailsShouldState(String expectedDetails) {
        Assert.assertTrue(transferPage.getConfirmationBody().contains(expectedDetails),
                "Expected transaction body to contain: " + expectedDetails);
    }
}
