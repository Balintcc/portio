import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegistrationWindow extends Util {
    public RegistrationWindow(WebDriver driver) {
        super(driver);
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
    public String RegisterFromFile() {
        acceptTermsAnd();
        driver.findElement(registrationFormButton).click();
        try {
            File myObj = new File("registerUserData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] datas = data.split(";");
                driver.findElement(username).sendKeys(Keys.CONTROL + "a");
                driver.findElement(username).sendKeys(datas[0]);
                driver.findElement(password).sendKeys(Keys.CONTROL + "a");
                driver.findElement(password).sendKeys(datas[1].trim());
                driver.findElement(email).sendKeys(Keys.CONTROL + "a");
                driver.findElement(email).sendKeys(datas[2].trim());
                driver.findElement(description).sendKeys(Keys.CONTROL + "a");
                driver.findElement(description).sendKeys(datas[3].trim());
                driver.findElement(registrationSubmitButton).click();
            }
            myReader.close();
            return driver.findElement(userRegisteredAlert).getText();

        } catch (FileNotFoundException e) {
        }
        return ("An error occurred.");
    }
}
