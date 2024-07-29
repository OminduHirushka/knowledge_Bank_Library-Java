package controller;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane root;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Dashboard");
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
