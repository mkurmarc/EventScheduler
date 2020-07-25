package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class monthlyReportAppointments implements Initializable {
    @FXML
    private TableView<?> monthlyApptReportTableView;

    @FXML
    private TableColumn<?, ?> monthApptReportColumn;

    @FXML
    private TableColumn<?, ?> phoneApptsColumn;

    @FXML
    private TableColumn<?, ?> inPersonApptColumn;

    @FXML
    private TableColumn<?, ?> chatApptsColumn;

    @FXML
    private ChoiceBox<?> selectOtherReportChoiceBox;

    @FXML
    private Button goToReportButton;

    @FXML
    private Button closeApptReportButton;

    @FXML
    void closeApptReportButton(ActionEvent event) {

    }

    @FXML
    void goToReportButtonHandler(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
