package app.test;

import Models.M_Enseignant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class SearchEN {

    @FXML
    private TableColumn<M_Enseignant, String> tabEmail;

    @FXML
    private TableColumn<M_Enseignant, String> tabMatricule;

    @FXML
    private TableColumn<M_Enseignant, String> tabNom;

    @FXML
    private TableColumn<M_Enseignant, String> tabPrenom;

    @FXML
    private TableColumn<M_Enseignant, String> tabTel;

    @FXML
    private TableColumn<M_Enseignant, Integer> id;

    @FXML
    private TableView<M_Enseignant> table;

    public ObservableList<M_Enseignant> data= FXCollections.observableArrayList();

    @FXML
    private TextField word;





    public void Search(ActionEvent actionEvent) {

       String Mot=word.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaprojet", "root", "");

            String sql="SELECT * FROM Enseignants where nom=? OR prenom=? OR tel=? ";
            PreparedStatement query = connection.prepareStatement(sql);
            query.setString(1,Mot);
            query.setString(2,Mot);
            query.setString(3,Mot);
            ResultSet rs=query.executeQuery();


            while (rs.next()){
                data.add(new M_Enseignant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(M_Enseignant enseignant : data) {
            System.out.println(enseignant.getId() + " " + enseignant.getNom() + " " + enseignant.getPrenom());
        }
        table.setItems(data);
        id.setCellValueFactory(new PropertyValueFactory<M_Enseignant,Integer>("id"));
        tabNom.setCellValueFactory(new PropertyValueFactory<M_Enseignant,String>("nom"));
        tabPrenom.setCellValueFactory(new PropertyValueFactory<M_Enseignant,String>("prenom"));
        tabTel.setCellValueFactory(new PropertyValueFactory<M_Enseignant,String>("tel"));
        tabMatricule.setCellValueFactory(new PropertyValueFactory<M_Enseignant,String>("matricule"));
        tabEmail.setCellValueFactory(new PropertyValueFactory<M_Enseignant,String>("email"));


    }


    public void refresh(ActionEvent actionEvent) {


        table.setItems(null);
        data.clear();


    }
}
