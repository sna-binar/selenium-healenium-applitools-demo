import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class VisualTestSaucedemo {
    @Test
    public void test() {
        //WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

        Eyes eyes = new Eyes();
        eyes.setApiKey("jzKg2Ck81vWPcWzFwWO6jA097kZnj6n7AkQJgLXLVjM110");

        try {
            eyes.open(driver, "Saucedemo", "Cart Visual Test");
            driver.get("https://www.saucedemo.com");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            driver.findElement(By.className("shopping_cart_link")).click();

            // Ambil snapshot visual halaman keranjang
            eyes.check("Cart Page", Target.window());

            driver.quit();
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
        }
    }
}

