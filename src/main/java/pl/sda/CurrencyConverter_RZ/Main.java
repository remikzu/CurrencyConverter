package pl.sda.CurrencyConverter_RZ;

import pl.sda.CurrencyConverter_RZ.service.CurrencyService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Remigiusz Zudzin
 */
public class Main {

    public static void main(String[] args) throws ParseException {

//        CurrencyService currencyService = new CurrencyService();
//
////        currencyService.showResult(25.8, "AUD", "GBP", "2019-04-20", 4);
////        currencyService.showResult(25.8, "USD", "JPY", "2019-04-20", 4);
//        currencyService.showResult(1, "EUR", "PLN", "2019-04-20", 3);
//        currencyService.showResult(1, "PLN", "EUR", "2019-04-20", 3);
//
//        BigDecimal fromPLNtoEUR = currencyService
//                .convertCurrencyValue(1, "PLN", "EUR", "2019-04-20", 4);
//        BigDecimal fromEURtoPLN = currencyService
//                .convertCurrencyValue(1, "EUR", "PLN", "2019-04-20", 4);
//
//        System.out.println(fromPLNtoEUR.multiply(fromEURtoPLN));
//        System.out.println(fromEURtoPLN.divide(fromPLNtoEUR, 4));

        String date_s = "2011-01-18 00:00:00.0";

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(date_s);
        System.out.println(date);

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(date));

    }

}
