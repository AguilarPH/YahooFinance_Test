package steps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class FetchingSteps {

    private WebClient client;

    public FetchingSteps(WebClient client) {
        this.client = client;
    }
    public HtmlPage getHtmlPage(){
        HtmlPage page = null;
        try {
            page = client.getPage("https://finance.yahoo.com/quote/AAPL?p=AAPL&.tsrc=fin-srch");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return page;
    }


}
