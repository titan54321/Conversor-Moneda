

import java.io.IOException;
import java.util.Scanner;

/**
 * La clase CurrencyConverter es la clase principal que gestiona la conversión de monedas.
 * Ofrece un menú interactivo al usuario para seleccionar la conversión deseada y mostrar el resultado.
 */
public class CurrencyConverter {

    private final CurrencyApiClient apiClient;

    /**
     * Constructor que inicializa el cliente de la API de conversión de moneda.
     */
    public CurrencyConverter() {
        this.apiClient = new CurrencyApiClient();
    }

    /**
     * Método principal que inicia el menú de conversión de moneda.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Muestra el menú de opciones al usuario
            System.out.println("-----------------------------------");
            System.out.println("Sea bienvenido/a al Conversor de Moneda");
            System.out.println("");
            System.out.println("1) Dólar       --> Peso Argentino");
            System.out.println("2) Peso Chileno  --> Dólar");
            System.out.println("3) Real Brasileño  --> Peso Colombiano");
            System.out.println("4) Salir");

            // Lee la opción seleccionada por el usuario
            int opcion = scanner.nextInt();

            // Si el usuario selecciona 4, sale del bucle
            if (opcion == 4) {
                break;
            }

            // Define las monedas de origen y destino basadas en la opción seleccionada
            String fromCurrency = "";
            String toCurrency = "";

            switch (opcion) {
                case 1:
                    fromCurrency = "USD";  // Dólar
                    toCurrency = "ARS";    // Peso Argentino
                    break;
                case 2:
                    fromCurrency = "CLP";  // Peso Chileno
                    toCurrency = "USD";    // Dólar
                    break;
                case 3:
                    fromCurrency = "BRL";  // Real Brasileño
                    toCurrency = "COP";    // Peso Colombiano
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 4.");
                    continue;
            }

            // Solicita al usuario el monto a convertir
            System.out.println("Ingrese el monto a convertir:");
            double amount = scanner.nextDouble();

            try {
                // Obtiene la tasa de cambio entre las dos monedas
                double rate = apiClient.getExchangeRate(fromCurrency, toCurrency);
                // Realiza la conversión de moneda
                double convertedAmount = amount * rate;
                // Crea un objeto ConversionResult para almacenar el resultado
                ConversionResult result = new ConversionResult(amount, fromCurrency, toCurrency, rate, convertedAmount);
                // Muestra el resultado de la conversión
                System.out.println(result);
            } catch (IOException | InterruptedException e) {
                // Maneja errores al obtener la tasa de cambio
                System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            }
        }

        // Cierra el objeto Scanner al finalizar el programa
        scanner.close();
        System.out.println("Gracias por usar el Conversor de Moneda. ¡Adiós!");
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.start();
    }
}
