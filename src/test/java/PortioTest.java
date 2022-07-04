import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("1 Regisztráció")
    public void RegisterNewUser() {
        RegistrationWindow registration = new RegistrationWindow(driver);
        String actual = registration.RegisterUser();
        Assertions.assertEquals("User registered!",actual);

    }

    @Test
    @DisplayName("2 Bejelentkezés")
    public void loginTest() {
        Profile profile = new Profile(driver);
        RegistrationWindow registrationWindow = new RegistrationWindow(driver);
        LoginWindow loginWindow = new LoginWindow(driver);
        registrationWindow.RegisterUser();
        loginWindow.login();
        Assertions.assertEquals("Logout", loginWindow.getLogoutText());
    }
    @Test
    @DisplayName("3 Adatkezelési nyilatkozat elfogadása")
    public void AcceptTerms() {
        Util acceptBox = new Util(driver);
        acceptBox.acceptTermsAnd();
        Assertions.assertEquals("true", driver.manage().getCookieNamed("tandc").getValue());
    }
    @Test
    @DisplayName("4 Adatok listázása")
    public void listDataTest() {

    }
    @Test
    @DisplayName("5 Több oldalas lista bejárása")
    public void paginationTest() {
        BlogPage blogPage = new BlogPage(driver);
        blogPage.navigate();

        int actual = 0;

        while (true) {

            actual += blogPage.numberOfEntries();
            System.out.println(actual);

            if (blogPage.isLastPage()) {
                break;
            }
            blogPage.clickNext();
        }
        Assertions.assertEquals(9, actual);
    }

    @Test
    @DisplayName("6 Űj adat bevitel")
    public void inputDataTest() {
        Profile profile = new Profile(driver);
        RegistrationWindow registrationWindow = new RegistrationWindow(driver);
        LoginWindow loginWindow = new LoginWindow(driver);
        registrationWindow.RegisterUser();


        loginWindow.login();

        String actual = profile.setProfileName();

        Assertions.assertEquals("Profile Edited!",actual);

        Assertions.assertTrue(driver.manage().getCookieNamed("a").getValue().contains("Morcos Aranka"));


    }
    @Test
    @DisplayName("7 Ismételt és sorozatos adatbevitel - regisztráció file-ban található adatokkal")
    public void readFile() {
        RegistrationWindow multipleRegistration = new RegistrationWindow (driver);
        String actual = multipleRegistration.RegisterFromFile();
        System.out.println(actual);    }

    @Test
    @DisplayName("8 Meglévő adat módosítás")
    public void alterDataTest() {
        Profile profile = new Profile(driver);
        RegistrationWindow registrationWindow = new RegistrationWindow(driver);
        LoginWindow loginWindow = new LoginWindow(driver);
        registrationWindow.RegisterUser();

        loginWindow.login();


        String actual = profile.modifyProfile();


        Assertions.assertEquals("Profile Edited!",actual);

        String cookieData = driver.manage().getCookieNamed("a").getValue();

        Assertions.assertTrue(cookieData.contains("Aranyos Aranka"));
        Assertions.assertTrue(cookieData.contains("Kisangyalom"));
        Assertions.assertTrue(cookieData.contains("555-1111-01"));




    }

    @Test
    @DisplayName("9 Adatok törlése")
    public void deleteDataTest() {

    }

    @Test
    @DisplayName("10 Adatok lementése felületről")
    public void saveDataFromPageTest() throws IOException {
        BlogPage blogPage = new BlogPage(driver);
        blogPage.navigate();

        List<WebElement> blogEntries = blogPage.entries();

        FileWriter out = new FileWriter("savedtitles.txt");
        for (WebElement element: blogEntries) {
            out.write(element.findElement(By.cssSelector("h5")).getText() + "\n");
        }
        out.flush();
        out.close();




    }

    @Test
    @DisplayName("11 Kijelentkezés")
    public void logoutTest() throws InterruptedException {
        RegistrationWindow registrationWindow = new RegistrationWindow(driver);
        LoginWindow loginWindow = new LoginWindow(driver);
        registrationWindow.RegisterUser();
        loginWindow.login();
        Thread.sleep(1000);
        loginWindow.logout();
        Assertions.assertThrows(Exception.class, () -> System.out.println(loginWindow.getLogoutText()));

    }

    @AfterEach
    public void closeChrome() {

        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
