package Weather_App;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherService weatherService = new WeatherService();

        System.out.println("--- APLIKACJA POGODOWA ---");
        System.out.println("Podaj nazwę miasta: ");
        String cityName = scanner.nextLine();

        Weather weather = weatherService.getWeather(cityName);

        if(weather != null) {
            System.out.println("\n=== AKTUALNA POGODA ===");
            System.out.println("Miasto: " + weather.getCityName());
            System.out.println("Temperatura: " + weather.getTemperature() + " °C");
            System.out.println("Wilgotność: " + weather.getHumidity() + " %");
            System.out.println("Opis: " + weather.getDescription());
        }else {
            System.out.println("Nie udało się wyświetlić pogody dla podanego miasta.");
        }
        
        scanner.close();
    }    
}
