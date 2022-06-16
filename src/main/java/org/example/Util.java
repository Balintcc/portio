package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Util {
        WebDriver driver;
        public Util(WebDriver driver) {
            this.driver = driver;
        }
        private final String url = "https://lennertamas.github.io/portio/index.html";
        private final By termsBox = By.xpath("//*[@id=\"overlay\"]/div/div[2]/div/div[1]");
        private final By acceptButton = By.xpath("//*[@id=\"terms-and-conditions-button\"]");
        private final By loginBox = By.xpath("//*[@id=\"login\"]");


    public void navigate() {
        driver.navigate().to(url);
    }
    public void clickButton() {
        driver.findElement(acceptButton).click();
    }

    }

