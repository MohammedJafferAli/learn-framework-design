package pageobjects;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver); //Want to pass current driver received from test to parent AbstractComponents
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*PageFactory used here to neatly align the web elements
    Element gets the driver using PageFactory initElements #15
    */
    @FindBy(css = ".mb-3")
    List<WebElement> products;


    //By locator concept for wait class
    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");
    By ppAddedToCart = By.cssSelector("div.toast-success");

    //Action Methods
    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement selectedProduct = getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText().equals(productName))
                .findFirst().orElse(null);
        return selectedProduct;
    }

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(addToCart).click();
        waitForElementToAppear(toastContainer);
        waitForElementToAppear(ppAddedToCart);
    }


}
