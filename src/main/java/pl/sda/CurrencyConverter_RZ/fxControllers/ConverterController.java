package pl.sda.CurrencyConverter_RZ.fxControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.sda.CurrencyConverter_RZ.model.Rates;
import pl.sda.CurrencyConverter_RZ.service.CurrencyService;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Remigiusz Zudzin
 */
public class ConverterController implements Initializable {

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private Label enterAmountLabel;

    @FXML
    private ComboBox<String> fromComboBox;

    @FXML
    private ComboBox<String> toComboBox;

    @FXML
    private TextField dateTextField;

    @FXML
    private TextField amountTextField;

    @FXML
    private Button convertButton;

    @FXML
    private Button closeButton;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    private CurrencyService currencyService;

    public CurrencyService getCurrencyService() {
        return currencyService;
    }

    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromComboBox.getItems().addAll("AUD", "BGN", "BRL", "CAD", "CHF", "CNY",
                "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "ISK", "JPY", "KRW", "MXN",
                "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB",
                "SEK", "SGD", "THB", "TRY", "USD", "ZAR");
        fromComboBox.setValue("PLN");
        toComboBox.getItems().addAll("AUD", "BGN", "BRL", "CAD", "CHF", "CNY",
                "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "ISK", "JPY", "KRW", "MXN",
                "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB",
                "SEK", "SGD", "THB", "TRY", "USD", "ZAR");
        toComboBox.setValue("EUR");
        dateTextField.setText(LocalDate.now().toString());
        amountTextField.setText("100");
    }

    public void setCloseButton() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setCloseMenuItem() {
        setCloseButton();
    }

    public void setConvertButton() {

        double amount = Double.parseDouble(amountTextField.getText());
        String fromCurrency = fromComboBox.getValue();
        String toCurrency = toComboBox.getValue();
        String date = dateTextField.getText();
        int scale = 4;
        BigDecimal result = CurrencyService.convertCurrencyValue(amount, fromCurrency, toCurrency, date, scale);
        resultLabel.setText(amount +
                " " +
                fromCurrency +
                " converted to " +
                toCurrency +
                " to a date of " +
                date +
                " is " +
                result.toString() +
                " " +
                toCurrency);
    }

}
