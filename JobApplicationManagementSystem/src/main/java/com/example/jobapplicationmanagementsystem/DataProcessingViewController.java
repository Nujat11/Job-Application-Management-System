package com.example.jobapplicationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import com.example.jobapplicationmanagementsystem.JobApplication;

import java.io.*;

public class DataProcessingViewController {

    @FXML
    private TableView<DummyTableViewClass> outputTableView;
    @FXML
    private TableColumn<DummyTableViewClass, String> toStringTableColumn;
    @FXML
    private ComboBox<Integer> yearOfExpFilterComboBox;
    @FXML
    private ComboBox<String> desigFilterComboBox;

    @FXML
    public void initialize() {
        desigFilterComboBox.getItems().addAll("Executive", "Manager", "Supervisor");
        yearOfExpFilterComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        toStringTableColumn.setCellValueFactory(new PropertyValueFactory<>("toStringVal"));
    }

    @FXML
    public void loadTableViewButtonOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open JobApplication.bin");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null || !selectedFile.getName().equals("JobApplication.bin")) {
            showError("Please select the 'JobApplication.bin' file.");
            return;
        }

        if (desigFilterComboBox.getValue() == null || yearOfExpFilterComboBox.getValue() == null) {
            showError("Select both designation and minimum year of experience.");
            return;
        }

        outputTableView.getItems().clear();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
            while (true) {
                JobApplication job = (JobApplication) ois.readObject();
                if (job.getDesignationAppliedFor().equals(desigFilterComboBox.getValue()) &&
                        job.getYearOfExperience() >= yearOfExpFilterComboBox.getValue()) {
                    outputTableView.getItems().add(new DummyTableViewClass(job.toString()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
