import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class PortioTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void AcceptTerms() {
        Util acceptBox = new Util(driver);
        acceptBox.acceptTermsAnd();
    }

    @Test
    public void paginationTest() {
        BlogPage blogPage = new BlogPage(driver);
        blogPage.navigate();

        int actual = 0;

        while (true) {
            actual += blogPage.numberOfEntries();

            if (blogPage.isLastPage()) {
                break;
            }
            blogPage.clickNext();
        }
        Assertions.assertEquals(9, actual);

    }
}
