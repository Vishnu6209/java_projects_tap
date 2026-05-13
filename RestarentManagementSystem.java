package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";  
    private static final String PASSWORD = "5876"; 

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
 
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");
        TextField rollNumberField = new TextField();
        rollNumberField.setPromptText("Enter Roll Number");
        TextField courseField = new TextField();
        courseField.setPromptText("Enter Course");
        TextField semesterField = new TextField();
        semesterField.setPromptText("Enter Semester");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter Phone");

      
        TextField searchRollField = new TextField();
        searchRollField.setPromptText("Enter Roll Number to Search");

     
        Button insertButton = new Button("Insert");
        Button searchButton = new Button("Search");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button refreshButton = new Button("Refresh Table");

 
        Label statusLabel = new Label();

         
        TableView<Student> tableView = new TableView<>();
        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Student, String> rollCol = new TableColumn<>("Roll Number");
        rollCol.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        TableColumn<Student, String> semesterCol = new TableColumn<>("Semester");
        semesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Student, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableView.getColumns().addAll(nameCol, rollCol, courseCol, semesterCol, emailCol, phoneCol);
        tableView.setPrefHeight(200);

    
        Label nameLabel = new Label("Name:");
        nameLabel.setTextFill(Color.BLACK);
        Label rollLabel = new Label("Roll Number:");
        rollLabel.setTextFill(Color.BLACK);
        Label courseLabel = new Label("Course:");
        courseLabel.setTextFill(Color.BLACK);
        Label semesterLabel = new Label("Semester:");
        semesterLabel.setTextFill(Color.BLACK);
        Label emailLabel = new Label("Email:");
        emailLabel.setTextFill(Color.BLACK);
        Label phoneLabel = new Label("Phone:");
        phoneLabel.setTextFill(Color.BLACK);
        Label searchLabel = new Label("Search by Roll Number:");
        searchLabel.setTextFill(Color.BLACK);
        Label allStudentsLabel = new Label("All Students:");
        allStudentsLabel.setTextFill(Color.BLACK);

         
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(rollLabel, 0, 1);
        grid.add(rollNumberField, 1, 1);
        grid.add(courseLabel, 0, 2);
        grid.add(courseField, 1, 2);
        grid.add(semesterLabel, 0, 3);
        grid.add(semesterField, 1, 3);
        grid.add(emailLabel, 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(phoneLabel, 0, 5);
        grid.add(phoneField, 1, 5);
        grid.add(insertButton, 0, 6);
        grid.add(updateButton, 1, 6);
        grid.add(searchLabel, 0, 7);
        grid.add(searchRollField, 1, 7);
        grid.add(searchButton, 0, 8);
        grid.add(deleteButton, 1, 8);
        grid.add(statusLabel, 0, 9, 2, 1);
        grid.add(allStudentsLabel, 0, 10);
        grid.add(tableView, 0, 11, 2, 1);
        grid.add(refreshButton, 1, 12);

 
        insertButton.setOnAction(e -> {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT INTO students (name, roll_number, course, semester, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nameField.getText());
                pstmt.setString(2, rollNumberField.getText());
                pstmt.setString(3, courseField.getText());
                pstmt.setString(4, semesterField.getText());
                pstmt.setString(5, emailField.getText());
                pstmt.setString(6, phoneField.getText());
                int rowsAffected = pstmt.executeUpdate();
                statusLabel.setText(rowsAffected > 0 ? "Data inserted successfully!" : "Failed to insert data.");
                clearFields(nameField, rollNumberField, courseField, semesterField, emailField, phoneField);
            } catch (SQLException ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

      
        searchButton.setOnAction(e -> {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM students WHERE roll_number = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, searchRollField.getText());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    rollNumberField.setText(rs.getString("roll_number"));
                    courseField.setText(rs.getString("course"));
                    semesterField.setText(rs.getString("semester"));
                    emailField.setText(rs.getString("email"));
                    phoneField.setText(rs.getString("phone"));
                    statusLabel.setText("Student found!");
                } else {
                    statusLabel.setText("Student not found.");
                    clearFields(nameField, rollNumberField, courseField, semesterField, emailField, phoneField);
                }
            } catch (SQLException ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

 
        updateButton.setOnAction(e -> {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE students SET name = ?, course = ?, semester = ?, email = ?, phone = ? WHERE roll_number = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nameField.getText());
                pstmt.setString(2, courseField.getText());
                pstmt.setString(3, semesterField.getText());
                pstmt.setString(4, emailField.getText());
                pstmt.setString(5, phoneField.getText());
                pstmt.setString(6, rollNumberField.getText());
                int rowsAffected = pstmt.executeUpdate();
                statusLabel.setText(rowsAffected > 0 ? "Data updated successfully!" : "Failed to update data.");
            } catch (SQLException ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        // Delete Button Action
        deleteButton.setOnAction(e -> {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "DELETE FROM students WHERE roll_number = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, rollNumberField.getText());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    statusLabel.setText("Student deleted successfully!");
                    clearFields(nameField, rollNumberField, courseField, semesterField, emailField, phoneField);
                } else {
                    statusLabel.setText("Student not found.");
                }
            } catch (SQLException ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

     
        refreshButton.setOnAction(e -> {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                ObservableList<Student> students = FXCollections.observableArrayList();
                String sql = "SELECT * FROM students";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    students.add(new Student(
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("course"),
                        rs.getString("semester"),
                        rs.getString("email"),
                        rs.getString("phone")
                    ));
                }
                tableView.setItems(students);
                statusLabel.setText("Table refreshed!");
            } catch (SQLException ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });
 
        Scene scene = new Scene(grid, 600, 600);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
