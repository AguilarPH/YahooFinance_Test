package steps;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import tests.BaseTest;

import java.time.Duration;

public class BaseSteps extends BaseTest {
    Actions customActions;
    JavascriptExecutor js;

    public BaseSteps() {
        this.customActions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    public void goToAPPLYahoo() {
        driver.navigate().to("https://finance.yahoo.com/quote/AAPL?p=AAPL&.tsrc=fin-srch");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public HtmlPage getHtmlPage(){
        String searchUrl = "https://finance.yahoo.com/quote/AAPL?p=AAPL&.tsrc=fin-srch";
        HtmlPage page = client.getPage(searchUrl);
        return page;
    }

}
