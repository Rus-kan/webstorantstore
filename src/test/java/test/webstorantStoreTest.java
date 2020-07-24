package test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.SearchPage;
import utilities.driver;


public class webstorantStoreTest {
    public String webAppURL="https://www.webstaurantstore.com/";
    public String searchItem="stainless work table";
    public String inTitle="Driver";
    LoginPage lp=new LoginPage();
    SearchPage sp=new SearchPage();
    CartPage cart=new CartPage();
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest extentTest;

    @BeforeSuite
    public void setUpReport(){
        htmlReporter=new ExtentHtmlReporter("target/reports/extent.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Test report");
        htmlReporter.config().setReportName("Test results");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent=new ExtentReports();
        extent.setSystemInfo("Webapp", "Webstorantstore.com");
        extent.setSystemInfo("Browser", "Chrome");
        extent.attachReporter(htmlReporter);
    }
    @BeforeTest
    public void goToPage(){
        driver.getDriver().get(webAppURL);
        lp.searchEquipment(searchItem);
    }

    @Test
    public void searchItem(){
        extentTest=extent.createTest("Verifying 'Table' in title of search results");
        boolean actual=sp.checkTitlesOnPage(inTitle);
        Assert.assertEquals(actual, true,"Not all items has 'Table' in title!");
        extentTest.getStatus();


    }

    @Test
    public void addItemToCart() throws InterruptedException{
        extentTest=extent.createTest("Verifying Item in cart");
        WebDriverWait wait= new WebDriverWait(driver.getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(sp.itemDescription));
        String expectedItemName=sp.lastItemDescription();
        sp.lastItemClick();
        wait.until(ExpectedConditions.elementToBeClickable(sp.popup));
        sp.popup.click();
        wait.until(ExpectedConditions.elementToBeClickable(sp.cart));
        sp.cart.click();
        String actualItemName=cart.inCartItemDescription();
        Assert.assertEquals(actualItemName,expectedItemName,"Wrong Item or No Item in the cart");
        extentTest.getStatus();
        cart.emptyCart();

    }


    @AfterTest
    public void tearDown(){
        driver.quitDriver();
        extent.flush();
    }







}
