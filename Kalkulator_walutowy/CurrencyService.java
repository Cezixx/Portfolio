import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyService {
    private final HttpClient client = HttpClient.newHttpClient();

    public Currency getCurrencyRate(String code) {
        String urlStr = "https://api.nbp.pl/api/exchangerates/rates/a/" + code.trim().toUpperCase() + "/?format=json";

        try {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlStr))
            .GET()
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200) {
                String jsonBody = response.body();
                Pattern pattern = Pattern.compile("\"mid\":([0-9.]+)");
                Matcher matcher = pattern.matcher(jsonBody);

                if (matcher.find()) {
                    double rate = Double.parseDouble(matcher.group(1));
                    return new Currency(code.toUpperCase(), rate);
                }
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Błąd połaczenia z API: " + e.getMessage());
        }
        return null;
    }
}
