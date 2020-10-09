package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.*;
import appointmentScheduler.Model.*;
import appointmentScheduler.Utilities.Alerts;
import appointmentScheduler.Utilities.TimeClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class dashboardController implements Initializable {
    private static final ObservableList<String> allAppointmentTypes = FXCollections.observableArrayList("Training",
            "Presentation","Scrum","Code Review","Meeting");

    @FXML
    private MenuBar menuBarHome;

    @FXML
    private Menu reportsMenuBar;

    @FXML
    private Menu closeMenuBar;

    @FXML
    private DatePicker datePickerAppointments;

    @FXML
    private RadioButton viewAllRadioButton;

    @FXML
    private RadioButton viewWeekRadioButton;

    @FXML
    private RadioButton viewMonthRadioButton;

    @FXML
    private Label varDateLabel;

    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private TableColumn<Appointment, LocalDate> dateAppointmentColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> startTimeAppointmentColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> endTimeAppointmentColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private Button viewAllCustomerButton;

    @FXML
    private Button viewCustomerButton;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button editAppointmentButton;

    @FXML
    private Button deleteAppointmentButton;

    private static int indexOfSelectedObj;

    ToggleGroup filterGroup = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // sets date label with PST
        ZoneId zoneID = ZoneId.of("America/Los_Angeles");
        LocalDateTime todayDateTime = LocalDateTime.now();
        ZonedDateTime zdtToday = todayDateTime.atZone(zoneID);
        LocalDate todayDate;
        todayDate = zdtToday.toLocalDate();
        varDateLabel.setText(String.valueOf(todayDate));

        // sets the toggle groups
        viewAllRadioButton.setToggleGroup(filterGroup);
        viewMonthRadioButton.setToggleGroup(filterGroup);
        viewWeekRadioButton.setToggleGroup(filterGroup);
        // sets default radio button selected to view all appts
        filterGroup.selectToggle(viewAllRadioButton);

        /*
        Below sets appointments table and columns
        */
        // the "date" refers to the getter in Appointments model class, getDate()
        dateAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        // the "startTime" refers to the getter in Appointments model class, getStartTime()
        startTimeAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        // the "endTime" refers to the getter in Appointments model class, getEndTime()
        endTimeAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        // retrieve data from database and sets appointment list
        try {
            // transfers data and sets from SQL server to list
            Appointment.setAllAppointments(AppointmentDaoImpl.getAllAppointments());
            Customer.setAllCustomers(CustomerDaoImpl.getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datePickerAppointments.setValue(LocalDate.now());
        LocalDate selectedDate = datePickerAppointments.getValue();

        ObservableList<Appointment> aList = Appointment.getAllAppointments();
        /*
        Lambda expression compares list of appts start dates to user selected date month, and shows the ones that
        are equal. This helps present filter logic in one section of the code.
        */
//        ObservableList<Appointment> fList = aList.filtered(a -> a.getDate().getMonth().equals(selectedDate.getMonth()));
//        if(viewMonthRadioButton.isSelected()) {
//            appointmentsTableView.setItems(fList);
//        }
        if(viewAllRadioButton.isSelected()) {
            appointmentsTableView.setItems(Appointment.getAllAppointments());
        }

    }

    // list getters and setters
    public static ObservableList<String> getAllAppointmentTypes() {
        return allAppointmentTypes;
    }

    // getter for index to be modified which allows access to other layers of the program
    public static int getIndexOfSelectedObj() {
        return indexOfSelectedObj;
    }

    @FXML
    void closeMenuBarActionHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit application?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    public void viewAllCustomersHandler(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) viewAllCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("allCustomers.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void viewCustomerButtonHandler(ActionEvent actionEvent) throws IOException {
        Appointment viewCustomer = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (viewCustomer != null) {
            indexOfSelectedObj = Appointment.getAllAppointments().indexOf(viewCustomer);

            Stage stage;
            Parent root;
            stage = (Stage) viewCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerInformation.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alerts.selectionError(2);
        }
    }

    @FXML
    void addAppointmentButtonHandler(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) addAppointmentButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addAppointment.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deleteAppointmentButtonHandler(ActionEvent event) throws SQLException {
        Appointment deleteAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (deleteAppointment != null) {
            if (Alerts.confirmationWindow(3)) {
                AppointmentDaoImpl.deleteAppointment(deleteAppointment);
                Appointment.getAllAppointments().remove(deleteAppointment);
                appointmentsTableView.setItems(Appointment.getAllAppointments());
            }
        }
        else {
            Alerts.selectionError(1);
        }
    }

    @FXML
    void editAppointmentButtonHandler(ActionEvent actionEvent) throws IOException {
        Appointment editAppt = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (editAppt != null) {
            indexOfSelectedObj = Appointment.getAllAppointments().indexOf(editAppt);
            Parent root;
            root = FXMLLoader.load(getClass().getResource("editAppointment.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            Alerts.selectionError(3);
        }
    }

    @FXML
    void reportsMenuBarActionHandler(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void datePickerHandler(ActionEvent actionEvent) {
        LocalDate selectedDate = datePickerAppointments.getValue();
        ObservableList<Appointment> aList = Appointment.getAllAppointments();
        if(viewAllRadioButton.isSelected()) appointmentsTableView.setItems(Appointment.getAllAppointments());
        if(viewMonthRadioButton.isSelected()) {
            ObservableList<Appointment> fList = aList.filtered(a -> a.getDate().getMonth().equals(selectedDate.getMonth()));
            appointmentsTableView.setItems(fList);
        }
//        if(viewWeekRadioButton.isSelected()) {
//
//        }
    }

    @FXML
    void viewAllRadioButtonHandler(ActionEvent event) {
        appointmentsTableView.setItems(Appointment.getAllAppointments());
    }

    @FXML
    void viewMonthRadioButtonHandler(ActionEvent event) {
        LocalDate selectedDate = datePickerAppointments.getValue();
        ObservableList<Appointment> aList = Appointment.getAllAppointments();
        /*
        Lambda expression compares list of appts start dates to user selected date month, and shows the ones that
        are equal. This helps present filter logic in one section of the code.
        */
        ObservableList<Appointment> fList = aList.filtered(a -> a.getDate().getMonth().equals(selectedDate.getMonth()));
        appointmentsTableView.setItems(fList);
    }

    @FXML
    void viewWeekRadioButtonHandler(ActionEvent event) {

    }
}
