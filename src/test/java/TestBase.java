import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {
    public WebDriver driver;
    public static final Logger logger = LogManager.getLogger("Log");

    @BeforeMethod
    public void setUp(Method method){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("STARTING test execution of Test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(Method method){
        driver.close();
        logger.info("ENDING test execution of Test: " + method.getName());
    }


}
