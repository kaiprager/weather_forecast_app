package exercises.weatherforcastapp.config;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherForecastHTTPClient {

    private static final String API_KEY = "8d6ea80c6e940739381b1e3a1aff43d7";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    public String getWeatherForecast(String city) throws IOException {

        // Create HttpClient Instance
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String url = String.format(API_URL, city, API_KEY);

            // Create HTTP GET request
            HttpGet request = new HttpGet(url);

            // Send HTTP request and retrieve response
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // Process response
                String result = EntityUtils.toString(response.getEntity());

                // Return weather forecast data
                return result;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
