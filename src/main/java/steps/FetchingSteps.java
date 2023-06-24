package steps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import pageobjects.BasePage;

import java.io.IOException;

public class FetchingSteps {

    private WebClient client;

    public FetchingSteps(WebClient client) {
        this.client = client;
    }

    public HtmlPage getHtmlPage() {
        HtmlPage page;
        try {
            page = client.getPage("https://finance.yahoo.com/quote/AAPL?p=AAPL&.tsrc=fin-srch");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return page;
    }

    public void fetchAAPL() {
        BasePage basePage = new BasePage(getHtmlPage());

        double stockPrice = Double.parseDouble(basePage.getStockPrice().getAttribute("value"));
        String mktCap = basePage.getMktCap().getTextContent();
        double peRatio = Double.parseDouble(basePage.getPERatio().getTextContent());
        double divYield = Double.parseDouble(basePage.getDivYield().getTextContent().substring(6, 10));

        System.out.println(stockPrice);
        System.out.println(mktCap);
        System.out.println(peRatio);
        System.out.println(divYield);

    }
}