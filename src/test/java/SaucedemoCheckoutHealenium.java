import com.epam.healenium.SelfHealingDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SaucedemoCheckoutHealenium {
    @Test
    public void test() {
        // Setup driver dan Self-Healing Wrapper
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        WebDriver delegate = new ChromeDriver(options);
        WebDriver driver = SelfHealingDriver.create(delegate);

        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        // JavaScript injection: ubah ID checkout
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('checkout').setAttribute('id', 'checkout-new');");

        // Tanpa auto-healing, ini akan gagal karena ID telah diubah
        driver.findElement(By.id("checkout")).click();
        System.out.println("Checkout berhasil meskipun ID diubah.");

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
