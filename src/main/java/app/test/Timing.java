package app.test;

import Models.BD;
import Models.M_Cours;
import Models.M_Timing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Timing {

    @FXML
    private ComboBox<String> Classe;

    @FXML
    private ComboBox<String> Cours;

    @FXML
    private TextField Debut;

    @FXML
    private ComboBox<String> Enseignant;

    @FXML
    private TextField Fin;

    @FXML
    private Button load;


    @FXML
    private TableColumn<M_Timing,Integer> id;


    @FXML
    private TableColumn<M_Timing, String> tabCours;

    @FXML
    private TableColumn<M_Timing, String> tabDebut;

    @FXML
    private TableColumn<M_Timing, String> tabEn;

    @FXML
    private TableColumn<M_Timing, String> tabFin;

    @FXML
    private TableColumn<M_Timing, String> tabSalle;

    @FXML
    private TableView<M_Timing> table;
    public ObservableList<M_Timing> data= FXCollections.observableArrayList();


    public void Afficher(ActionEvent actionEvent) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base = new BD();
            Connection connection = base.connect();


            String sql = "SELECT * FROM EmploieDuTemps";
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                data.add(new M_Timing(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
            }
            connection.close();

            table.setItems(data);
            id.setCellValueFactory(new PropertyValueFactory<M_Timing,Integer>("id"));
            tabCours.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("cours"));
            tabSalle.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("salle"));
            tabEn.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("enseignant"));
            tabDebut.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("heureDebut"));
            tabFin.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("heureFin"));

            setValueFromeTable();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void Loading(ActionEvent actionEvent) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base= new BD();
            Connection connection=base.connect();

            // for matiere combobox
            String sql1="SELECT * FROM Matieres";
            PreparedStatement query = connection.prepareStatement(sql1);
            ResultSet rs1=query.executeQuery();

            ObservableList data= FXCollections.observableArrayList();

            while (rs1.next()){
                data.add(new String(rs1.getString(2)));
            }
            Cours.setItems(data);
            rs1.close();

            //for ensignant
            String sql2="SELECT * FROM Enseignants";
            PreparedStatement query2 = connection.prepareStatement(sql2);
            ResultSet rs2=query2.executeQuery();

            ObservableList data2= FXCollections.observableArrayList();

            while (rs2.next()){
                data2.add(new String(rs2.getString(2)));
            }
            Enseignant.setItems(data2);
            rs2.close();

            // for classe
            String sql3="SELECT * FROM Classes";
            PreparedStatement query3 = connection.prepareStatement(sql3);
            ResultSet rs3=query3.executeQuery();

            ObservableList data3= FXCollections.observableArrayList();

            while (rs3.next()){
                data3.add(new String(rs3.getString(2)));
            }
            Classe.setItems(data3);
            rs3.close();

            connection.close();



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void Add(ActionEvent actionEvent) {

        try {

            String HeureDebut=Debut.getText();
            String HeureFin=Fin.getText();

            String CO=Cours.getValue();
            String EN=Enseignant.getValue();
            String SA=Classe.getValue();

            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base= new BD();
            Connection connection=base.connect();

            PreparedStatement query = connection.prepareStatement("insert into EmploieDuTemps(enseignant,cours,salle,heureDebut,heureFin) VALUES (?, ?, ?,?,?)");
            query.setString(1,EN);
            query.setString(2,CO);
            query.setString(3,SA);
            query.setString(4,HeureDebut);
            query.setString(5,HeureFin);

            int status= query.executeUpdate();
            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");
                Debut.setText("");
                Fin.setText("");
            }else {
                JOptionPane.showMessageDialog(null,"FAILED");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private  Integer ID;

    public void setValueFromeTable(){
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                M_Timing Tm=table.getItems().get(table.getSelectionModel().getSelectedIndex());

                Cours.setValue(Tm.getCours());
                Enseignant.setValue(Tm.getEnseignant());
                Classe.setValue(Tm.getSalle());
                Debut.setText(Tm.getHeureDebut());
                Fin.setText(Tm.getHeureFin());
                ID=Tm.getId();
            }
        });

    }

    public void update(ActionEvent actionEvent) {
        String HeureDebut=Debut.getText();
        String HeureFin=Fin.getText();
        String CO=Cours.getValue();
        String EN=Enseignant.getValue();
        String SA=Classe.getValue();

        try{
            BD base= new BD();
            Connection connection=base.connect();

            String sql="update emploiedutemps set cours=?, enseignant=? ,salle=?, heureDebut=?,heureFin=? where id=?";
            PreparedStatement query = connection.prepareStatement(sql);
            query.setString(1,CO);
            query.setString(2,EN);
            query.setString(3,SA);
            query.setString(4,HeureDebut);
            query.setString(5,HeureFin);

            query.setInt(6,ID);

            int status= query.executeUpdate();

            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");


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

            BD base= new BD();
            Connection connection=base.connect();
            PreparedStatement query = connection.prepareStatement("DELETE from emploiedutemps  where id=? ");

            query.setInt(1,ID);

            int status= query.executeUpdate();

            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");


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
