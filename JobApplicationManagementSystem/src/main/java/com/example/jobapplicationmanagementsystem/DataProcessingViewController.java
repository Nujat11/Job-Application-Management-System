package com.example.jobapplicationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

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

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(selectedFile);
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    JobApplication job = (JobApplication) ois.readObject();
                    if (job.getDesignationAppliedFor().equals(desigFilterComboBox.getValue()) &&
                            job.getYearOfExperience() >= yearOfExpFilterComboBox.getValue()) {
                        outputTableView.getItems().add(new DummyTableViewClass(job.toString()));
                    }
                } catch (EOFException eof) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            showError("Error loading data: " + e.getMessage());

        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                showError("Error closing file: " + e.getMessage());
            }
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
