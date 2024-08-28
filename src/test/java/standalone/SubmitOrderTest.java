package standalone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pageobjects.*;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static String productName = "ZARA COAT 3";

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Pre-condition - Already registered to this website
        LoginPage loginPage = new LoginPage(driver);
        loginPage.launchApp();
        ProductCatalogue productCatalogue = loginPage.loginToApplication("MohammedJaffer1@practise.com", "Password.1001");

        //Get the list of products
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        // Select Cart from global header
        CartPage cartPage = productCatalogue.goToCartPage();

        //Verify added product available in MyCart
        Boolean match = cartPage.verifyCartProducts(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();//Checkout from Mycart page

        //Checkout/Payment page
        checkOutPage.selectCountry("India");
        //Place order
        ConfirmationPage confirmationPage = checkOutPage.placeOrder();

        // Thank you / Order confirmation page
        String confirmMessage = confirmationPage.getConfirmaitonMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        //Close the application
        confirmationPage.signOut();
        driver.quit();

    }
}
