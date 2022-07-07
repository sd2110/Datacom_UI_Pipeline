package parallel;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.payeePage;
import pages.paymentsPage;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {
WebDriver driver;
    payeePage payeepage = new payeePage();
    paymentsPage paymentsPage = new paymentsPage();
    private double everydayAccountBalance_beforeTransfer;
    private double billsAccountBalance_beforeTransfer;

    @Given("User launches the demo application website {string} on {string}")
    public void userLaunchesTheDemoWebsite(String website, String browserName) throws MalformedURLException {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver", "src\\test\\resources\\drivers\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        else {
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.get(website);
        driver.manage().window().maximize();
        String title = driver.getTitle();
        System.out.println("The title of the page is : " + title);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("js-overview")));
    }

    @And("User clicks on Menu")
    public void userClickOnMenu() {
        driver.findElement(By.xpath(payeepage.getMenuButton_xpath())).click();
    }

    @When("User clicks on Payees option")
    public void userClicksOnPayeesOption() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath(payeepage.getPayeeButton_xpath())).click();
    }

    @Then("Payees page is loaded successfully")
    public void payeesPageIsLoadedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(payeepage.getSearchPayee_css())));
        if (driver.findElement(By.cssSelector(payeepage.getPayeeHeader_css())).isDisplayed() &&
                driver.findElement(By.className(payeepage.getAddButtonMain_class())).isDisplayed() &&
                driver.findElement(By.cssSelector(payeepage.getSearchPayee_css())).isDisplayed() &&
                driver.findElement(By.className(payeepage.getNameColumn_class())).isDisplayed() &&
                driver.findElement(By.className(payeepage.getLastPaidColumn_class())).isDisplayed()) {
            System.out.println("Payee page is displayed successfully");
        } else
            Assert.fail("Payee page could not be loaded successfully");
    }

    @And("Navigates to the payee page")
    public void navigatesToThePayeePage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(payeepage.getMenuButton_xpath())).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(payeepage.getPayeeButton_xpath())).click();
    }

    @When("User clicks on the add button")
    public void userClicksOnTheAddButton() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.className(payeepage.getAddButtonMain_class())).click();
    }

    @And("Enters the payee details with name {string} and account number {string}{string}{string}{string}")
    public void entersThePayeeDetailsWithNameAndAccountNumber(String payeeName, String bank, String branch, String account, String suffix) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id(payeepage.getPayeeName_id())).sendKeys(payeeName);
        driver.findElement(By.id(payeepage.getPayeeNameHover_id())).click();
        driver.findElement(By.id(payeepage.getPayeeBankNumber_id())).sendKeys(bank);
        driver.findElement(By.id(payeepage.getPayeeBranchNumber_id())).sendKeys(branch);
        driver.findElement(By.id(payeepage.getPayeeAccountNumber_id())).sendKeys(account);
        driver.findElement(By.id(payeepage.getPayeeSuffixNumber_id())).sendKeys(suffix);
    }

    @And("Clicks on the add button")
    public void clicksOnTheAddButton() {
        driver.findElement(By.className(payeepage.getPayeeAddButton_class())).click();
    }

    @Then("Payee added message {string} gets displayed")
    public void payeeAddedMessageGetsDisplayed(String successMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(payeepage.getAddPayeeSuccessMsg_class())));
        String addPayeeSuccessMessage = driver.findElement(By.className(payeepage.getAddPayeeSuccessMsg_class())).getText();
        Assert.assertEquals(successMessage, addPayeeSuccessMessage);
    }

    @And("Payee with {string} gets added in the list of payees")
    public void payeeGetsAddedInTheListOfPayees(String payeeName) {
        List<WebElement> addedPayeeName = driver.findElements(By.className(payeepage.getAddedPayeeName_class()));
        try {
            for (WebElement webElement : addedPayeeName) {
                String addedPayeeName2 = webElement.getText();
                if (addedPayeeName2.equalsIgnoreCase(payeeName)) {
                    System.out.println("Payee was added successfully");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Payee could not be added successfully");
        }
    }

    @Then("Validation error gets displayed for the mandatory payee name field")
    public void validationErrorGetsDisplayedForTheMandatoryPayeeNameField() {
        if (driver.findElement(By.className(payeepage.getValidationError_class())).isDisplayed())
            System.out.println("Validation error for the mandatory field was successfully displayed");
        else
            Assert.fail("Validation error was not displayed");
    }

    @Then("List of added payee names are sorted in the ascending order")
    public void listOfAddedPayeeNamesAreSortedInTheAscendingOrder() {
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> addedPayeeName = driver.findElements(By.className(payeepage.getAddedPayeeName_class()));
        for (WebElement we : addedPayeeName) {
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedListAscending = new ArrayList<>();
        for (String s : obtainedList) {
            sortedListAscending.add(s);
        }
        Collections.sort(sortedListAscending);
        Assert.assertTrue(sortedListAscending.equals(obtainedList));
    }

    @When("User clicks on the Name column")
    public void userClicksOnTheNameColumn() {
        driver.findElement(By.className(payeepage.getNameColumn_class())).click();
    }

    @Then("List of added payee names get sorted in the descending order")
    public void listOfAddedPayeeNamesGetSortedInTheDescendingOrder() {
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> addedPayeeName = driver.findElements(By.className(payeepage.getAddedPayeeName_class()));
        for (WebElement we : addedPayeeName) {
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedListDescending = new ArrayList<>();
        for (String s : obtainedList) {
            sortedListDescending.add(s);
        }
        Collections.sort(sortedListDescending);
        Collections.reverse(sortedListDescending);
        Assert.assertTrue(sortedListDescending.equals(obtainedList));
    }

    @When("Navigates to the payments page")
    public void navigatestopaymentspage() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,60);
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
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(paymentsPage.getToAccount_css())).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("Bills");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[data-monitoring-label='Transfer Form Account Card']")).click();
    }

    @And("Transfer amount {string}")
    public void transferAmount(String amount) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.id(paymentsPage.getAmountTextBox_id())).sendKeys(amount);
    }

    @And("Clicks on the Pay or Transfer button")
    public void clicksOnThePayOrTransferButton() throws InterruptedException {
        Thread.sleep(1000);
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

    //Closing the browser
    @After
    public void quitDriver() {
        driver.quit();
    }
}

