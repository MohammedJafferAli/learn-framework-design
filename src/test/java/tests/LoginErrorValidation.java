package tests;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckOutPage;
import pageobjects.ConfirmationPage;
import pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class LoginErrorValidation extends BaseTest {

@Test
    public void submitOrder() throws IOException {

        loginPage.loginToApplication("MohammedJaffer2@practise.com", "Password.101");
        Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");

    }
}
