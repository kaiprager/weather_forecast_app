package exercises.weatherforcastapp.entity;

public class WeatherInfo {

    private String description;


    // Constructor

    public WeatherInfo() {
    }

    public WeatherInfo(String description) {
        this.description = description;
    }


    // Getters and Setters


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
