package project.local.api;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class MyDataAPIClient {

    private static final String USER_AGENT = "x-api-ket";
    private static final String GET_URL = "https://localhost:8080/v2/card/cards";

    public static void sendGet() throws IOException, ParseException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(GET_URL);

        httpGet.addHeader("USER-AGENT", USER_AGENT);
        httpGet.addHeader("Content-type", "application/json");

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        System.out.println("GET Response Status");
        System.out.println(httpResponse.getCode());
        String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        System.out.println(json);

        httpClient.close();
    }
}
