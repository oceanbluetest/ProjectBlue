
import Tests.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import util.Screenshot;

public class PHPTravels_Test extends TestBase {

    @Test(description = "Amazon Test")
    public void testAmazon(){
        ///WebDriver driver = initializeWebDriver();
        //Screenshot screenshot = new Screenshot(driver, reporter);
        driver.get("https://amazon.com");
        reporter.log(LogStatus.INFO,driver.getTitle());
        screenshot.takeScreenshotAndLog();
        //test.log(LogStatus.PASS, "Screenshot below: " + test.addBase64ScreenShot(Screenshot.takeScreenshot(driver, test)));
    }

    @Test(description = "Google Test")
    public void testGoogle(){
        //WebDriver driver = initializeWebDriver();
        //Screenshot screenshot = new Screenshot(driver, reporter);
        driver.get("https://google.com");
        reporter.log(LogStatus.INFO, driver.getTitle());
        screenshot.takeScreenshotAndLog();
    }

    @Test(description = "TLA Test")
    public void testTLA(){
        //WebDriver driver = initializeWebDriver();
        //Screenshot screenshot = new Screenshot(driver, reporter);
        driver.get("https://techleadacademy.io");
        reporter.log(LogStatus.INFO, driver.getTitle());
        screenshot.takeScreenshotAndLog();
    }



}
