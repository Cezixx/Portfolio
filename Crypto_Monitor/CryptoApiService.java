import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CryptoApiService {
    private final HttpClient client = HttpClient.newHttpClient();
    
    public CryptoRate getCryptoRate(String code) {
       String coinId = code.trim().toLowerCase();
       String urlStr = "https://api.coingecko.com/api/v3/simple/price?ids=" + coinId + "&vs_currencies=pln";

        try {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlStr))
            .GET()
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonBody = response.body();
                Pattern pattern = Pattern.compile("\"pln\"\\s*:\\s*([0-9.]+)");
                Matcher matcher = pattern.matcher(jsonBody);

                if (matcher.find()) {
                    double rate = Double.parseDouble(matcher.group(1));
                    return new CryptoRate(coinId, code.trim().toUpperCase(), rate, "PLN"); 
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Błąd połączenia API: " + e.getMessage());
        }
        return null;
    }
}
