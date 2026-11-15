package Weather_App;

public class Weather {
    private String cityName;
    private double temperature;
    private int humidity;
    private String description;

    public Weather(String cityName, double temperature, int humidity, String description ) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
    }
    
    public String getCityName() {
        return this.cityName;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public String getDescription() {
        return this.description;
    }
}
