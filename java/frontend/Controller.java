package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {

    // Login
    @FXML
    private Button btnsignin;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    // Dashboard navigation buttons
    @FXML
    private Button btnProducts;

    @FXML
    private Button btnCurrentStock;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnSuppliers;

    @FXML
    private Button btnSales;

    @FXML
    private Button btnPurchase;

    @FXML
    private Button btnusr;

    // Products fields
    @FXML
    private TextField TfProductCode;

    @FXML
    private TextField TfProductName;

    @FXML
    private TextField TfCategoryID;

    @FXML
    private TextField TfSize;

    @FXML
    private TextField TfPrice;

    // Customers table and fields
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> codeColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> contactColumn;

    @FXML
    private TableColumn<?, ?> positionColumn;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField positionField;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    // Method to navigate to Users dashboard
    @FXML
    private void clkusr(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/dashboard-users.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnusr.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Method to navigate to Products dashboard
    @FXML
    private void ClickBtnProducts(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/dashboard-products.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnProducts.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Method to navigate to Current Stock dashboard
    @FXML
    private void ClickbtnCurrentStock(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/dashboard-stock.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnCurrentStock.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Method to navigate to Customers dashboard
    @FXML
    private void ClickbtnCustomers(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/dashboard-customers.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnCustomers.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Method to navigate to Suppliers dashboard
    @FXML
    private void ClickbtnSuppliers(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/dashboard-suppliers.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnSuppliers.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Method to navigate to Sales dashboard
    @FXML
    private void ClickbtnSales(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/dashboard-sales.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnSales.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Method to navigate to Purchase dashboard
    @FXML
    private void clickbtnPurchase(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/dashboard-purchase.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnPurchase.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Method to handle sign-in button action
    @FXML
    public void signInButton(ActionEvent event) {
        dbConnection connectNow = new dbConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT COUNT(1) FROM tblUser WHERE strUserName = '" + username.getText() + "' AND password = '" + password.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    // Successful login, navigate to Users dashboard
                    loadDashboard("/frontend/dashboard-users.fxml");
                } else {
                    // Invalid login, show alert
                    showAlert(Alert.AlertType.ERROR, "Login Error", null, "Invalid username or password. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to handle add button action for adding a new user
    @FXML
    private void btnAddClicked(ActionEvent event) {
        String userID = userIdField.getText();
        String userName = userNameField.getText();
        String contactNo = contactField.getText();
        String position = positionField.getText();

        // Perform database insertion logic here
        dbConnection connectNow = new dbConnection();
        Connection connectDB = connectNow.getConnection();

        String insertUser = "INSERT INTO dbPotteryInventory.tblUser (strUserID, strUserName, intContactNo, strPosition) " +
                "VALUES ('" + userID + "', '" + userName + "', '" + contactNo + "', '" + position + "')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertUser);

            // Optional: Refresh table or UI after adding
            // Example: tableView.getItems().addAll(new User(userID, userName, contactNo, position));

            // Clear fields after successful add
            clearUserFields();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle database errors or display error message
        }
    }

    // Method to handle edit button action for editing an existing user
    @FXML
    private void btnEditClicked(ActionEvent event) {
        String userID = userIdField.getText();
        String userName = userNameField.getText();
        String contactNo = contactField.getText();
        String position = positionField.getText();

        // Perform database update logic here
        dbConnection connectNow = new dbConnection();
        Connection connectDB = connectNow.getConnection();

        String updateUser = "UPDATE dbPotteryInventory.tblUser " +
                "SET strUserName = '" + userName + "', intContactNo = '" + contactNo + "', strPosition = '" + position + "' " +
                "WHERE strUserID = '" + userID + "'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateUser);

            // Optional: Refresh table or UI after editing
            // Example: tableView.getItems().set(selectedIndex, new User(userID, userName, contactNo, position));

            // Clear fields after successful edit
            clearUserFields();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle database errors or display error message
        }
    }

    // Method to handle delete button action for deleting a user
    @FXML
    private void btnDeleteClicked(ActionEvent event) {
        String userID = userIdField.getText();

        // Perform database delete logic here
        dbConnection connectNow = new dbConnection();
        Connection connectDB = connectNow.getConnection();

        String deleteUser = "DELETE FROM dbPotteryInventory.tblUser WHERE strUserID = '" + userID + "'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteUser);

            // Optional: Refresh table or UI after deletion
            // Example: tableView.getItems().remove(selectedIndex);

            // Clear fields after successful delete
            clearUserFields();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle database errors or display error message
        }
    }

    // Utility method to load a new dashboard scene
    private void loadDashboard(String resourcePath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnsignin.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Utility method to show an alert dialog
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    // Utility method to clear user input fields
    private void clearUserFields() {
        userIdField.clear();
        userNameField.clear();
        contactField.clear();
        positionField.clear();
    }

    // Add other methods as needed for your application

}







