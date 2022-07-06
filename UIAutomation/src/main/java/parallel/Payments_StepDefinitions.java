package parallel;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.payeePage;
import pages.paymentsPage;

import static parallel.Payee_StepDefinitions.driver;

public class Payments_StepDefinitions {
    paymentsPage paymentsPage = new paymentsPage();
    payeePage payeepage = new payeePage();
    private double everydayAccountBalance_beforeTransfer;
    private double billsAccountBalance_beforeTransfer;



    @When("Navigates to the payments page")
    public void navigatestopaymentspage() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(paymentsPage.getEverydayAccountBalance_xpath())));
        everydayAccountBalance_beforeTransfer = Double.parseDouble(driver.findElement(By.xpath(paymentsPage.getEverydayAccountBalance_xpath())).getText().replaceAll(",",""));
        billsAccountBalance_beforeTransfer = Double.parseDouble(driver.findElement(By.xpath(paymentsPage.getBillsAccountBalance_xpath())).getText().replaceAll(",",""));
        driver.findElement(By.xpath(payeepage.getMenuButton_xpath())).click();
        Thread.sleep(1000);
        driver.findElement(By.className(paymentsPage.getPayOrTransferButton_class())).click();
        Thread.sleep(2000);
    }

    @And("User clicks on the from Everyday account to Bills account")
    public void userClicksOnTheFromEverydayAccountToBillsAccount() throws InterruptedException {
        driver.findElement(By.cssSelector(paymentsPage.getFromAccount_css())).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("Everyday");
        Thread.sleep(1000);
        driver.findElement(By.className("name-1-1-65")).click();
        driver.findElement(By.cssSelector(paymentsPage.getToAccount_css())).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("Bills");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[data-monitoring-label='Transfer Form Account Card']")).click();
    }

    @And("Transfer amount {string}")
    public void transferAmount(String amount) {
        driver.findElement(By.id(paymentsPage.getAmountTextBox_id())).sendKeys(amount);
    }

    @And("Clicks on the Pay or Transfer button")
    public void clicksOnThePayOrTransferButton() {
        driver.findElement(By.cssSelector(paymentsPage.getTransferButton_css())).click();
    }

    @Then("Payment is successful")
    public void paymentIsSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(paymentsPage.getPaymentSuccessfulMessage_class())));
        System.out.println("Payment was done successfully");
    }

    @And("Everyday account balance reduces by {string}")
    public void everydayAccountBalanceReducesBy(String amount) {
        double everydayAccountBalance_afterTransfer = Double.parseDouble(driver.findElement(By.xpath(paymentsPage.getEverydayAccountBalance_xpath())).getText().replaceAll(",",""));
        Assert.assertTrue("Everyday account balance was reduced successfully",everydayAccountBalance_afterTransfer==everydayAccountBalance_beforeTransfer-Double.parseDouble(amount));
    }

    @And("Bill account balance increases by {string}")
    public void billAccountBalanceIncreasesBy(String amount) {
        double billsAccountBalance_afterTransfer = Double.parseDouble(driver.findElement(By.xpath(paymentsPage.getBillsAccountBalance_xpath())).getText().replaceAll(",",""));
        Assert.assertTrue("Bills account balance was increased successfully", billsAccountBalance_afterTransfer==billsAccountBalance_beforeTransfer+Double.parseDouble(amount));

    }
}
