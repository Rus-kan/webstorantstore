package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.driver;


public class LoginPage {
    public LoginPage(){PageFactory.initElements(driver.getDriver(), this);}

    @FindBy(id = "searchval")
    public WebElement searchInput;
    @FindBy(xpath = "//button[@value='Search']")
    public WebElement searchButton;
    public void searchEquipment(String string){
        searchInput.sendKeys(string);
        searchButton.click();
    }

}
