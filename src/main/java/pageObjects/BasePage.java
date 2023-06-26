package pageObjects;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BasePage{

    private HtmlPage page;

    public BasePage(HtmlPage page) {
        this.page = page;
    }

    private HtmlElement stockPrice() {
        HtmlElement currentPrice = page.getFirstByXPath("//fin-streamer[@data-symbol='AAPL' and @data-field='regularMarketPrice']");
        return currentPrice;
    }
    public HtmlElement getStockPrice() {
        return stockPrice();
    }
    private HtmlElement mktCap() {
        HtmlElement marketCapitalization = page.getFirstByXPath("//td[@data-test='MARKET_CAP-value']");
        return marketCapitalization;
    }
    public HtmlElement getMktCap() {
        return mktCap();
    }
    private HtmlElement peRatio() {
        HtmlElement priceEarningRatio = page.getFirstByXPath("//td[@data-test='PE_RATIO-value']");
        return priceEarningRatio;
    }
    public HtmlElement getPERatio() {
        return peRatio();
    }
    private HtmlElement divYield() {
        HtmlElement dividentYield = page.getFirstByXPath("//td[@data-test='DIVIDEND_AND_YIELD-value']");
        return dividentYield;
    }
    public HtmlElement getDivYield() {
        return divYield();
    }
    private HtmlElement earnPerShare() {
        HtmlElement earningPerShare = page.getFirstByXPath("//td[@data-test='EPS_RATIO-value']");
        return earningPerShare;
    }
    public HtmlElement getEarnPerShare() {
        return earnPerShare();
    }

}
