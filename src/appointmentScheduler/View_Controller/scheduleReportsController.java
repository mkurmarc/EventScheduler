package appointmentScheduler.View_Controller;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class scheduleReportsController implements Initializable {
    @FXML
    private DatePicker scheduleReportDatePicker;

    @FXML
    private Button generateReportButton;

    @FXML
    private ChoiceBox<String> changeReportChoiceBox;

    @FXML
    private ComboBox<User> userCombo;

    @FXML
    private Button closeReportButton;

    @FXML
    private TextArea reportsTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> reportNamesList = FXCollections.observableArrayList("Appointment Types Statistics",
                "User Appointment Schedule", "Customer Total Report");
        userCombo.setItems(User.getUserList());
        changeReportChoiceBox.setItems(reportNamesList);
    }

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
        LocalDate selectedDate = scheduleReportDatePicker.getValue();
        String monthSelected = String.valueOf(selectedDate.getMonth());
        ObservableList<Appointment> aList = Appointment.getAllAppointments();
        /*Lambda expression filters the appointment list and compares each appt object's month to the user selected month,
        and adds results to another list, fList. */
        ObservableList<Appointment> fList = aList.filtered(a -> a.getDate().getMonth().equals(selectedDate.getMonth()));
        // this if statement creates the Appointment Types Statistics Report
        if(changeReportChoiceBox.getValue().equals("Appointment Types Statistics")) {
            int trainingCount = 0;
            int presentationCount = 0;
            int meetingCount = 0;
            int retailCallCount = 0;
            int wholesaleCallCount = 0;
            for(int i=0; i < fList.size(); i++) {
                String appointmentType = fList.get(i).getType();
                if(appointmentType.equals("Training")) trainingCount++;
                if(appointmentType.equals("Presentation")) presentationCount++;
                if(appointmentType.equals("Meeting")) meetingCount++;
                if(appointmentType.equals("Retail Sales Call")) retailCallCount++;
                if(appointmentType.equals("Wholesale Sales Call")) wholesaleCallCount++;
            }
            // displays the types and the counts for each
            reportsTextArea.setText(
                    "REPORT SHOWS THE NUMBER OF APPOINTMENT TYPES FOR THE MONTH OF " + monthSelected + "\n\n" +
                    "Training: " + trainingCount + "\n" +
                    "Presentation: " + presentationCount + "\n" +
                    "Meeting: " + meetingCount + "\n" +
                    "Retail Sales Call: " + retailCallCount + "\n" +
                    "Wholesale Sales Call: " + wholesaleCallCount);
        }
        // this else if statement creates User Appointment Schedule Report
        else if(changeReportChoiceBox.getValue().equals("User Appointment Schedule")) {
            String x = "";
            for(int i=0; i < fList.size(); i++) {
                String title= fList.get(i).getTitle();
                String start = String.valueOf(fList.get(i).getStartTime());
                String end = String.valueOf(fList.get(i).getEndTime());
                x = x + title + "    " + start + " to " + end + "\n";
            }
            reportsTextArea.setText(
                    "REPORT SHOWS SELECTED USER'S APPOINTMENT SCHEDULE FOR THE MONTH OF " + monthSelected + "\n\n" +
                    x);
        }
        // this else if statement creates Customer Location Statistics Report
        else if(changeReportChoiceBox.getValue().equals("Customer Total Report")) {
            int totalCount = Customer.getAllCustomers().size();
            reportsTextArea.setText("REPORT SHOWS TOTAL CUSTOMERS\n\n" +
                    "Total Customers: " + totalCount);
        }
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/