package tests;

import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import steps.BaseSteps;
import steps.FetchingSteps;

public class BaseTest extends BaseSteps{

    public void testYahooScrapping() {
        FetchingSteps fetchingSteps = new FetchingSteps(client);

        fetchingSteps.fetchAAPL();
    }

}
