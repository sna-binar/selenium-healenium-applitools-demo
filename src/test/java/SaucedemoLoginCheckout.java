import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SaucedemoLoginCheckout {
    @Test
    public void test() {
        //WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        // Checkout
        driver.findElement(By.id("checkout")).click();

        // Input checkout info
        driver.findElement(By.id("first-name")).sendKeys("QA");
        driver.findElement(By.id("last-name")).sendKeys("Engineer");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();
        String thankYouText = driver.findElement(By.cssSelector("h2.complete-header")).getText();
        Assert.assertEquals("Thank you for your order!", thankYouText);
        driver.findElement(By.id("back-to-products")).isDisplayed();

        driver.quit();
    }
}

