package app.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        this.stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 500);
        scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("application.css")).toExternalForm());
        stage.setTitle("TimingManager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}