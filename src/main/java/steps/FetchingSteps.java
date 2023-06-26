package steps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.testng.annotations.BeforeGroups;
import pageObjects.BasePage;

import java.io.IOException;

public class FetchingSteps {

    private WebClient client;
    private  HtmlPage page;
    public FetchingSteps(WebClient client) {
        this.client = client;
        this.page = getHtmlPage();
    }
    @BeforeGroups
    private HtmlPage getHtmlPage() {
        HtmlPage page;
        try {
            page = client.getPage("https://finance.yahoo.com/quote/AAPL?p=AAPL&.tsrc=fin-srch");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return page;
    }

    public double scrapStockPrice() {
        BasePage basePage = new BasePage(page);

        double stockPrice = Double.parseDouble(basePage.getStockPrice().getAttribute("value"));

        return stockPrice;
    }
    public String scrapMktCap() {
        BasePage basePage = new BasePage(page);

        StringBuilder sbMktCap = new StringBuilder();
        sbMktCap.append(basePage.getMktCap().getTextContent());
        sbMktCap.deleteCharAt(sbMktCap.length() - 1);
        System.out.println(sbMktCap);

        return sbMktCap.toString();
    }
    public double scrapPERatio() {
        BasePage basePage = new BasePage(page);
        double peRatio = Double.parseDouble(basePage.getPERatio().getTextContent());

        return peRatio;
    }
    public double scrapDivYield() {
        BasePage basePage = new BasePage(page);
        double divYield = Double.parseDouble(basePage.getDivYield().getTextContent().substring(6, 10));

        return divYield;
    }
}