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
import dto.BorrowingDto;
import entity.BorrowingEntity;
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

public class BorrowingControllerFxml {

    private BorrowingController borrowingController;

    public BorrowingControllerFxml() throws Exception {
        borrowingController = new BorrowingController();
    }

    @FXML
    private TableColumn<BorrowingDto, String> colBookID;

    @FXML
    private TableColumn<BorrowingDto, String> colBrDate;

    @FXML
    private TableColumn<BorrowingDto, String> colDueDate;

    @FXML
    private TableColumn<BorrowingDto, String> colID;

    @FXML
    private TableColumn<BorrowingDto, String> colMemberID;

    @FXML
    private TableColumn<BorrowingDto, String> colRetDate;

    @FXML
    private TableColumn<BorrowingDto, String> colStatus;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BorrowingDto> tblBorrowings;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBorrowDate;

    @FXML
    private TextField txtBorrowingID;

    @FXML
    private TextField txtDueDate;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtStatus;

    public void initialize() throws ClassNotFoundException, SQLException {
        colID.setCellValueFactory(new PropertyValueFactory<>("brID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colMemberID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        colBrDate.setCellValueFactory(new PropertyValueFactory<>("brDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colRetDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        getAllBorrowings();

        tblBorrowings.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtBorrowingID.setText(newValue.getBrID());
                txtBookID.setText(newValue.getBookID());
                txtMemberID.setText(newValue.getMemberID());
                txtBorrowDate.setText(newValue.getBrDate());
                txtDueDate.setText(newValue.getDueDate());
                txtReturnDate.setText(newValue.getReturnDate());
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
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (validateInputs()) {
                BorrowingDto dto = new BorrowingDto(txtBorrowingID.getText(), txtBookID.getText(), txtMemberID.getText(),
                        txtBorrowDate.getText(), txtDueDate.getText(), txtReturnDate.getText(), txtStatus.getText());
                String response = borrowingController.save(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllBorrowings();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at save data");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            if (validateSelection()) {
                String brID = txtBorrowingID.getText();
                String response = borrowingController.delete(brID);

                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllBorrowings();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at Delete Item");
        }
    }

    @FXML
    void btnShowAllBorrowingsOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        getAllBorrowings();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (validateInputs()) {
                BorrowingDto dto = new BorrowingDto(txtBorrowingID.getText(), txtBookID.getText(), txtMemberID.getText(),
                        txtBorrowDate.getText(), txtDueDate.getText(), txtReturnDate.getText(), txtStatus.getText());
                String response = borrowingController.update(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllBorrowings();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at update data");
        }
    }

    private void clear() {
        txtBorrowingID.setText("");
        txtBookID.setText("");
        txtMemberID.setText("");
        txtBorrowDate.setText("");
        txtDueDate.setText("");
        txtReturnDate.setText("");
        txtStatus.setText("");

        tblBorrowings.getSelectionModel().clearSelection();
    }

    public void getAllBorrowings() throws ClassNotFoundException, SQLException {
        Connection connection = dbConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM borrowing");
        ResultSet rst = statement.executeQuery();

        ArrayList<BorrowingEntity> borrowingEntities = new ArrayList<BorrowingEntity>();

        while (rst.next()) {
            BorrowingEntity borrowingEntity = new BorrowingEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7));

            borrowingEntities.add(borrowingEntity);
        }

        ObservableList<BorrowingDto> borrowingDtos = FXCollections.observableArrayList();

        for (BorrowingEntity borrow : borrowingEntities) {
            BorrowingDto borrowingDto = new BorrowingDto(
                borrow.getBrID(),
                borrow.getBookID(),
                borrow.getMemberID(),
                borrow.getBrDate(),
                borrow.getDueDate(),
                borrow.getReturnDate(),
                borrow.getStatus());

            borrowingDtos.add(borrowingDto);
        }
        tblBorrowings.setItems(borrowingDtos);
    }

    private boolean validateInputs() {
        if (txtBorrowingID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Borrowing ID cannot be empty");
            return false;
        }
        if (txtBookID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book ID cannot be empty");
            return false;
        }
        if (txtMemberID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Member ID cannot be empty");
            return false;
        }
        if (txtBorrowDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Burrow Date cannot be empty");
            return false;
        }
        if (txtDueDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Due Date cannot be empty");
            return false;
        }
        if (txtStatus.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Status cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateSelection() {
        if (tblBorrowings.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "No borrowing selected");
            return false;
        }
        return true;
    }

}

