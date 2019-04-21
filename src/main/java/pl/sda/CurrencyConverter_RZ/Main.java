package pl.sda.CurrencyConverter_RZ;

import pl.sda.CurrencyConverter_RZ.service.CurrencyService;

import java.math.BigDecimal;

/**
 * @author Remigiusz Zudzin
 */
public class Main {

    public static void main(String[] args) {

        CurrencyService currencyService = new CurrencyService();

//        currencyService.showResult(25.8, "AUD", "GBP", "2019-04-20", 4);
//        currencyService.showResult(25.8, "USD", "JPY", "2019-04-20", 4);
        currencyService.showResult(1, "EUR", "PLN", "2019-04-20", 3);
        currencyService.showResult(1, "PLN", "EUR", "2019-04-20", 3);

        BigDecimal fromPLNtoEUR = currencyService
                .convertCurrencyValue(1, "PLN", "EUR", "2019-04-20", 4);
        BigDecimal fromEURtoPLN = currencyService
                .convertCurrencyValue(1, "EUR", "PLN", "2019-04-20", 4);

        System.out.println(fromPLNtoEUR.multiply(fromEURtoPLN));
        System.out.println(fromEURtoPLN.divide(fromPLNtoEUR, 4));

    }

}
