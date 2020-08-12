package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class scheduleReportsController implements Initializable {

    @FXML
    private DatePicker scheduleReportDatePicker;

    @FXML
    private Button generateReportButton;

    @FXML
    private ChoiceBox<?> changeReportChoiceBox;

    @FXML
    private Button goToReportButton;

    @FXML
    private Button closeReportButton;

    @FXML
    private TextArea reportsTextArea;

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
