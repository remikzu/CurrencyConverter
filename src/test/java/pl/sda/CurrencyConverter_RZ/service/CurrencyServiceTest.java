package pl.sda.CurrencyConverter_RZ.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author Remigiusz Zudzin
 */
class CurrencyServiceTest {

    private static CurrencyService currencyService = new CurrencyService();

    @Test
    @DisplayName("If conversion is correct by multiplying values")
    void shouldReturnTrueByMultiplying() {
        BigDecimal fromPLNtoEUR = currencyService
                .convertCurrencyValue(1, "PLN", "EUR", "2019-04-20", 4);
        BigDecimal fromEURtoPLN = currencyService
                .convertCurrencyValue(1, "EUR", "PLN", "2019-04-20", 4);
        assertAll(() -> assertThat((fromPLNtoEUR.multiply(fromEURtoPLN)).setScale(1, BigDecimal.ROUND_HALF_DOWN))
                .isEqualTo(BigDecimal.valueOf(1.0)),
                () -> assertThat((fromEURtoPLN.multiply(fromPLNtoEUR)).setScale(1, BigDecimal.ROUND_HALF_DOWN))
                        .isEqualTo(BigDecimal.valueOf(1.0)));
    }

    @ParameterizedTest
    @CsvSource({"BGN", "NZD", "ILS", "RUB", "CAD", "USD", "PHP",
            "CHF", "AUD", "JPY", "TRY", "HKD", "MYR", "HRK",
            "CZK", "IDR", "DKK", "NOK", "HUF", "GBP","MXN",
            "THB", "ISK", "ZAR", "BRL", "SGD", "PLN","INR",
            "KRW", "RON", "CNY", "SEK", "EUR"})
    @DisplayName("Returns true if shortcut is correct")
    void IfCurrencyShortcutIsCorrect(String currencyShortcut) {
        assertThat(currencyService
                .isCurrencyShortcutValid(currencyShortcut))
                .isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({"QWE", "REW", "VFC", "XXX", "ZXC", "VDS", "ZDG"})
    @DisplayName("Returns true if shortcut is not correct")
    void IfCurrencyShortcutIsNotCorrect(String currencyShortcut) {
        assertThat(currencyService
                .isCurrencyShortcutValid(currencyShortcut))
                .isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({"BGN", "NZD", "ILS", "RUB", "CAD", "USD", "PHP",
            "CHF", "AUD", "JPY", "TRY", "HKD", "MYR", "HRK",
            "CZK", "IDR", "DKK", "NOK", "HUF", "GBP","MXN",
            "THB", "ISK", "ZAR", "BRL", "SGD", "PLN","INR",
            "KRW", "RON", "CNY", "SEK", "EUR"})
    @DisplayName("Returns true if shortcut length equals 3")
    void IfCurrencyShortcutLengthIsCorrect(String currencyShortcut) {
        assertThat(currencyService
                .isCurrencyShortcutLengthValid(currencyShortcut))
                .isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({"BGNA", "NZDB", "ILSC", "RUDB", "CAED", "USFD"})
    @DisplayName("Returns true if shortcut length equals 3")
    void IfCurrencyShortcutLengthIsNotCorrect(String currencyShortcut) {
        assertThat(currencyService
                .isCurrencyShortcutLengthValid(currencyShortcut))
                .isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({"BGN", "NZD", "ILS", "RUB", "CAD", "USD", "PHP",
            "CHF", "AUD", "JPY", "TRY", "HKD", "MYR", "HRK",
            "CZK", "IDR", "DKK", "NOK", "HUF", "GBP","MXN",
            "THB", "ISK", "ZAR", "BRL", "SGD", "PLN","INR",
            "KRW", "RON", "CNY", "SEK", "EUR"})
    @DisplayName("Returns true if shortcut length equals 3 and the shortcut is correct")
    void IsCurrencyShortcutCorrect(String currencyShortcut) {
        assertThat(currencyService
                .isCurrencyShortcutCorrect(currencyShortcut))
                .isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({"BGNP", "NZS", "ILLLS", "CRUB", "CCC", "A"})
    @DisplayName("Returns true if shortcut length is not equal to 3, or if the shortcut is not correct")
    void IsCurrencyShortcutNotCorrect(String currencyShortcut) {
        assertThat(currencyService
                .isCurrencyShortcutCorrect(currencyShortcut))
                .isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({"2019-04-20", "2017-01-13"})
    void isDateFormatCorrect(String date) {
        assertThat(currencyService
                .isDateFormatCorrect(date))
                .isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({"20-04-2019", "01-01-1999", "22/2/2012", "2012/2/12"})
    void isDateFormatNotCorrect(String date) {
        assertThat(currencyService
                .isDateFormatCorrect(date))
                .isEqualTo(false);
    }
}