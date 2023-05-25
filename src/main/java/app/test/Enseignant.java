package app.test;

import Models.BD;
import Models.M_Enseignant;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Logger;

public class Enseignant {


    @FXML
    private TextField Tel;

    @FXML
    private TextField email;

    @FXML
    private TextField matricule;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    Connection connection;
    PreparedStatement query;

    private  Integer ID;


    @FXML
    void Add(ActionEvent event) throws ClassNotFoundException {
        String name=nom.getText();
        String firstname=prenom.getText();
        String Matricule=matricule.getText();
        String Mail=email.getText();
        String phone=Tel.getText();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost/javaprojet", "root", "");

             query=connection.prepareStatement("insert into Enseignants(nom,prenom,matricule,email,tel) VALUES (?, ?, ?,?,?)");
              query.setString(1,name);
            query.setString(2,firstname);
            query.setString(3,Matricule);
            query.setString(4,Mail);
            query.setString(5,phone);

            int status= query.executeUpdate();

            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");
                nom.setText("");
                prenom.setText("");
                matricule.setText("");
                Tel.setText("");
                email.setText("");
            }else {
                JOptionPane.showMessageDialog(null,"FAILED");
            }



        }catch (ClassNotFoundException ex){
            System.out.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
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
    void Afficher(ActionEvent event) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javaprojet", "root", "");

            String sql="SELECT * FROM Enseignants";
            query=connection.prepareStatement(sql);
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

        setValueFromeTable();

    }

    public void setValueFromeTable(){
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                M_Enseignant En=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                nom.setText(En.getNom());
                prenom.setText(En.getPrenom());
                Tel.setText(En.getTel());
                email.setText(En.getEmail());
                matricule.setText(En.getMatricule());

                ID=En.getId();

            }
        });

    }

    public void update(ActionEvent actionEvent) {

        String name=nom.getText();
        String firstname=prenom.getText();
        String Matricule=matricule.getText();
        String Mail=email.getText();
        String phone=Tel.getText();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javaprojet", "root", "");

            query=connection.prepareStatement("update Enseignants set nom=?, prenom=?, matricule=?, email=?, tel=? where id=? ");
            query.setString(1,name);
            query.setString(2,firstname);
            query.setString(3,Matricule);
            query.setString(4,Mail);
            query.setString(5,phone);
            query.setInt(6,ID);

            int status= query.executeUpdate();

            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");
                nom.setText("");
                prenom.setText("");
                matricule.setText("");
                Tel.setText("");
                email.setText("");
            }else {
                JOptionPane.showMessageDialog(null,"FAILED");
            }



        }catch (ClassNotFoundException ex){
            System.out.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(null);
        data.clear();


    }

    public void delete(ActionEvent actionEvent) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javaprojet", "root", "");

            query=connection.prepareStatement("DELETE from Enseignants  where id=? ");

            query.setInt(1,ID);

            int status= query.executeUpdate();

            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");
                nom.setText("");
                prenom.setText("");
                matricule.setText("");
                Tel.setText("");
                email.setText("");
            }else {
                JOptionPane.showMessageDialog(null,"FAILED");
            }


        }catch (ClassNotFoundException ex){
            System.out.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(null);
        data.clear();




    }
}
