package conversor.historial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {

    private final List<String> historial;

    public HistorialConversiones() {
        this.historial = new ArrayList<>();
    }
    public void registrar(double monto, String monedaBase, double resultado, String monedaDestino) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String registro = String.format("[%s] %.2f %s → %.2f %s",
                timestamp, monto, monedaBase, resultado, monedaDestino);

        historial.add(registro);
    }
    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("📭 No hay conversiones registradas.");
        } else {
            System.out.println("\n📜 Historial de Conversiones:");
            historial.forEach(System.out::println);
        }
    }
}

