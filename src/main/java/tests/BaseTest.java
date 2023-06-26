package tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.APIRequestSteps;
import steps.BaseSteps;
import steps.FetchingSteps;

public class BaseTest extends BaseSteps{
    @Test(description = "This test verifies the scrapped values from Yahoo Finance against the same values" +
            "taken from the API", groups = "YhFinTests")
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

        Assert.assertEquals(scrpDivYield, apiDivYield,
                "Scrapped Dividend Yield does not match value from API Request:\n");

        tearDown();
    }

}
