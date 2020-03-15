package Tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.Screenshot;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;
    protected Screenshot screenshot;
    protected static final Logger logger = LogManager.getLogger("Log");
    private ExtentReports extent;
    protected ExtentTest reporter;

    protected Test test;

    @BeforeSuite
    public void startReport() {
        extent = new ExtentReports("target//extentReports-DARK.html", true);
        extent
                .addSystemInfo("Host Name", "TLA BootCamp")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User name", "Kuba");
        extent.loadConfig(new File("src/main/resources/extent-config.xml"));
    }

    @BeforeMethod
    public void setUp(Method method){
        initializeWebDriver();
        test = method.getAnnotation(Test.class);
        logger.info("STARTING test execution of Test: " + method.getName());

        reporter = extent.startTest(test.description());  //extent.startTest((this.getClass().getSimpleName())); //can add description to Report here
        //test.assignAuthor("Author name");
        reporter.assignCategory(test.description());
        screenshot = new Screenshot(driver, reporter);
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result){
        logger.info("ENDING test execution of Test: " + method.getName());
        if(result.getStatus() == ITestResult.FAILURE) {
            //test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            reporter.log(LogStatus.FAIL, result.getName(), "Test was Unsuccessful");
            reporter.log(LogStatus.FAIL, result.getThrowable());
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            reporter.log(LogStatus.PASS, result.getName(), "Test was successful");
        }
        else {
            reporter.log(LogStatus.SKIP, result.getName(), "Test was SKIPPED");
            //test.skip(result.getThrowable());
            reporter.log(LogStatus.SKIP, result.getThrowable());
        }
        extent.endTest(reporter);
        driver.close();
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        extent.close();
    }

    public void initializeWebDriver(){
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");  //To get rid of console Warning outputs
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }


}
