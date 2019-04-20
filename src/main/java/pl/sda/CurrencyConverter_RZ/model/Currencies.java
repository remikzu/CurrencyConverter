package pl.sda.CurrencyConverter_RZ.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Remigiusz Zudzin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currencies {

    private String base;
    private Rates rates;
    private String date;

}
