package app.test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminView implements Initializable {

    private Parent fxml;

    @FXML
    private AnchorPane root;

    @FXML
    public void Enseignant(javafx.scene.input.MouseEvent mouseEvent) {

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("enseignant.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Home(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void SE(javafx.scene.input.MouseEvent mouseEvent) {

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search_EN.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    @FXML
    public void SM(javafx.scene.input.MouseEvent mouseEvent) {

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search_MA.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void annee(javafx.scene.input.MouseEvent mouseEvent) {

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("year.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void classe(javafx.scene.input.MouseEvent mouseEvent) {

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Classe.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void cours(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Cours.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void matiere(javafx.scene.input.MouseEvent mouseEvent) {

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("matiere.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void timing(javafx.scene.input.MouseEvent mouseEvent) {

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Timing.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException e){
            e.printStackTrace();
        }

    }



}