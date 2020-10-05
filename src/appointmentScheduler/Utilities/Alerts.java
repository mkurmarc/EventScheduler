package appointmentScheduler.Utilities;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

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

    public static void loginError(int code, TextField textField, PasswordField passwordField) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Cannot login");
        switch (code) {
            case 1: {
                alert.setContentText("Login name is empty!");
                textFieldError(textField);
                break;
            }
            case 2: {
                alert.setContentText("Password is empty!");
                passwordFieldError(passwordField);
                break;
            }
            case 3: {
                alert.setContentText("Login name is too long!");
                textFieldError(textField);
                break;
            }
            case 4: {
                alert.setContentText("Password is too long!");
                passwordFieldError(passwordField);
                break;
            }
            case 5: {
                alert.setContentText("Incorrect login credentials!");
                break;
            }
            default: {
                alert.setContentText("Unknown error!");
                textFieldError(textField);
                passwordFieldError(passwordField);
                break;
            }
        }
        alert.showAndWait();
    }


    public static void passwordFieldError(PasswordField passwordField) {
        if (passwordField != null) {
            passwordField.setStyle("-fx-border-color: red");
        }
    }


    private static void textFieldError(TextField textField) {
        if (textField != null) {
            textField.setStyle("-fx-border-color: red");
        }
    }

    public static boolean confirmationWindow(int code) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // code 1 means cancel confirmation message
        if (code == 1) {
            alert.setTitle("Cancel");
            alert.setHeaderText("Are you sure you want to cancel?");
            alert.setContentText("Click ok to confirm");
            Optional<ButtonType> result = alert.showAndWait();
            return result.get() == ButtonType.OK;
        }
        // code 2 means go back to main screen confirmation message
        if (code == 2) {
            alert.setTitle("Go Back");
            alert.setHeaderText("Are you sure you want to go back to the home screen?");
            alert.setContentText("Click ok to confirm");
            Optional<ButtonType> result = alert.showAndWait();
            return result.isPresent() && result.get() == ButtonType.OK;
        }
        if (code == 3) {
            alert.setTitle("Delete Appointment");
            alert.setHeaderText("Are you sure you want to delete this appointment?");
            alert.setContentText("Click ok to confirm");
            Optional<ButtonType> result = alert.showAndWait();
            return result.get() == ButtonType.OK;
        }
        if (code == 4) {
            alert.setTitle("Delete");
            alert.setHeaderText("Are you sure you want to delete this customer?");
            alert.setContentText("Click ok to confirm");
            Optional<ButtonType> result = alert.showAndWait();
            return result.get() == ButtonType.OK;
        }
        return false;
    }

    public static void selectionError(int code) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        // code 1 means appointment not selected for deletion
        if (code == 1) {
            alert.setTitle("Delete error");
            alert.setHeaderText("Cannot delete appointment");
            alert.setContentText("Please select appointment from table before trying to delete.");
            alert.showAndWait();
        }
        if (code == 2) {
            alert.setTitle("View error");
            alert.setHeaderText("Cannot view customer details");
            alert.setContentText("Please select appointment from table before trying to view.");
            alert.showAndWait();
        }
        if (code == 3) {
            alert.setTitle("Selection error");
            alert.setHeaderText("Cannot edit appointment details");
            alert.setContentText("Please select appointment from table before trying to view.");
            alert.showAndWait();
        }
        if (code == 4) {
            alert.setTitle("Selection error");
            alert.setHeaderText("Cannot edit customer details");
            alert.setContentText("Please select appointment from table before trying to edit customer details.");
            alert.showAndWait();
        }

    }

}
