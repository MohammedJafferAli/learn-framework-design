package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver)
    {
        super(driver); //Want to pass current driver received from test to parent AbstractComponents
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*PageFactory used here to neatly align the web elements
    Element gets the driver using PageFactory initElements #15
    */
    @FindBy(css =".mb-3")
    List<WebElement> listOfProducts;

    //By locator concept for wait class
    By productsBy = By.cssSelector(".mb-3");

    //Action Methods
    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return listOfProducts;
    }


}
