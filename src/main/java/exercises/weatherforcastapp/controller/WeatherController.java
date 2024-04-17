package exercises.weatherforcastapp.controller;

import exercises.weatherforcastapp.entity.WeatherData;
import exercises.weatherforcastapp.service.WeatherDataInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class WeatherController {

    private WeatherDataInterface weatherDataInterface;

    @Autowired
    public WeatherController(WeatherDataInterface weatherDataInterface) {
        this.weatherDataInterface = weatherDataInterface;
    }

    @GetMapping("/weather")
    public String showForm() {
        return "weather-form";
    }

    @PostMapping("/forecast")
    public String getWeatherForecast(@RequestParam String city, Model model) {
        try {
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            WeatherData weatherData = weatherDataInterface.getWeatherForecast(encodedCity);
            if (weatherData != null && weatherData.getMain() != null) {
                double temperatureKelvin = weatherData.getMain().getTemp();
                String formattedTemperature = weatherDataInterface.getFormattedTemperature(temperatureKelvin);
                model.addAttribute("weatherData", weatherData);
                model.addAttribute("formattedTemperature", formattedTemperature);
                return "weather-result";
            } else {
                model.addAttribute("error", "Weather data not available for the specified city.");
                return "weather-form";
            }
        } catch (HttpClientErrorException.NotFound e) {
            model.addAttribute("error", "City not found. Please enter another city.");
            return "weather-form";
        } catch (IOException e) {
            model.addAttribute("error", "Error fetching weather data.");
            e.printStackTrace();
            return "weather-form";
        }
    }
}
