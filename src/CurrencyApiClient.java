import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class CurrencyApiClient {

    // Reemplaza con tu clave de API personal
    private static final String API_KEY = "25d2105c990af9de198a9d3a";
    // URL base de la API de tasas de cambio, usando la clave de API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private final HttpClient client;

    /**
     * Constructor que inicializa el cliente HTTP para realizar solicitudes a la API.
     */
    public CurrencyApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    public double getExchangeRate(String fromCurrency, String toCurrency) throws IOException, InterruptedException {
        // Construye la URL de solicitud para obtener tasas de cambio para la moneda de origen
        String requestUrl = API_URL + fromCurrency;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .build();

        // Envía la solicitud HTTP y obtiene la respuesta en formato de cadena
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Analiza la respuesta JSON y extrae la tasa de cambio
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");
        // Devuelve la tasa de cambio para la moneda de destino
        return rates.get(toCurrency).getAsDouble();
    }
}
