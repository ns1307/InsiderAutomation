package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public boolean isElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickElement(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void clickWithJS(WebElement button) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", button);
        button.click();
    }

    public void clickWithHover(WebElement button) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", button);
        sleep(2);
        //button = wait.until(ExpectedConditions.elementToBeClickable(button));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).pause(Duration.ofSeconds(2)).click().perform();
        sleep(2);

    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void sleep(int second) {
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickDeclineCookies(){
        clickElement(By.id("wt-cli-reject-btn"));
    }
}