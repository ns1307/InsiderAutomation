package com.insider.pages;

import com.insider.utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private By careersLink = By.xpath("//a[contains(text(),'Careers')]");
    private By companyMenu = By.xpath("//a[contains(text(),'Company')]");

    public HomePage(WebDriver driver) {
       super(driver);
    }

    public boolean isHomePageOpened() {
        return driver.getTitle().contains("Insider") & driver.getCurrentUrl().equals(Config.HOME_PAGE_URL);
    }

    public void navigateToCareers() {
        clickElement(companyMenu);
        clickElement(careersLink);
    }

}
