import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BlogPage {
    WebDriver driver;

    public BlogPage(WebDriver driver) {
        this.driver = driver;
    }
    public final String url = "https://lennertamas.github.io/portio/blog/";
    //This By must be rethinked
    public final By nextButton = By.xpath("//*[@id=\"content\"]/section/div/div/nav/ul/li[3]/a");
    private final By blogEntriesVisible = By.className("blog-page__item-thumb");

    public void navigate() {
        driver.navigate().to(url);
    }

    public int numberOfEntries() {
        int num = 0;

        List<WebElement> entries = driver.findElements(blogEntriesVisible);
        num = entries.size();

        return num;
    }

    //check if rel="next" is visible
    public boolean isLastPage(){
        String attr = driver.findElement(nextButton).getAttribute("style");
        return attr.equals("display: none;");
    }
    public void clickNext() {
        if (!isLastPage()) {
            driver.findElement(nextButton).click();
        }
    }
}
