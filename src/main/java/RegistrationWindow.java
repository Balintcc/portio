import org.example.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationWindow extends Util {
    WebDriver driver;
    public RegistrationWindow(WebDriver driver, WebDriver driver1) {
        super(driver);
        this.driver = driver1;
    }

    final static By registrationFormButton = By.id("register-form-button");
    final static By registrationSubmitButton = By.xpath("//*[@id=\"register\"]/form/div[6]/button");
    final static By userRegisteredAlert = By.id("register-alert");
    final static By username = By.id("register-username");
    final static By password = By.id("register-password");
    final static By email = By.id("register-email");
    final static By description = By.id("register-description");

    public String RegisterUser()  {

        acceptTermsAnd();
        driver.findElement(registrationFormButton).click();
        driver.findElement(username).sendKeys("a");
        driver.findElement(password).sendKeys("b");
        driver.findElement(email).sendKeys("c@d");
        driver.findElement(description).sendKeys("eee");
        driver.findElement(registrationSubmitButton).click();
        return driver.findElement(userRegisteredAlert).getText();

    }
}
