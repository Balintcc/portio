import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationWindow {
    WebDriver driver;
    public RegistrationWindow(WebDriver driver) {
        this.driver = driver;
    }

    final static String url = "https://lennertamas.github.io/portio/index.html";
    final static By registrationFormButton = By.id("register-form-button");
    final static By registrationSubmitButton = By.id("register");

    public void navigate() {
        driver.navigate().to(url);
    }


}
