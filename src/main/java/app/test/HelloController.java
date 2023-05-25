package app.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button LOGIN;

    //private  Stage stage;

    @FXML
    public void connection(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene=new Scene(root,1400,500);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("admin.css")).toExternalForm());
            stage.setScene(scene);

            stage.show();
            HelloApplication.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}