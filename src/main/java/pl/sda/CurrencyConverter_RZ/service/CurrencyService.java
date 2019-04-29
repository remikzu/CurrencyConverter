package pl.sda.CurrencyConverter_RZ.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.CurrencyConverter_RZ.model.Currencies;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * @author Remigiusz Zudzin
 */
@Data
@NoArgsConstructor
public class CurrencyService {

    private static Currencies currencyValue = new Currencies();

    public static Currencies getCurrencyValue() {
        return currencyValue;
    }

    private final String finalHistoricalURLAddress = "https://api.exchangeratesapi.io/";

    public Currencies readJSON(String date, String currencyShortcut) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(finalHistoricalURLAddress + date + "?base=" + currencyShortcut);
        return mapper.readValue(url, Currencies.class);
    }

    public static BigDecimal convertCurrencyValue(double amount, String fromCurrency, String toCurrency, String date, int scale) {
        if (isCurrencyShortcutCorrect(fromCurrency) && isCurrencyShortcutCorrect(toCurrency)) {
            CurrencyService currencyService = new CurrencyService();
            try {
                currencyValue = currencyService.readJSON(date, fromCurrency);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BigDecimal exchangeRatio = BigDecimal.valueOf(getCurrencyValue(toCurrency));
            BigDecimal value = BigDecimal.valueOf(amount).multiply(exchangeRatio);
            return value.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void showResult(double amount, String fromCurrency, String toCurrency, String date, int scale) {
        BigDecimal value = convertCurrencyValue(amount, fromCurrency, toCurrency, date, scale);
        System.out.println("Konwersja na dzień " + currencyValue.getDate());
        System.out.println(amount + " " + fromCurrency + " to " + value + " " + toCurrency);
    }

    private static Double getCurrencyValue(String currency) {

        switch (currency) {
            case "BGN":
                return currencyValue.getRates().getBGN();

            case "NZD":
                return currencyValue.getRates().getNZD();

            case "ILS":
                return currencyValue.getRates().getILS();

            case "RUB":
                return currencyValue.getRates().getRUB();

            case "CAD":
                return currencyValue.getRates().getCAD();

            case "USD":
                return currencyValue.getRates().getUSD();

            case "PHP":
                return currencyValue.getRates().getPHP();

            case "CHF":
                return currencyValue.getRates().getCHF();

            case "AUD":
                return currencyValue.getRates().getAUD();

            case "JPY":
                return currencyValue.getRates().getJPY();

            case "TRY":
                return currencyValue.getRates().getTRY();

            case "HKD":
                return currencyValue.getRates().getHKD();

            case "MYR":
                return currencyValue.getRates().getMYR();

            case "HRK":
                return currencyValue.getRates().getHRK();

            case "CZK":
                return currencyValue.getRates().getCZK();

            case "IDR":
                return currencyValue.getRates().getIDR();

            case "DKK":
                return currencyValue.getRates().getDKK();

            case "NOK":
                return currencyValue.getRates().getNOK();

            case "HUF":
                return currencyValue.getRates().getHUF();

            case "GBP":
                return currencyValue.getRates().getGBP();

            case "MXN":
                return currencyValue.getRates().getMXN();

            case "THB":
                return currencyValue.getRates().getTHB();

            case "ISK":
                return currencyValue.getRates().getISK();

            case "ZAR":
                return currencyValue.getRates().getZAR();

            case "BRL":
                return currencyValue.getRates().getBRL();

            case "SGD":
                return currencyValue.getRates().getSGD();

            case "PLN":
                return currencyValue.getRates().getPLN();

            case "INR":
                return currencyValue.getRates().getINR();

            case "KRW":
                return currencyValue.getRates().getKRW();

            case "RON":
                return currencyValue.getRates().getRON();

            case "CNY":
                return currencyValue.getRates().getCNY();

            case "SEK":
                return currencyValue.getRates().getSEK();

            case "EUR":
                return currencyValue.getRates().getEUR();

            default:
                return null;
        }
    }

    /**
     * @param currency - shows the shortcut of the currency in argument
     * @return true if the shortcut is correct, otherwise @return false
     */
    static boolean isCurrencyShortcutValid(String currency) {
        return currency.equals("BGN") || currency.equals("NZD") ||
                currency.equals("ILS") || currency.equals("RUB") ||
                currency.equals("CAD") || currency.equals("USD") ||
                currency.equals("PHP") || currency.equals("CHF") ||
                currency.equals("AUD") || currency.equals("JPY") ||
                currency.equals("TRY") || currency.equals("HKD") ||
                currency.equals("MYR") || currency.equals("HRK") ||
                currency.equals("CZK") || currency.equals("IDR") ||
                currency.equals("DKK") || currency.equals("NOK") ||
                currency.equals("HUF") || currency.equals("GBP") ||
                currency.equals("MXN") || currency.equals("THB") ||
                currency.equals("ISK") || currency.equals("ZAR") ||
                currency.equals("BRL") || currency.equals("SGD") ||
                currency.equals("PLN") || currency.equals("INR") ||
                currency.equals("KRW") || currency.equals("RON") ||
                currency.equals("CNY") || currency.equals("SEK") ||
                currency.equals("EUR");
    }

    static boolean isCurrencyShortcutLengthValid(String currency) {
        return currency.length() == 3;
    }

    public static boolean isCurrencyShortcutCorrect(String currency) {
        return isCurrencyShortcutValid(currency) && isCurrencyShortcutLengthValid(currency);
    }

    public static boolean isAmountValuePositive(double amount) {
        return amount > 0;
    }

    public static boolean isAmountValueBetweenMinAndMax(double amount) {
        return amount < Double.MAX_VALUE && amount > Double.MIN_VALUE;
    }

    public static boolean isAmountValueContainingCommaInsteadOfDot(String amount) {
        String value = String.valueOf(amount);
        String[] valueArray = value.split("");

        for (int i = 0; i < valueArray.length; i++) {
            if (valueArray[i].equals(",")) {
                return true;
            }
        }
        return false;
    }

    public static double convertingCommaToDotOfValue(String amount) {
        return Double.valueOf(String.valueOf(amount).replace(",", "."));
    }

    public static boolean isDateFormatCorrect(String date) {
        try {
            LocalDate ld = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            ld = LocalDate.parse(date, formatter);
            String result = ld.format(formatter);
            return result.equals(date);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isNumeric(String amount) {
        try {
            Double.parseDouble(amount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
