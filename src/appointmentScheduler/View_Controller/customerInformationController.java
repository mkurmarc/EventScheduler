package appointmentScheduler.View_Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class customerInformationController implements Initializable {
    @FXML
    private Label varFullNameLabel;

    @FXML
    private Label varIdLabel;

    @FXML
    private Label varAddress1Label;

    @FXML
    private Label varAddress2Label;

    @FXML
    private Label varActiveLabel;

    @FXML
    private Label varCreateDateLabel;

    @FXML
    private Label varCreatedByLabel;

    @FXML
    private Button backButton;

    @FXML
    void backButtonHandler(ActionEvent actionEvent) throws IOException {
        if (confirmationWindow(2)) {
            Stage stage;
            Parent root;
            stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
