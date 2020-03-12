
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class ExtentReport_DARK {
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = new ExtentReports("target//extentReports-DARK.html", true);
        extent.loadConfig(new File("src/main/resources/extent-config.xml"));
    }

    @BeforeMethod
    public void setUp(Method method){
        test = extent.startTest((this.getClass().getSimpleName() + " : " + method.getName()), method.getName());
        test.assignAuthor("Author name");
        test.assignCategory("Smoke Test");
        //test.log(LogStatus.PASS, "Test passed message");
    }

    @Test
    public void testCase1() {
        //test.log(LogStatus.PASS, "Test passed message");
        Assert.assertTrue(true);
    }

    @Test
    public void testCase2() {
        //test.log(LogStatus.PASS, "Test passed message");
        Assert.assertTrue(true);
    }

    @Test
    public void testCase5() {
        //test.log(LogStatus.SKIP, "Test passed message");
        throw new SkipException("Skipping this test with exception");
    }

    @Test
    public void testCase6(){
        Assert.assertTrue(false);
        //test.log(LogStatus.FAIL, "Test passed message");
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            //test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.log(LogStatus.FAIL, result.getName(), "Test was Unsuccessful");
            test.log(LogStatus.FAIL, result.getThrowable());
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, result.getName(), "Test was successful");
            //test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            //test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.log(LogStatus.SKIP, result.getName(), "Test was SKIPPED");
            //test.skip(result.getThrowable());
            test.log(LogStatus.SKIP, result.getThrowable());
        }
        //test.log(LogStatus.PASS, "Test passed message");
        extent.endTest(test);
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        extent.close();
    }

}
