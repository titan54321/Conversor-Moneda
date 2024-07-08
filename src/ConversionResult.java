/**
 * La clase ConversionResult representa el resultado de una conversión de moneda.
 * Almacena el monto original, las monedas de origen y destino, la tasa de cambio, y el monto convertido.
 */
public class ConversionResult {
    private final double amount;
    private final String fromCurrency;
    private final String toCurrency;
    private final double rate;
    private final double convertedAmount;

    /**
     * Constructor para inicializar el resultado de la conversión.
     *
     * @param amount          El monto original a convertir.
     * @param fromCurrency    La moneda de origen.
     * @param toCurrency      La moneda de destino.
     * @param rate            La tasa de cambio entre las dos monedas.
     * @param convertedAmount El monto convertido a la moneda de destino.
     */
    public ConversionResult(double amount, String fromCurrency, String toCurrency, double rate, double convertedAmount) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
        this.convertedAmount = convertedAmount;
    }

    // Métodos getters para obtener los valores de los campos

    public double getAmount() {
        return amount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getRate() {
        return rate;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    /**
     * Devuelve una representación en cadena del resultado de la conversión.
     *
     * @return Una cadena que muestra el monto original, la tasa de cambio y el monto convertido.
     */
    @Override
    public String toString() {
        return String.format("Monto: %.2f %s\nTasa de cambio: %.4f\nMonto convertido: %.2f %s",
                amount, fromCurrency, rate, convertedAmount, toCurrency);
    }
}
