package exercises.weatherforcastapp.dao;

import java.io.IOException;

public interface WeatherDataRepoInterface {

    String getWeatherData(String city) throws IOException;
}
