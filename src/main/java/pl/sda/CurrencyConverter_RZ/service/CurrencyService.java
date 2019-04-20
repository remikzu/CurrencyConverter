package pl.sda.CurrencyConverter_RZ.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.CurrencyConverter_RZ.model.Currencies;
import pl.sda.CurrencyConverter_RZ.model.Rates;

import java.io.IOException;
import java.net.URL;

/**
 * @author Remigiusz Zudzin
 */
@Data
@NoArgsConstructor
public class CurrencyService {

    private final String finalHistoricalURLAddress = "https://api.exchangeratesapi.io/";
    public Currencies getCurrencyValue(String date, String currencyShortcut) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(finalHistoricalURLAddress + date + "?base=" + currencyShortcut);
        return mapper.readValue(url, Currencies.class);
    }



}
