package frontend;

import javafx.beans.property.*;

public class User {
    private final StringProperty userId;
    private final StringProperty userName;
    private final IntegerProperty contactNo;
    private final StringProperty position;

    public User(String userId, String userName, int contactNo, String position) {
        this.userId = new SimpleStringProperty(userId);
        this.userName = new SimpleStringProperty(userName);
        this.contactNo = new SimpleIntegerProperty(contactNo);
        this.position = new SimpleStringProperty(position);
    }

    public String getUserId() {
        return userId.get();
    }

    public StringProperty userIdProperty() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public int getContactNo() {
        return contactNo.get();
    }

    public IntegerProperty contactNoProperty() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo.set(contactNo);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getUserID() {
        return userId.get();
    }
}




