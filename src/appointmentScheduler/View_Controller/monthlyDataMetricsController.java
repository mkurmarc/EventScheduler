package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class monthlyDataMetricsController {
    @FXML
    private ChoiceBox<?> dataMetricsReportChoiceBox;

    @FXML
    private Button generateDataButton;

    @FXML
    private TreeTableView<?> employeeMetricsTableView;

    @FXML
    private TreeTableColumn<?, ?> mostApptsColumn;

    @FXML
    private TreeTableColumn<?, ?> weeklyAverageApptColumn;

    @FXML
    private TreeTableColumn<?, ?> mostCallApptsColumn;

    @FXML
    private TreeTableColumn<?, ?> mostInPersonApptsColumn;

    @FXML
    private ChoiceBox<?> otherReportsChoiceBox;

    @FXML
    private Button goToReportButton;

    @FXML
    private Button cancelMetricsButton;

    @FXML
    void cancelMetricsButtonHandler(ActionEvent event) {

    }

    @FXML
    void generateDataButtonHandler(ActionEvent event) {

    }

    @FXML
    void goToReportButtonHandler(ActionEvent event) {

    }


}
