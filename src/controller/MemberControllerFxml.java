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
import dto.MemberDto;
import entity.MemberEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MemberControllerFxml {

    private MemberController memberController;

    public MemberControllerFxml() throws Exception {
        memberController = new MemberController();
    }
    
    @FXML
    private TableColumn<MemberDto, String> colAddress;

    @FXML
    private TableColumn<MemberDto, String> colID;

    @FXML
    private TableColumn<MemberDto, String> colMobile;

    @FXML
    private TableColumn<MemberDto, String> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<MemberDto> tblMembers;

    @FXML
    private TextArea txtMemberAddress;

    @FXML
    private TextField txtMemberContact;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtMemberName;

    public void initialize() throws ClassNotFoundException, SQLException {
        colID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        getAllMembers();

        tblMembers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtMemberID.setText(newValue.getMemberID());
                txtMemberName.setText(newValue.getName());
                txtMemberAddress.setText(newValue.getAddress());
                txtMemberContact.setText(newValue.getMobile());
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
                MemberDto dto = new MemberDto(txtMemberID.getText(), txtMemberName.getText(), txtMemberAddress.getText(),
                        txtMemberContact.getText());
                String response = memberController.save(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllMembers();
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
                String memberID = txtMemberID.getText();
                String response = memberController.delete(memberID);

                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllMembers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at Delete Item");
        }
    }

    @FXML
    void btnShowAllMembersOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        getAllMembers();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (validateInputs()) {
                MemberDto dto = new MemberDto(txtMemberID.getText(), txtMemberName.getText(), txtMemberAddress.getText(),
                        txtMemberContact.getText());
                String response = memberController.update(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllMembers();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at update data");
        }
    }

    private void clear() {
        txtMemberID.setText("");
        txtMemberName.setText("");
        txtMemberAddress.setText("");
        txtMemberContact.setText("");

        tblMembers.getSelectionModel().clearSelection();
    }

    public void getAllMembers() throws ClassNotFoundException, SQLException {
        Connection connection = dbConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM member");
        ResultSet rst = statement.executeQuery();

        ArrayList<MemberEntity> memberEntities = new ArrayList<MemberEntity>();

        while (rst.next()) {
            MemberEntity memberEntity = new MemberEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));

            memberEntities.add(memberEntity);
        }

        ObservableList<MemberDto> memberDtos = FXCollections.observableArrayList();

        for (MemberEntity member : memberEntities) {
            MemberDto memberDto = new MemberDto(
                member.getMemberID(),
                member.getName(),
                member.getAddress(),
                member.getMobile());

            memberDtos.add(memberDto);
        }
        tblMembers.setItems(memberDtos);
    }

    private boolean validateInputs() {
        if (txtMemberID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Member ID cannot be empty");
            return false;
        }
        if (txtMemberName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Member Name cannot be empty");
            return false;
        }
        if (txtMemberAddress.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Address cannot be empty");
            return false;
        }
        if (txtMemberContact.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mobile Number cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateSelection() {
        if (tblMembers.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "No Member selected");
            return false;
        }
        return true;
    }
    
}
