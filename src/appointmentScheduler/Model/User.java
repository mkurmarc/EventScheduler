package appointmentScheduler.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/*
    @AUTHOR
    Marc Rios
    ID:
*/
public class User {
    private static ObservableList<User> userList = FXCollections.observableArrayList();

    private int userId;
    private String userName;
    private String password;
    private byte active;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;

    public User(int userId, String userName, String password, byte active, LocalDateTime createDate, String createdBy,
                LocalDateTime lastUpdate, String lastUpdateBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    // adds new user to the userList if it is not null and it is not in the userList already
    public static void addUser(User newUser) {
        if (newUser != null && !userList.contains(newUser)) {
            userList.add(newUser);
        }
    }

    public static boolean checkLoginCredentials(String userName, String password) {
        boolean found = false;
        for (User user : userList) {
            if (user.getUserName().equals(userName)) { // checks if userName string in the userList
                if (user.getPassword().equals(password)) found = true;
            }
        }
        return found;
    }

    public static ObservableList<User> getUserList() {
        return userList;
    }

    public static void setUserList(ObservableList<User> userList) {
        User.userList = userList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public String toString() {
        return userName;
    }
}
