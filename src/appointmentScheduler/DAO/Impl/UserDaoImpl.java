package appointmentScheduler.DAO.Impl;

import appointmentScheduler.DAO.UserDAO;
import appointmentScheduler.Model.User;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserDaoImpl implements UserDAO {
    @Override
    public ObservableList<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(int contactID) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
