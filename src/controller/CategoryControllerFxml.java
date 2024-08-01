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
import dto.CategoryDto;
import entity.CategoryEntity;
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

public class CategoryControllerFxml {

    private CategoryContoller categoryContoller;

    public CategoryControllerFxml() throws Exception {
        categoryContoller = new CategoryContoller();
    }

    @FXML
    private TableColumn<CategoryDto, String> colID;

    @FXML
    private TableColumn<CategoryDto, String> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CategoryDto> tblCategories;

    @FXML
    private TextField txtCatID;

    @FXML
    private TextField txtCatName;

    public void initialize() throws ClassNotFoundException, SQLException {
        colID.setCellValueFactory(new PropertyValueFactory<>("catID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("catName"));

        getAllCategories();

        tblCategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtCatID.setText(newValue.getCatID());
                txtCatName.setText(newValue.getCatName());
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
                CategoryDto dto = new CategoryDto(txtCatID.getText(), txtCatName.getText());
                String response = categoryContoller.save(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllCategories();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at save data");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (validateInputs()) {
                CategoryDto dto = new CategoryDto(txtCatID.getText(), txtCatName.getText());
                String response = categoryContoller.update(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllCategories();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at update data");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            if (validateSelection()) {
                String catID = txtCatID.getText();
                String response = categoryContoller.delete(catID);

                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllCategories();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at Delete Item");
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnShowAllCategoriesOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        getAllCategories();
    }

    private void clear() {
        txtCatID.setText("");
        txtCatName.setText("");
        tblCategories.getSelectionModel().clearSelection();
    }
    
    public void getAllCategories() throws ClassNotFoundException, SQLException {
        Connection connection = dbConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM category");
        ResultSet rst = statement.executeQuery();

        ArrayList<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();

        while (rst.next()) {
            CategoryEntity categoryEntity = new CategoryEntity(
                    rst.getString(1),
                    rst.getString(2));

            System.out.println(categoryEntity);
            categoryEntities.add(categoryEntity);
        }

        ObservableList<CategoryDto> categoryDtos = FXCollections.observableArrayList();

        for (CategoryEntity category : categoryEntities) {
            CategoryDto categoryDto = new CategoryDto(
                    category.getCatID(),
                    category.getCatName());

            categoryDtos.add(categoryDto);
        }
        tblCategories.setItems(categoryDtos);
    }

    private boolean validateInputs() {
        if (txtCatID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Category ID cannot be empty");
            return false;
        }
        if (txtCatName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Category Name cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateSelection() {
        if (tblCategories.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "No category selected");
            return false;
        }
        return true;
    }
}
