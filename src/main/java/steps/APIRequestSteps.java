package steps;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class APIRequestSteps {
    public JSONObject yahooFinance(String symbol, String module) {
        JSONObject jsonResponse = new JSONObject();

        try {
            URL url = new URL("https://query1.finance.yahoo.com/v10/finance/quoteSummary/" +
                    symbol + "?modules=" + module);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            response.delete(0, 29 + module.length() + 2);
            response.delete(response.length() - 17, response.length());
            jsonResponse = new JSONObject(response.toString());

            reader.close();

            connection.disconnect();
        } catch (IOException | JSONException | StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    public double getRegularMarketPrice(JSONObject jsonQuery) {
        double value;
        try {
            JSONObject result = (JSONObject) jsonQuery.get("regularMarketPrice");
            value = Double.parseDouble(result.get("fmt").toString());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public double getMktCap(JSONObject jsonQuery) {
        double value;
        try {
            JSONObject result = (JSONObject) jsonQuery.get("marketCap");

            value = Double.parseDouble(result.get("raw").toString()) / Math.pow(10, 12);

            value = BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).doubleValue();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return value;
    }
    public double getPERatio(JSONObject jsonQuery) {
        double value;
        try {
            JSONObject result = (JSONObject) jsonQuery.get("regularMarketOpen");
            value = Double.parseDouble(result.get("fmt").toString());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
    public double getDividendYield(JSONObject jsonQuery) {
        double value;
        try {
            JSONObject result = (JSONObject) jsonQuery.get("dividendYield");
            StringBuilder sb = new StringBuilder(result.get("fmt").toString());
            sb.deleteCharAt(sb.length() - 1);
            value = Double.parseDouble(sb.toString());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}