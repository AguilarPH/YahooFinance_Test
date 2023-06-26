package tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import steps.APIRequestSteps;
import steps.BaseSteps;
import steps.FetchingSteps;

public class BaseTest extends BaseSteps{

    public void testYahooScrapping() {
        FetchingSteps fetching = new FetchingSteps(client);
        APIRequestSteps apiRequest = new APIRequestSteps();
        JSONObject aaplData;

        double scrpStockPrice = fetching.scrapStockPrice();
        String scrpMktCap = fetching.scrapMktCap();
        double scrpPERatio =  fetching.scrapPERatio();
        double scrpDivYield = fetching.scrapDivYield();

        aaplData = apiRequest.yahooFinance("AAPL", "price");
        double apiStockPrice = apiRequest.getRegularMarketPrice(aaplData);
        String apiMktCap = apiRequest.getMktCap(aaplData);

        aaplData = apiRequest.yahooFinance("AAPL", "summaryDetail");
        double apiDivYield = apiRequest.getDividendYield(aaplData);

        Assert.assertEquals(scrpStockPrice, apiStockPrice,
                "Scrapped current stock price is not matching the value from API request:\n");

        Assert.assertEquals(scrpMktCap, apiMktCap,
                "Scrapped market capitalization does not match value from API request:\n");

        tearDown();
    }

}
