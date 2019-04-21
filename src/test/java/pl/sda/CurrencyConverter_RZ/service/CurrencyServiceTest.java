package pl.sda.CurrencyConverter_RZ.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.sda.CurrencyConverter_RZ.model.Currencies;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author Remigiusz Zudzin
 */
class CurrencyServiceTest {

    public static Currencies currencyValue = new Currencies();
    public static CurrencyService currencyService = new CurrencyService();

    @BeforeAll
    static void setUp() {

    }

    @Test
    @DisplayName("If conversion is correct by multiplying values")
    void shouldReturnTrueByMultiplying() {
        BigDecimal fromPLNtoEUR = currencyService
                .convertCurrencyValue(1, "PLN", "EUR", "2019-04-20", 4);
        BigDecimal fromEURtoPLN = currencyService
                .convertCurrencyValue(1, "EUR", "PLN", "2019-04-20", 4);
        assertAll(() -> assertThat((fromPLNtoEUR.multiply(fromEURtoPLN)).setScale(0, BigDecimal.ROUND_HALF_DOWN))
                .isEqualTo(BigDecimal.valueOf(1)),
                () -> assertThat((fromEURtoPLN.multiply(fromPLNtoEUR)).setScale(0, BigDecimal.ROUND_HALF_DOWN))
                        .isEqualTo(BigDecimal.valueOf(1)));
    }

//    @ParameterizedTest   // <- to check if its needed
//    @CsvSource({"AUD", "JPY"})
    @DisplayName("Tests if shortcut of currency is correct")
    void shouldReturnTrueIfCurrencyShortcutIsCorrect(String currencyShortcut) {
        //TODO
    }
}