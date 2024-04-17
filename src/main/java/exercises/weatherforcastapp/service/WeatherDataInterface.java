package exercises.weatherforcastapp.service;

import exercises.weatherforcastapp.entity.WeatherData;

import java.io.IOException;

public interface WeatherDataInterface {

    WeatherData getWeatherForecast(String city) throws IOException;

    String getFormattedTemperature(double temperatureKelvin);
}
