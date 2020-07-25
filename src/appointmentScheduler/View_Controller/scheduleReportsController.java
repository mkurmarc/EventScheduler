package appointmentScheduler.View_Controller;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class scheduleReportsController implements Initializable {
    @FXML
    private DatePicker scheduleReportDatePicker;

    @FXML
    private Button generateReportButton;

    @FXML
    private TextField scheduleReportTextArea;

    @FXML
    private ChoiceBox<?> otherReportsChoiceBox;

    @FXML
    private Button goToReportButton;

    @FXML
    private Button closeReportButton;

    @FXML
    void closeReportButtonHandler(ActionEvent event) {

    }

    @FXML
    void generateReportButtonHandler(ActionEvent event) {

    }

    @FXML
    void goToReportButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
