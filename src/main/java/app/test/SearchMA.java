package app.test;

import Models.BD;
import Models.M_Matiere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchMA {

    @FXML
    private TableColumn<M_Matiere, Integer> id;

    @FXML
    private TableColumn<M_Matiere, String> tabCode;

    @FXML
    private TableColumn<M_Matiere, String> tabIntituler;

    @FXML
    private TableView<M_Matiere> table;

    PreparedStatement query;

    public ObservableList<M_Matiere> data= FXCollections.observableArrayList();
    @FXML
    private TextField word;

    public void Search(ActionEvent actionEvent) {

        String Mot=word.getText();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base= new BD();
            Connection connection=base.connect();


            String sql="SELECT * FROM Matieres where code=? or intituler=?";
            query=connection.prepareStatement(sql);
            query.setString(1,Mot);
            query.setString(2,Mot);
            ResultSet rs=query.executeQuery();

            while (rs.next()){
                data.add(new M_Matiere(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(M_Matiere matiere : data) {
            System.out.println(matiere.getId() + " " +matiere.getIntituler() + " " + matiere.getCode());
        }
        table.setItems(data);
        id.setCellValueFactory(new PropertyValueFactory<M_Matiere,Integer>("id"));
        tabIntituler.setCellValueFactory(new PropertyValueFactory<M_Matiere,String>("intituler"));
        tabCode.setCellValueFactory(new PropertyValueFactory<M_Matiere,String>("code"));
    }


    public void refresh(ActionEvent actionEvent) {
        table.setItems(null);
        data.clear();

    }
}
