
import org.testng.annotations.Test;
import util.ConfigReader;

public class PHPTravels_Test extends TestBase{

    @Test
    public void test1(){
        System.out.println(ConfigReader.readProperty("browser"));
    }



}
