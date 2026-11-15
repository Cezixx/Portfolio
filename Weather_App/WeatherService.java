package Weather_App;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    private final String API_KEY = "a4d56928a67ecc1128e7ac9df6dab6f3";
    
    public Weather getWeather(String cityName) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + API_KEY + "&units=metric";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
                System.out.println("Błąd: Nie znaleziono miasta lub zły klucz API (Kod: \" + response.statusCode() + \") )");
                return null;
            }

            String jsonResponse = response.body();

            double temperature = parseTemperature(jsonResponse);
            int humidity = parseHumidity(jsonResponse);
            String description = parseDescription(jsonResponse);

            return new Weather(cityName, temperature, humidity, description);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Wystąpił błąd podczas połączenia z siecią: " + e.getMessage());
            return null;
        }
    }

    private double parseTemperature(String json) {
        int index = json.indexOf("\"temp\":");
        int startIndex = index + 7;
        int endIndex = json.indexOf(",", startIndex);
        String tempStr = json.substring(startIndex, endIndex);
        return  Double.parseDouble(tempStr);
    }

    private int parseHumidity(String json) {
        int index = json.indexOf("\"humidity\":");
        int startIndex = index + 11;
        int endIndex = json.indexOf(",", startIndex);
        String humStr = json.substring(startIndex, endIndex);
        return  Integer.parseInt(humStr);
    }
    private String parseDescription(String json) {
        int index = json.indexOf("\"description\":");
        int startIndex = index + 15;
        int endIndex = json.indexOf(",", startIndex);
        return json.substring(index, endIndex);
    }
}
