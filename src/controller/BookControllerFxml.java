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
import dto.BookDto;
import entity.BookEntity;
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

public class BookControllerFxml {

    private BookController bookController;

    public BookControllerFxml() throws Exception {
        bookController = new BookController();
    }

    @FXML
    private TableColumn<BookDto, Integer> colNb;

    @FXML
    private TableColumn<BookDto, String> colAuthor;

    @FXML
    private TableColumn<BookDto, String> colCatID;

    @FXML
    private TableColumn<BookDto, String> colDescription;

    @FXML
    private TableColumn<BookDto, String> colID;

    @FXML
    private TableColumn<BookDto, String> colTitle;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookDto> tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtCatID;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtNumOfBooks;

    @FXML
    private TextField txtTitle;

    public void initialize() throws ClassNotFoundException, SQLException {
        colID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCatID.setCellValueFactory(new PropertyValueFactory<>("catID"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colNb.setCellValueFactory(new PropertyValueFactory<>("nob"));

        getAllBooks();

        tblBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtBookID.setText(newValue.getBookID());
                txtTitle.setText(newValue.getTitle());
                txtDescription.setText(newValue.getDescription());
                txtCatID.setText(newValue.getCatID());
                txtAuthor.setText(newValue.getAuthor());
                txtNumOfBooks.setText(String.valueOf(newValue.getNob()));
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
                BookDto dto = new BookDto(txtBookID.getText(), txtTitle.getText(), txtDescription.getText(),
                        txtCatID.getText(), txtAuthor.getText(), Integer.parseInt(txtNumOfBooks.getText()));
                String response = bookController.save(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllBooks();
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
                String bookID = txtBookID.getText();
                String response = bookController.delete(bookID);

                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllBooks();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at Delete Item");
        }
    }

    @FXML
    void btnShowAllBooksOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        getAllBooks();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (validateInputs()) {
                BookDto dto = new BookDto(txtBookID.getText(), txtTitle.getText(), txtDescription.getText(),
                        txtCatID.getText(), txtAuthor.getText(), Integer.parseInt(txtNumOfBooks.getText()));
                String response = bookController.update(dto);
                JOptionPane.showMessageDialog(null, response);

                clear();
                getAllBooks();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error at update data");
        }
    }

    private void clear() {
        txtBookID.setText("");
        txtTitle.setText("");
        txtDescription.setText("");
        txtCatID.setText("");
        txtAuthor.setText("");
        txtNumOfBooks.setText("");

        tblBooks.getSelectionModel().clearSelection();
    }

    public void getAllBooks() throws ClassNotFoundException, SQLException {
        Connection connection = dbConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM book");
        ResultSet rst = statement.executeQuery();

        ArrayList<BookEntity> bookEntities = new ArrayList<BookEntity>();

        while (rst.next()) {
            BookEntity bookEntity = new BookEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6));

            bookEntities.add(bookEntity);
        }

        ObservableList<BookDto> bookDtos = FXCollections.observableArrayList();

        for (BookEntity book : bookEntities) {
            BookDto bookDto = new BookDto(
                    book.getBookID(),
                    book.getTitle(),
                    book.getDescription(),
                    book.getCatID(),
                    book.getAuthor(),
                    book.getNob());

            bookDtos.add(bookDto);
        }
        tblBooks.setItems(bookDtos);
    }

    private boolean validateInputs() {
        if (txtBookID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book ID cannot be empty");
            return false;
        }
        if (txtTitle.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book Title cannot be empty");
            return false;
        }
        if (txtDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Description cannot be empty");
            return false;
        }
        if (txtCatID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Category ID cannot be empty");
            return false;
        }
        if (txtAuthor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Author Name cannot be empty");
            return false;
        }
        if (txtNumOfBooks.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Number of Books cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateSelection() {
        if (tblBooks.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "No book selected");
            return false;
        }
        return true;
    }

}
