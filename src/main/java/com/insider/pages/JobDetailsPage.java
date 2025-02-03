package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobDetailsPage extends BasePage {
    private By jobTitle = By.cssSelector(".posting-headline h2");
    private By jobLocation =By.cssSelector(".posting-categories .location");
    private By jobDepartment =By.cssSelector(".posting-categories .department");
    public JobDetailsPage(WebDriver driver) {
        super(driver);
    }


    public boolean isJobDetailsPageLever() {
        return driver.getCurrentUrl().contains("lever.co");
    }

    public String getJobTitle(){
        return getText(jobTitle);
    }
    public String getJobLocation(){
        return getText(jobLocation);
    }
    public String getjobDepartment(){
        return getText(jobDepartment);
    }
}
