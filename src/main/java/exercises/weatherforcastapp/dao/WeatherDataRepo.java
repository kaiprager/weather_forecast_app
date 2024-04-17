package exercises.weatherforcastapp.dao;

import exercises.weatherforcastapp.config.WeatherForecastHTTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class WeatherDataRepo implements WeatherDataRepoInterface {

    private WeatherForecastHTTPClient weatherClient;

    @Autowired
    public WeatherDataRepo(WeatherForecastHTTPClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public String getWeatherData(String city) throws IOException {
        return weatherClient.getWeatherForecast(city);
    }
}
