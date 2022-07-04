import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginWindow extends Util {


    public LoginWindow(WebDriver driver) {
        super(driver);
    }

    final static By loginTab = By.xpath("//*[@id=\"register\"]/*[@id=\"login-form-button\"]");
    final static By userNameInput = By.id("email");
    final static By passwordInput = By.id("password");
    final static By loginButton = By.xpath("//*[@id=\"login\"]/form/*/button[text()=\"Login\"]");

    final static By logoutButton = By.cssSelector("#logout-link a");

    public void login()  {
        WebElement tabButton = driver.findElement(loginTab);

        tabButton.click();
        driver.findElement(userNameInput).sendKeys("a");
        driver.findElement(passwordInput).sendKeys("b");
        driver.findElement(loginButton).click();
    }
    public String getLogoutText() {
        WebElement logout = driver.findElement(logoutButton);
        return logout.getText();

    }
    public void logout() {
        driver.findElement(logoutButton).click();

    }
}
