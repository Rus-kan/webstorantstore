package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.driver;
import java.util.List;

public class SearchPage {
    public SearchPage(){
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-testid='itemDescription']")
    public List<WebElement> itemDescription;
    @FindBy(xpath = "//input[@name='addToCartButton']")
    public List<WebElement> addToCardButton;
    @FindBy(id="cartItemCountSpan")
    public WebElement cart;
    @FindBy(xpath = "//div[@class='notification-fader open']")
    public WebElement popup;



    public void lastItemClick() {
        addToCardButton.get(addToCardButton.size() - 1).click();
    }

    public String lastItemDescription(){
        return itemDescription.get(itemDescription.size()-1).getAttribute("href");
    }

    public boolean checkTitlesOnPage(String string){
        boolean flag=true;
        for(WebElement item:itemDescription){
            flag=item.getText().contains(string);
            if(flag==false) break;
        }
        return flag;
    }

}
