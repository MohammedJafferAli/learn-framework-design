package standalone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static String productName = "ZARA COAT 3";

    //MohammedJaffer1@practise.com Password.1001
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        //Pre-condition - Already registered to this website
        driver.findElement(By.id("userEmail")).sendKeys("MohammedJaffer1@practise.com");
        driver.findElement(By.id("userPassword")).sendKeys("Password.1001");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        //Get the list of products
        List<WebElement> listOfProducts = driver.findElements(By.cssSelector(".mb-3"));
        //Get the product name then click on add to cart
        WebElement selectedProduct = listOfProducts.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click(); // Add the product to the cart

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // Select Cart from global header

        //Verify added product available in MyCart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        //Checkout from Mycart page
        driver.findElement(By.cssSelector(".totalRow button")).click();

        //Payment page
        WebElement country  = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));

        Actions customAction = new Actions(driver);
        customAction.sendKeys(country,"india").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); //Wait for the list to appear
        //Iterate through the list, find the country & select
        List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-item"));
        for(WebElement e : countryList)
        {
           if (e.getText().equalsIgnoreCase("India"))
           {
               e.click();
           }
        }
        //Place order
        driver.findElement(By.cssSelector(".action__submit")).click();

        // Thank you page
        String confirmMessage = driver.findElement(By.tagName("h1")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        Thread.sleep(500);
        driver.quit();

    }
}
