package appointmentScheduler.Utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Alerts {

    // Method creates error messages based of the code given and highlights error field in red
    public static void errorCustomer(int code, TextField textField) {
        textFieldError(textField);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error adding customer");
        alert.setHeaderText("Cannot add customer");
        switch (code) {
            case 1: {
                alert.setContentText("Field is empty!");
                break;
            }
            case 2: {
                alert.setContentText("First name field is too long!");
                break;
            }
            case 3: {
                alert.setContentText("Last name field is too long!");
                break;
            }
            case 4: {
                alert.setContentText("Address 1 field is too long!");
                break;
            }
            case 5: {
                alert.setContentText("Address 2 field is too long!");
                break;
            }
            case 6: {
                alert.setContentText("City field is too long!");
                break;
            }
            case 7: {
                alert.setContentText("Zip code must be an integer.\n(Example: \"91731\")");
                break;
            }
            case 9: {
                alert.setContentText("Active option must be selected!");
                break;
            }
            default: {
                alert.setContentText("Unknown error!");
                break;
            }
        }
        alert.showAndWait();
    }

    public static void errorAppointment(int code, TextField textField) {
        textFieldError(textField);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error adding appointment");
        alert.setHeaderText("Cannot add appointment");
        switch (code) {
            case 1: {
                alert.setContentText("Field is empty!");
                break;
            }
            /*
            case 2: {
                alert.setContentText("Please select existing customer!");
                break;
            }
            case 3: {
                alert.setContentText("Select an appointment date!");
                break;
            }
            case 4: {
                alert.setContentText("Select appointment type!");
                break;
            }
            case 5: {
                alert.setContentText("Select start time");
                break;
            }
            case 6: {
                alert.setContentText("Select end time");
                break;
            }

             */
            case 7: {
                alert.setContentText("Title too long!");
                break;
            }
            case 8: {
                alert.setContentText("Description is too long!");
                break;
            }
            case 9: {
                alert.setContentText("First name field is too long!");
                break;
            }
            case 10: {
                alert.setContentText("Last name field is too long!");
                break;
            }
            case 11: {
                alert.setContentText("Address 1 field is too long");
                break;
            }
            case 12: {
                alert.setContentText("Address 2 field is too long");
                break;
            }
            case 13: {
                alert.setContentText("City field is too long!");
                break;
            }
            case 14: {
                alert.setContentText("Zip code must be an integer.\n(Example: \"91731\")");
                break;
            }
            default: {
                alert.setContentText("Unknown error!");
                break;
            }
        }
        alert.showAndWait();
    }

    private static void textFieldError(TextField textField) {
        if (textField != null) {
            textField.setStyle("-fx-border-color: red");
        }
    }

}
