import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class BlogPage {
    WebDriver driver;

    public BlogPage(WebDriver driver) {
        this.driver = driver;
    }
    public final String url = "https://lennertamas.github.io/portio/blog/";
    //This By must be rethinked
    public final By nextButton = By.xpath("//*[@rel='next']");
    private final By blogEntriesVisible = By.className("blog-page__item-thumb");

    public void navigate() {
        driver.navigate().to(url);
    }

    public int numberOfEntries() {
        int num;

        List<WebElement> entries = driver.findElements(blogEntriesVisible);
        num = entries.size();

        return num;
    }

    public boolean isLastPage() {
        try {
            driver.findElement(nextButton).isDisplayed();
        }
        catch (Exception e) {
            return true;
        }
        return false;
     }
    public void clickNext() {
        if (!isLastPage()) {
            driver.findElement(nextButton).click();
        }
    }
}
