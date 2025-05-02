module com.example.jobapplicationmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jobapplicationmanagementsystem to javafx.fxml;
    exports com.example.jobapplicationmanagementsystem;
}