package pl.sda.CurrencyConverter_RZ;

import pl.sda.CurrencyConverter_RZ.model.Currencies;
import pl.sda.CurrencyConverter_RZ.service.CurrencyService;

import java.io.IOException;

/**
 * @author Remigiusz Zudzin
 */
public class Main {

    static void checkCurrencyConvertionRate(Double amount, String fromCurrency, String date) throws IOException {
        CurrencyService currencyService = new CurrencyService();
        Currencies currencies = currencyService.getCurrencyValue(date, fromCurrency);
        currencies.getRates().
        double exchangeRatio = currencies.getRates().getEUR();
        double value = amount * exchangeRatio;
        System.out.println(amount + " " + fromCurrency + " na PLN: " + String.format("%.2f%n", value));
    }

    static void checkCurrencyConvertionRate(Integer amount, String fromCurrency, String date) throws IOException {
        CurrencyService currencyService = new CurrencyService();
        Currencies currencies = currencyService.getCurrencyValue(date, fromCurrency);
        Double value = Double.valueOf(amount) * currencies.getRates().getPLN();
        System.out.println("Na dzień: " + date);
        System.out.println(amount + " " + fromCurrency + " na PLN: " + String.format("%.2f%n", value));
    }

    public static void main(String[] args) throws IOException {

//        checkCurrencyConvertionRate(25.8, "EUR", "2019-04-20");
        checkCurrencyConvertionRate(25.8, "PLN", "2019-04-20");


    }

}
