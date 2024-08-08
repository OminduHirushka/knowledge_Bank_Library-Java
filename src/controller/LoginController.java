package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    Connection dbConnection;
    PreparedStatement statement;
    ResultSet rst;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String uname = txtUserName.getText();
        String pass = txtPassword.getText();

        if (uname.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Username or Password cannot be empty");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedlibrary", "root", "");

                statement = dbConnection.prepareStatement("SELECT * FROM staff WHERE uname = ? and password = ?");

                statement.setString(1, uname);
                statement.setString(2, pass);

                rst = statement.executeQuery();

                if (rst.next()) {
                    JOptionPane.showMessageDialog(null, "Succsess");

                    URL resource = getClass().getResource("/view/Dashboard.fxml");
                    Parent root = FXMLLoader.load(resource);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    stage.setTitle("Dashboard");
                } else {
                    JOptionPane.showMessageDialog(null, "Fail");
                    txtUserName.setText("");
                    txtPassword.setText("");
                    ;
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Main.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("United Library");
    }

}
