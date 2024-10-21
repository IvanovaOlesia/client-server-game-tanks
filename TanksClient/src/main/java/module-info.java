module edu.school21.clientTanks {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires com.google.gson;
    requires javafx.graphics;
    requires java.compiler;

    opens edu.school21.clientTanks.JSONModel to com.google.gson;
    exports edu.school21.clientTanks.JSONModel;

    opens edu.school21.clientTanks.app to javafx.fxml;
    exports edu.school21.clientTanks.app ;

    opens edu.school21.clientTanks.view to javafx.fxml;
    exports edu.school21.clientTanks.view;


}