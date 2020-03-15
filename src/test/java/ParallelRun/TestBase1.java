package ParallelRun;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase1 {
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    protected static final Logger logger = LogManager.getLogger("Log");
    protected Test test;


    @BeforeMethod
    public void setUp(Method method){
        initializeWebDriver();
        test = method.getAnnotation(Test.class);
        logger.info("STARTING test execution of Test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result){
        logger.info("ENDING test execution of Test: " + method.getName());
        driver().close();
    }

    public void initializeWebDriver(){
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");  //To get rid of console Warning outputs
        WebDriver driver = new ChromeDriver();
        drivers.set(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    public WebDriver driver(){
        WebDriver driver = drivers.get();
        if (driver == null) {
            throw new IllegalStateException("Driver should have not been null.");
        }
        return driver;
    }



}

