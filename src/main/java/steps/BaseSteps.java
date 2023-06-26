package steps;

import com.gargoylesoftware.htmlunit.WebClient;

public class BaseSteps {
    public WebClient client;
    public BaseSteps() {
        this.client = getClient();
    }

    private WebClient getClient() {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        return webClient;
    }

    public void tearDown() {
        client.getCurrentWindow().getJobManager().removeAllJobs();
        client.close();
        client.getCurrentWindow().getJobManager().shutdown();
    }
}
