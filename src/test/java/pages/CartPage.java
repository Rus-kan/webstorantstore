package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.driver;

import java.util.Iterator;
import java.util.Set;

public class CartPage {
    public CartPage(){
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='details']/span/a")
    public WebElement itemInCart;
    @FindBy(xpath = "//div[@data-hypernova-key='EmptyCart']/a")
    public WebElement emptyCartButton;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement verifyCleanButton;
    public String inCartItemDescription() {
        return itemInCart.getAttribute("href");
    }

    public void emptyCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), 20);
        emptyCartButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert=driver.getDriver().switchTo().alert();
        alert.accept();
    }



}
