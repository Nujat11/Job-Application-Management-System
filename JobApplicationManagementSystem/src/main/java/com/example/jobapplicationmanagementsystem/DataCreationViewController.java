package com.example.jobapplicationmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataCreationViewController {

    @FXML
    private CheckBox isGraduateCheckBox;
    @FXML
    private TextField expertiseTextField;
    @FXML
    private ComboBox<Integer> yearOfExpComboBox;
    @FXML
    private DatePicker dateOfApplnDatePicker;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField jobApplnNoTextField;
    @FXML
    private ComboBox<String> desigComboBox;

    private ArrayList<String> expertiseList;

    @FXML
    public void initialize() {
        expertiseList = new ArrayList<>();
        desigComboBox.getItems().addAll("Executive", "Manager", "Supervisor");
        yearOfExpComboBox.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7);
    }

    @FXML
    public void addExpertiseButtonOnAction(ActionEvent actionEvent) {
        String expertise = expertiseTextField.getText();
        if (!expertise.isEmpty()) {
            expertiseList.add(expertise);
            expertiseTextField.clear();
        } else {
            showErrorAlert("Please enter a valid expertise.");
        }
    }

    @FXML
    public void goToNextSceneOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("DataProcessingAndFilterOutputView.fxml"));
            Scene nextScene = new Scene(loader.load());
            Stage nextStage = new Stage();
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            showErrorAlert("Error loading next scene.");
        }
    }

    @FXML
    public void validateAndAppendButtonOnAction(ActionEvent actionEvent) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            if (jobApplnNoTextField.getText().isEmpty() || nameTextField.getText().isEmpty()
                    || desigComboBox.getValue() == null || dateOfApplnDatePicker.getValue() == null
                    || yearOfExpComboBox.getValue() == null) {
                showErrorAlert("Please fill in all required fields.");
            } else {
                int appNo;
                try {
                    appNo = Integer.parseInt(jobApplnNoTextField.getText());
                } catch (NumberFormatException e) {
                    showErrorAlert("Application number must be a valid integer.");
                    return;
                }

                LocalDate selectedDate = dateOfApplnDatePicker.getValue();
                boolean isFreshGrad = isGraduateCheckBox.isSelected();
                int yearOfExp = yearOfExpComboBox.getValue();

                if (selectedDate.isAfter(LocalDate.now())) {
                    showErrorAlert("Date of application cannot be in the future.");
                } else {
                    if (isFreshGrad) {
                        if (yearOfExp != 0) {
                            showErrorAlert("Fresh graduates must have 0 years of experience.");
                        } else if (expertiseList.isEmpty()) {
                            showErrorAlert("Fresh graduates must provide at least one expertise.");
                        } else {
                            File file = new File("JobApplication.bin");
                            fos = new FileOutputStream(file, true);
                            if (file.exists() && file.length() > 0) {
                                oos = new AppendableObjectOutputStream(fos);
                            } else {
                                oos = new ObjectOutputStream(fos);
                            }

                            JobApplication job = new JobApplication(
                                    appNo,
                                    nameTextField.getText(),
                                    desigComboBox.getValue(),
                                    selectedDate,
                                    true,
                                    yearOfExp,
                                    expertiseList
                            );

                            oos.writeObject(job);
                            showInfoAlert("Application added successfully!");
                        }
                    } else {
                        File file = new File("JobApplication.bin");
                        fos = new FileOutputStream(file, true);
                        if (file.exists() && file.length() > 0) {
                            oos = new AppendableObjectOutputStream(fos);
                        } else {
                            oos = new ObjectOutputStream(fos);
                        }

                        JobApplication job = new JobApplication(
                                appNo,
                                nameTextField.getText(),
                                desigComboBox.getValue(),
                                selectedDate,
                                false,
                                yearOfExp,
                                expertiseList
                        );

                        oos.writeObject(job);
                        showInfoAlert("Application added successfully!");
                    }
                }
            }

        } catch (Exception e) {
            showErrorAlert("Error saving application: " + e.getMessage());
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (Exception ignored) {
            }
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
