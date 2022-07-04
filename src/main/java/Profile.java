import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Profile extends Util{
    WebDriver driver;
    public Profile(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    final static  By profileButton = By.id("profile-btn");
    final static By saveProfileButton = By.xpath("//*[@id=\"edit-profile\"]/form/*/button[text()=\"Save Profile\"]");
    final static By nameInput = By.id("name");
    final static By bioInput = By.id("bio");
    final static By phoneNumberInput = By.id("phone-number");
    final static By editAlert = By.id("edit-alert");

    public String modifyProfile(){
        driver.findElement(profileButton).click();
        driver.findElement(nameInput).sendKeys("Aranyos Aranka");
        driver.findElement(bioInput).sendKeys("Kisangyalom");
        driver.findElement(phoneNumberInput).sendKeys("555-1111-01");
        driver.findElement(saveProfileButton).click();
        return driver.findElement(editAlert).getText();

    }

    public String setProfileName(){
        driver.findElement(profileButton).click();
        driver.findElement(nameInput).sendKeys("Morcos Aranka");
        driver.findElement(saveProfileButton).click();
        return driver.findElement(editAlert).getText();

    }
}
