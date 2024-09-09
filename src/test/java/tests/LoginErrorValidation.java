package tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class LoginErrorValidation extends BaseTest {

@Test
    public void submitOrder() throws IOException {

        loginPage.loginToApplication("MohammedJaffer2@practise.com", "Password.101");
        Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");

    }
}
