package exercises.weatherforcastapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercises.weatherforcastapp.dao.WeatherDataRepoInterface;
import exercises.weatherforcastapp.entity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherDataService implements WeatherDataInterface {

    private WeatherDataRepoInterface weatherDataRepoInterface;
    private ObjectMapper objectMapper;

    @Autowired
    public WeatherDataService(WeatherDataRepoInterface weatherDataRepoInterface, ObjectMapper objectMapper) {
        this.weatherDataRepoInterface = weatherDataRepoInterface;
        this.objectMapper = objectMapper;
    }

    @Override
    public WeatherData getWeatherForecast(String city) throws IOException {
        String weatherDataJson = weatherDataRepoInterface.getWeatherData(city);
        return objectMapper.readValue(weatherDataJson, WeatherData.class);
    }

    @Override
    public String getFormattedTemperature(double temperatureKelvin) {
        double temperatureCelsius = temperatureKelvin - 273.15;
        return String.format("%.2f", temperatureCelsius);
    }
}
