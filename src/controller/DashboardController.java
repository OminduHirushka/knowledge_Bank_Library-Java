package controller;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    private AnchorPane root;

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Book.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Books");
    }

    @FXML
    void btnBorrowingsOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Borrowing.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Borrowings");
    }

    @FXML
    void btnCategoriesOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Category.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Categories");
    }

    @FXML
    void btnFinesOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Fine.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Fines");
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Member.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Members");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/LoginForm.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Login");
    }
}
