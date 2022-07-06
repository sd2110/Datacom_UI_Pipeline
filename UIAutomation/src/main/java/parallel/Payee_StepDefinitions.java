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
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.payeePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Payee_StepDefinitions {
    public static RemoteWebDriver driver;
    payeePage payeepage = new payeePage();

    @Given("User launches the demo application website {string} on {string}")
    public void userLaunchesTheDemoWebsite(String website, String browserName) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (browserName.equalsIgnoreCase("opera")){
            capabilities.setBrowserName(BrowserType.OPERA_BLINK);
            System.out.println("##Test execution started on "+browserName+"##");
        }
        else if(browserName.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName(BrowserType.FIREFOX);
            System.out.println("##Test execution started on "+browserName+"##");
        }
        else {
            capabilities.setBrowserName(BrowserType.CHROME);
            System.out.println("##Test execution started on "+browserName+"##");
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"),capabilities);
        driver.get(website);
        driver.manage().window().maximize();
        String title = driver.getTitle();
        System.out.println("The title of the page is : " + title);
        WebDriverWait wait = new WebDriverWait(driver, 20);
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
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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

    //Closing the browser
    @After
    public void quitDriver() {
        driver.quit();
    }
}

