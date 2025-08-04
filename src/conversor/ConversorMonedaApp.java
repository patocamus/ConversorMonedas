package conversor;

import conversor.api.ServicioApi;
import conversor.json.JsonParserService;
import conversor.historial.HistorialConversiones;
import conversor.logica.ConversorMoneda;

import java.util.Scanner;
import java.util.Set;
import java.util.Map;

public class ConversorMonedaApp {

    private static final Set<String> MONEDAS_DISPONIBLES = Set.of("ARS", "BOB", "BRL", "CLP", "COP", "USD");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicioApi servicioApi = new ServicioApi();
        JsonParserService jsonParser = new JsonParserService();
        ConversorMoneda conversor = new ConversorMoneda();
        HistorialConversiones historial = new HistorialConversiones();

        double monto = 0;
        double resultado = 0;
        String monedaBase = "";
        String monedaDestino = "";

        System.out.println("--------------------------------------------------");
        System.out.println("  Bienvenido/a al Conversor de Moneda 🪙");
        System.out.println("--------------------------------------------------");

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSeleccione la moneda BASE:");
            mostrarOpciones();

            monedaBase = scanner.nextLine().toUpperCase();
            if (!MONEDAS_DISPONIBLES.contains(monedaBase)) {
                System.out.println("Moneda no válida. Intente nuevamente.");
                continue;
            }

            System.out.println("Seleccione la moneda DESTINO:");
            mostrarOpciones();

            monedaDestino = scanner.nextLine().toUpperCase();
            if (!MONEDAS_DISPONIBLES.contains(monedaDestino)) {
                System.out.println("Moneda no válida. Intente nuevamente.");
                continue;
            }

            if (monedaBase.equals(monedaDestino)) {
                System.out.println("La moneda base y destino no pueden ser iguales.");
                continue;
            }

            System.out.print("Ingrese el monto a convertir: ");
            try {
                monto = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Monto inválido. Intente nuevamente.");
                continue;
            }

            String json = servicioApi.obtenerDatos(monedaBase);

            Map<String, Double> tasas = jsonParser.extraerTasas(json, MONEDAS_DISPONIBLES);

            if (!tasas.containsKey(monedaDestino)) {
                System.out.println("No se encontró la tasa de cambio para " + monedaDestino);
                continue;
            }

            double tasa = tasas.get(monedaDestino);
            resultado = conversor.convertir(monto, tasa);

            System.out.printf("✔️ %.2f %s equivale a %.2f %s\n",
                    monto, monedaBase, resultado, monedaDestino);

            historial.registrar(monto, monedaBase, resultado, monedaDestino);

            System.out.print("¿Desea realizar otra conversión? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            continuar = respuesta.equals("s");
        }

        System.out.println("\n¿Desea ver el historial de conversiones? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            historial.mostrarHistorial();
        }

        System.out.println("Gracias por utilizar el conversor de monedas 🧾");
    }

    private static void mostrarOpciones() {
        System.out.println("Opciones disponibles:");
        MONEDAS_DISPONIBLES.forEach(moneda -> System.out.println(" - " + moneda));
        System.out.print("Ingrese la moneda deseada: ");
    }
}

