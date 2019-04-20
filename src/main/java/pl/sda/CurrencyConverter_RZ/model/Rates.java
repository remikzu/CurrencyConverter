package pl.sda.CurrencyConverter_RZ.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Remigiusz Zudzin
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rates {

    @JsonProperty("BGN")
    private Double BGN;

    @JsonProperty("NZD")
    private Double NZD;

    @JsonProperty("ILS")
    private Double ILS;

    @JsonProperty("RUB")
    private Double RUB;

    @JsonProperty("CAD")
    private Double CAD;

    @JsonProperty("USD")
    private Double USD;

    @JsonProperty("PHP")
    private Double PHP;

    @JsonProperty("CHF")
    private Double CHF;

    @JsonProperty("AUD")
    private Double AUD;

    @JsonProperty("JPY")
    private Double JPY;

    @JsonProperty("TRY")
    private Double TRY;

    @JsonProperty("HKD")
    private Double HKD;

    @JsonProperty("MYR")
    private Double MYR;

    @JsonProperty("HRK")
    private Double HRK;

    @JsonProperty("CZK")
    private Double CZK;

    @JsonProperty("IDR")
    private Double IDR;

    @JsonProperty("DKK")
    private Double DKK;

    @JsonProperty("NOK")
    private Double NOK;

    @JsonProperty("HUF")
    private Double HUF;

    @JsonProperty("GBP")
    private Double GBP;

    @JsonProperty("MXN")
    private Double MXN;

    @JsonProperty("THB")
    private Double THB;

    @JsonProperty("ISK")
    private Double ISK;

    @JsonProperty("ZAR")
    private Double ZAR;

    @JsonProperty("BRL")
    private Double BRL;

    @JsonProperty("SGD")
    private Double SGD;

    @JsonProperty("PLN")
    private Double PLN;

    @JsonProperty("INR")
    private Double INR;

    @JsonProperty("KRW")
    private Double KRW;

    @JsonProperty("RON")
    private Double RON;

    @JsonProperty("CNY")
    private Double CNY;

    @JsonProperty("SEK")
    private Double SEK;

    @JsonProperty("EUR")
    private Double EUR;


}
