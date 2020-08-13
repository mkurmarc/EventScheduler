package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

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
    void closeReportButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(2)) {
            Stage stage;
            Parent root;
            stage = (Stage) closeReportButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
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
