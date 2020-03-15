package ParallelRun;

import org.testng.Assert;
import org.testng.annotations.*;
import util.RetryAnalyzer;


public class PHPTravels_Test1 extends TestBase1{
    @Test(description = "Amazon Test")
    public void testAmazon(){
        driver().get("https://amazon.com");
        logger.info(test.description() + " | Title: " + driver().getTitle());
    }

    @Test(description = "Google Test")
    public void testGoogle(){
        driver().get("https://google.com");
        logger.info(test.description() + " | Title: " + driver().getTitle());
    }

    @Test(description = "TLA Test")
    public void testTLA(){
        driver().get("https://techleadacademy.io");
        logger.info(test.description() + " | Title: " + driver().getTitle());
    }

    //===================FOR RE-RUN FAILED TESTS===============
//    @Test(description = "Retry Test", retryAnalyzer = RetryAnalyzer.class)
//    public void retryTest() throws InterruptedException {
//        driver().get("https://amazon.com");
//        logger.info(test.description() + " | Title: " + driver().getTitle());
//        Thread.sleep(2000);
//        Assert.fail();
//    }


}
