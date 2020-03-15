package ParallelRun;

import org.testng.annotations.Test;


public class PHPTravels_Test2 extends TestBase1{

    @Test(description = "BestBuy Test")
    public void bestBuy(){
        driver().get("https://bestbuy.com");
        logger.info(test.description() + " | Title: " + driver().getTitle());
    }

    @Test(description = "CNN Test")
    public void cnn(){
        driver().get("https://cnn.com");
        logger.info(test.description() + " | Title: " + driver().getTitle());
    }

    @Test(description = "YouTube Test")
    public void youTube(){
        driver().get("https://youtube.com");
        logger.info(test.description() + " | Title: " + driver().getTitle());
    }


}
