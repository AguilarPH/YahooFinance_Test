package steps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import tests.BaseTest;

import java.io.IOException;
import java.time.Duration;

public class BaseSteps {
    public WebDriver driver;
    public WebClient client;
    Actions customActions;
    JavascriptExecutor js;

    public BaseSteps() {
        this.driver = getDriver();
        this.client = getClient();
        this.customActions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }
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
    public void goToAPPLYahoo() {
        driver.navigate().to("https://finance.yahoo.com/quote/AAPL?p=AAPL&.tsrc=fin-srch");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

}
