package steps;

import com.gargoylesoftware.htmlunit.WebClient;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseSteps {
    public WebClient client;
    public BaseSteps() {
        this.client = getClient();
    }
    @BeforeTest
    private WebClient getClient() {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        return webClient;
    }
    @AfterTest
    public void tearDown() {
        client.getCurrentWindow().getJobManager().removeAllJobs();
        client.close();
        client.getCurrentWindow().getJobManager().shutdown();
    }
}
