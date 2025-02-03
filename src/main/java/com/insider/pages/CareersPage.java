package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage{
    private By locationsBlock = By.id("career-our-location");
    private By teamsBlock = By.id("career-find-our-calling");
    private By lifeAtInsiderBlock = By.cssSelector("[data-id='a8e7b90']");//no specific id was found


    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCareersPageOpened() {
        return driver.getCurrentUrl().contains("careers") & driver.getCurrentUrl().equals("https://useinsider.com/careers/");
    }

    public boolean areBlocksVisible() {
        return isElementPresent(locationsBlock) &&
                isElementPresent(teamsBlock) &&
                isElementPresent(lifeAtInsiderBlock);
    }

}
