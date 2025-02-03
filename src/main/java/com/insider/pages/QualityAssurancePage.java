package com.insider.pages;

import com.insider.utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QualityAssurancePage extends BasePage {

    private By seeAllQAJobsButton = By.xpath("//a[text()='See all QA jobs']");
    private By locationFilter = By.xpath("//span[@id='select2-filter-by-location-container']");
    private By departmentFilter = By.xpath("//span[@id='select2-filter-by-department-container']");
    private By jobList = By.cssSelector("#jobs-list .position-list-item");
    private By viewRole = By.xpath("//a[text()='View Role']");
    private By positionTitle = By.cssSelector(".position-title");
    private By department = By.cssSelector(".position-department");
    private By location = By.cssSelector(".position-location");
    public QualityAssurancePage(WebDriver driver) {
        super(driver);
    }

    public void clickSeeAllQAJobs() {
        clickElement(seeAllQAJobsButton);
    }



    public void filterJobs(String location, String department) {

        clickElement(departmentFilter);//click for options to be choosable
        sleep(2);
        clickElement(By.xpath("//option[text()='" + department + "']"));
        sleep(2);
        clickElement(locationFilter);//click for options to be choosable
        sleep(2);
        clickElement(By.xpath("//option[text()='" + location + "']"));
        sleep(4);
        clickElement(locationFilter);//close locations dropdown
    }

    public boolean isJobListPresent() {
        return isElementPresent(jobList);
    }

    public boolean verifyJobDetailsQA() {
        List<WebElement> jobs = driver.findElements(jobList);
        for (WebElement job : jobs) {
            String positionTitleText = job.findElement(positionTitle).getText();
            String departmentText = job.findElement(department).getText();
            String locationText = job.findElement(location).getText();
            if ((!positionTitleText.toLowerCase().contains("quality assurance")|| !positionTitleText.toUpperCase().contains("QA ")) &&
                    !departmentText.equals("Quality Assurance") &&
                    !locationText.equals("Istanbul, Turkey")) {
                return false;
            }

        }
        return true;
    }
    public boolean verifyJobResultsNavigation() {
        List<WebElement> jobs = driver.findElements(jobList);
        for (WebElement job : jobs) {
            WebElement viewRoleBtn=job.findElement(viewRole);
            String originalWindow = driver.getWindowHandle();
            clickWithJS(viewRoleBtn);
            // Yeni sekmenin açılmasını bekle
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> driver.getWindowHandles().size() > 1);
            // Yeni sekmeye geçiş yapın
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            JobDetailsPage jobDetailsPage=new JobDetailsPage(driver);
            if (!jobDetailsPage.isJobDetailsPageLever()){
                return false;
            }

            driver.close(); // Yeni sekmeyi kapat
            driver.switchTo().window(originalWindow);
        }
        return true;
    }




    public boolean isQAPageOpened() {
        return driver.getTitle().toLowerCase().contains("quality assurance") & driver.getCurrentUrl().equals(Config.CAREERS_PAGE_URL+"quality-assurance/");
    }
}

