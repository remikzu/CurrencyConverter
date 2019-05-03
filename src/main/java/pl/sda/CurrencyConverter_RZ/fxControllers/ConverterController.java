package pl.sda.CurrencyConverter_RZ.fxControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.sda.CurrencyConverter_RZ.service.CurrencyService;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromComboBox.getItems().addAll("AUD", "BGN", "BRL", "CAD", "CHF", "CNY",
                "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "ISK", "JPY", "KRW", "LTL", "LVL", "MXN",
                "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB",
                "SEK", "SGD", "THB", "TRY", "USD", "ZAR");
        fromComboBox.setValue("PLN");
        toComboBox.getItems().addAll("AUD", "BGN", "BRL", "CAD", "CHF", "CNY",
                "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "ISK", "JPY", "KRW", "LTL", "LVL", "MXN",
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

        if (CurrencyService
                .isAmountValueContainingCommaInsteadOfDot((amountTextField.getText()))) {
            amountTextField.setText(String
                                        .valueOf(CurrencyService
                                                    .convertingCommaToDotOfValue(amountTextField.getText())));
        }

        if (!CurrencyService
                .isCurrencyShortcutCorrect(fromComboBox.getValue()) ||
                !CurrencyService
                        .isCurrencyShortcutCorrect(toComboBox.getValue())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong shortcut!");
            alert.setHeaderText("I have no idea how you've done it, but the shortcut is invalid, fix it!");
            alert.showAndWait();
        } else if (!CurrencyService.isNumeric(amountTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong value!");
            alert.setHeaderText("The amount you entered is not a numeric value - fix it!");
            alert.showAndWait();
            amountTextField.setText("100");
        } else if (!CurrencyService.isDateFormatCorrect(dateTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong date!");
            alert.setHeaderText("Wrong date format! It has to be in yyyy-MM-dd format!");
            alert.showAndWait();
            dateTextField.setText(String.valueOf(LocalDate.now()));
        } else if (!CurrencyService.isAmountValuePositive(Double.parseDouble(amountTextField.getText()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong value!");
            alert.setHeaderText("Value should be positive!");
            alert.showAndWait();
            amountTextField.setText("100");
        } else if (!CurrencyService.isAmountValueBetweenMinAndMax(Double.parseDouble(amountTextField.getText()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong value!");
            alert.setHeaderText("Value shouldn't be bigger than 1.7*10^308 nor lower than 4.9^-324");
            alert.showAndWait();
            amountTextField.setText("100");
        } else {
            double correctAmount = CurrencyService.convertingCommaToDotOfValue(amountTextField.getText());
            double amount = Double.parseDouble(amountTextField.getText());
            String fromCurrency = fromComboBox.getValue();
            String toCurrency = toComboBox.getValue();
            String date = dateTextField.getText();
            int scale = 4;
            BigDecimal result = CurrencyService.convertCurrencyValue(correctAmount, fromCurrency, toCurrency, date, scale);
            resultLabel.setText(amount +
                    " " +
                    fromCurrency +
                    " converted to " +
                    toCurrency +
                    " to a date of " +
                    CurrencyService.getCurrencyValue().getDate() +
                    " is " +
                    result.toString() +
                    " " +
                    toCurrency);
        }
    }

    public void setAboutMenuItem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Currency Converter v1.0 by Remigiusz Zudzin");
        alert.setHeaderText("Welcome to the Currency Converter v1.0!" + "\n" +
                "This application converts the value of the chosen currency into the choosen one." + "\n" +
                "You can choose the currency shortcut from the both lists" + "\n" +
                "The amount should be a positive value between 4.9^-324 and 1.7*10^308" + "\n" +
                "Date format is YEAR-MONTH-DAY in this exact format: yyyy-MM-dd" + "\n" +
                "For more information contact me by the email:" + "\n" +
                "remik.zudzin@gmail.com");
        alert.showAndWait();
    }
}
