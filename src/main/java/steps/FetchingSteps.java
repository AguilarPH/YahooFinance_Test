package steps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.testng.annotations.BeforeGroups;
import pageObjects.BasePage;

import java.io.IOException;

public class FetchingSteps {

    private WebClient client;
    private  HtmlPage page;

    public FetchingSteps() {

    }
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
    public double scrapMktCap() {
        BasePage basePage = new BasePage(page);

        String strMktCap = basePage.getMktCap().getTextContent();
        String power = strMktCap.substring(strMktCap.length() - 1);
        double value = Double.parseDouble(strMktCap.substring(0, strMktCap.length() - 1));

        switch (power) {
            case "T":
                break;
            case "G":
                value = value/ Math.pow(10, 3);
                break;
            case "M":
                value = value / Math.pow(10, 6);
            default:
                System.out.println("Market capitalization notation out of range: " + value + power);
        }

        return value;
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