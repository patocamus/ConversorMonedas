package conversor.api;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicioApi {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/5be402ba052973b816ec6a14/latest/";

        private final HttpClient client;

        public ServicioApi() {
            this.client = HttpClient.newHttpClient();
        }
        public String obtenerDatos(String monedaBase) {
            String url = BASE_URL + monedaBase;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                int status = response.statusCode();
                if (status == 200) {
                    return response.body();
                } else {
                    System.err.println("Error: Código de estado HTTP " + status);
                    return "{}";
                }

            } catch (IOException | InterruptedException e) {
                System.err.println("Error al conectar con la API: " + e.getMessage());
                return "{}";
            }
        }
}
