package tests;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class LoginErrorValidation extends BaseTest {
    public static String productName = "ZARA COAT 3";
@Test
    public void loginErrorValidation() throws IOException {

        loginPage.loginToApplication("MohammedJaffer2@practise.com", "Password.101");
        Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");

    }

    @Test
    public void productMisMatchValidation()
    {
        ProductCatalogue productCatalogue = loginPage.loginToApplication("MohammedJaffer1@practise.com", "Password.1001");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyCartProducts("ZARA COAT 33");
        Assert.assertTrue(match);
    }
}
