package conversor.json;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class JsonParserService {

    public Map<String, Double> extraerTasas(String json, Set<String> monedasDeseadas) {
        Map<String, Double> tasas = new HashMap<>();

        try {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            if (!jsonObject.has("conversion_rates")) {
                System.err.println("⚠️ El JSON no contiene el campo 'conversion_rates'");
                return tasas;
            }

            JsonObject conversiones = jsonObject.getAsJsonObject("conversion_rates");

            for (String moneda : monedasDeseadas) {
                if (conversiones.has(moneda)) {
                    double tasa = conversiones.get(moneda).getAsDouble();
                    tasas.put(moneda, tasa);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error al parsear el JSON: " + e.getMessage());
        }

        return tasas;
    }
}
