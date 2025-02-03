import com.insider.pages.CareersPage;
import com.insider.pages.HomePage;
import com.insider.pages.QualityAssurancePage;
import com.insider.utils.Config;
import com.insider.utils.Driver;
import com.insider.utils.ScreenshotUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;


public class InsiderTests {
    static WebDriver driver;
    private static CareersPage careersPage;
    private static HomePage homePage;
    private static QualityAssurancePage qaPage;

    @BeforeClass
    public static void setup() {
        driver = Driver.getDriver();
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        qaPage = new QualityAssurancePage(driver);
    }
    @AfterClass
    public static void teardown() {
        driver.quit();
    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            ScreenshotUtil.takeScreenshot(driver, description.getMethodName());
        }
    };

    @Test
    public void testHomePageIsOpened() {//Visit https://useinsider.com/ and check Insider home page is opened or not
        driver.get(Config.HOME_PAGE_URL);
        assertTrue("Home Page is not opened", homePage.isHomePageOpened());
    }

    @Test
    public void testCareersPageNavigation() {// Select the “Company” menu in the navigation bar, select “Careers” and check Career
                                             // page, its Locations, Teams, and Life at Insider blocks are open or not
        driver.get(Config.HOME_PAGE_URL);
        homePage.navigateToCareers();
        assertTrue("Careers Page is not opened", careersPage.isCareersPageOpened());
        assertTrue("One or more blocks are not visible", careersPage.areBlocksVisible());
    }


    @Test
    public void testJobsFiltering() {// Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter jobs
                                     // by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the presence of the job list
        driver.get(Config.CAREERS_PAGE_URL+"quality-assurance/");
        assertTrue("QA Job Page is not opened", qaPage.isQAPageOpened());
        qaPage.clickSeeAllQAJobs();
        qaPage.filterJobs("Istanbul, Turkey", "Quality Assurance");
        assertTrue("Job list is not present", qaPage.isJobListPresent());
    }


    @Test
    public void testFilterResults() {//Check that all jobs’ Position contains “Quality Assurance”, Department contains
                                     // “Quality Assurance”, and Location contains “Istanbul, Turkey”
        driver.get(Config.CAREERS_PAGE_URL+"quality-assurance/");
        qaPage.clickSeeAllQAJobs();
        qaPage.filterJobs("Istanbul, Turkey", "Quality Assurance");
        assertTrue("Job details are incorrect", qaPage.verifyJobDetailsQA());
    }

    @Test
    public void testFilterResultsNavigation() {//Click the “View Role” button and check that this action redirects us to the Lever Application form page
        driver.get(Config.CAREERS_PAGE_URL+"quality-assurance/");
        qaPage.clickDeclineCookies();
        qaPage.clickSeeAllQAJobs();
        qaPage.filterJobs("Istanbul, Turkey", "Quality Assurance");
        assertTrue("Job details are incorrect", qaPage.verifyJobResultsNavigation());
    }


}
