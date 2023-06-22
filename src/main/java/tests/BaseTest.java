package tests;

import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    public WebDriver driver = getDriver();
    public WebClient client = getClient();
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/ODA5/YahooFinance_Scrapping/chromedriver");
        WebDriver webDriver = new ChromeDriver();

        return webDriver;
    }
    private WebClient getClient() {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        return webClient;
    }
}
