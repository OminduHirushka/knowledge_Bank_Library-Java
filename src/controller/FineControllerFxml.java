package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.dbConnection;
import dto.FineDto;
import entity.FineEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FineControllerFxml {

    private FineController fineController;

    public FineControllerFxml() throws Exception {
        fineController = new FineController();
    }

    @FXML
    private TableColumn<FineDto, String> colBrID;

    @FXML
    private TableColumn<FineDto, Double> colFine;

    @FXML
    private TableColumn<FineDto, String> colID;

    @FXML
    private TableColumn<FineDto, String> colIssuedDate;

    @FXML
    private TableColumn<FineDto, String> colPaidDate;

    @FXML
    private TableColumn<FineDto, String> colStatus;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<FineDto> tblFines;

    @FXML
    private TextField txtBorrowingID;

    @FXML
    private TextField txtFineAmount;

    @FXML
    private TextField txtFineID;

    @FXML
    private TextField txtIssuedDate;

    @FXML
    private TextField txtPaidDate;

    @FXML
    private TextField txtStatus;

    public void initialize() throws ClassNotFoundException, SQLException {
        colID.setCellValueFactory(new PropertyValueFactory<>("fineID"));
        colBrID.setCellValueFactory(new PropertyValueFactory<>("brID"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colIssuedDate.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        colPaidDate.setCellValueFactory(new PropertyValueFactory<>("paidDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        getAllFines();

        tblFines.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtFineID.setText(newValue.getFineID());
                txtBorrowingID.setText(newValue.getBrID());
                txtFineAmount.setText(String.valueOf(newValue.getAmount()));
                txtIssuedDate.setText(newValue.getIssuedDate());
                txtPaidDate.setText(newValue.getPaidDate());
                txtStatus.setText(newValue.getStatus());
            }
        });
    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        Parent root = FXMLLoader.load(resource);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Dashboard");
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (validateInputs()) {
                FineDto dto = new FineDto(txtFineID.getText(), txtBorrowingID.getText(), Double.parseDouble(txtFineAmount.getText()),
                        txtIssuedDate.getText(), txtPaidDate.getText(), txtStatus.getText());
                String response = fineController.save(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllFines();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at save data");
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            if (validateSelection()) {
                String fineID = txtFineID.getText();
                String response = fineController.delete(fineID);

                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllFines();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at Delete Item");
        }
    }

    @FXML
    void btnShowAllFinesOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        getAllFines();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (validateInputs()) {
                FineDto dto = new FineDto(txtFineID.getText(), txtBorrowingID.getText(), Double.parseDouble(txtFineAmount.getText()),
                        txtIssuedDate.getText(), txtPaidDate.getText(), txtStatus.getText());
                String response = fineController.update(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllFines();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at update data");
        }
    }

    private void clear() {
        txtFineID.setText("");
        txtBorrowingID.setText("");
        txtFineAmount.setText("");
        txtIssuedDate.setText("");
        txtPaidDate.setText("");
        txtStatus.setText("");

        tblFines.getSelectionModel().clearSelection();
    }

    public void getAllFines() throws ClassNotFoundException, SQLException {
        Connection connection = dbConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM fine");
        ResultSet rst = statement.executeQuery();

        ArrayList<FineEntity> fineEntities = new ArrayList<FineEntity>();

        while (rst.next()) {
            FineEntity fineEntity = new FineEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));

            fineEntities.add(fineEntity);
        }

        ObservableList<FineDto> fineDtos = FXCollections.observableArrayList();

        for (FineEntity fine : fineEntities) {
            FineDto fineDto = new FineDto(
                fine.getFineID(),
                fine.getBrID(),
                fine.getAmount(),
                fine.getIssuedDate(),
                fine.getPaidDate(),
                fine.getStatus());

            fineDtos.add(fineDto);
        }
        tblFines.setItems(fineDtos);
    }

    private boolean validateInputs() {
        if (txtFineID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fine ID cannot be empty");
            return false;
        }
        if (txtBorrowingID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Borrowing ID cannot be empty");
            return false;
        }
        if (txtFineAmount.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fine Amount cannot be empty");
            return false;
        }
        if (txtIssuedDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Issued Date cannot be empty");
            return false;
        }
        if (txtStatus.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Status cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateSelection() {
        if (tblFines.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "No fine selected");
            return false;
        }
        return true;
    }

}
