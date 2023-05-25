package app.test;

import Models.BD;
import Models.M_Cours;

import Models.M_Enseignant;
import Models.M_Year;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Cours {


    @FXML
    private ComboBox<String> annee;

    @FXML
    private ComboBox<String> classe;

    @FXML
    private ComboBox<String> enseignant;

    @FXML
    private Button load;

    @FXML
    private ComboBox<String> matiere;


    @FXML
    private TableColumn<M_Cours, String> tabCA;

    @FXML
    private TableColumn<M_Cours, String> tabEn;

    @FXML
    private TableColumn<M_Cours, String> tabMA;

    @FXML
    private TableColumn<M_Cours, String> tabYE;

    @FXML
    private TableColumn<M_Cours,Integer> tabID;

    @FXML
    private TableView<M_Cours> table;

    public ObservableList<M_Cours> data= FXCollections.observableArrayList();



    public void Add(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        String MA= matiere.getValue();
        String EN=enseignant.getValue();
        String CL=classe.getValue();
        String YE=annee.getValue();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base= new BD();
            Connection connection=base.connect();

            PreparedStatement query = connection.prepareStatement("insert into Cours(matiere,enseignant,classe,year) VALUES (?, ?, ?,?)");
            query.setString(1,MA);
            query.setString(2,EN);
            query.setString(3,CL);
            query.setString(4,YE);


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
            matiere.setItems(data);
            rs1.close();

            //for ensignant
            String sql2="SELECT * FROM Enseignants";
            PreparedStatement query2 = connection.prepareStatement(sql2);
            ResultSet rs2=query2.executeQuery();

            ObservableList data2= FXCollections.observableArrayList();

            while (rs2.next()){
                data2.add(new String(rs2.getString(2)));
            }
            enseignant.setItems(data2);
            rs2.close();

            // for classe
            String sql3="SELECT * FROM Classes";
            PreparedStatement query3 = connection.prepareStatement(sql3);
            ResultSet rs3=query3.executeQuery();

            ObservableList data3= FXCollections.observableArrayList();

            while (rs3.next()){
                data3.add(new String(rs3.getString(2)));
            }
            classe.setItems(data3);
            rs3.close();

            //for anneeScolaire
            String sql4="SELECT * FROM Years";
            PreparedStatement query4 = connection.prepareStatement(sql4);
            ResultSet rs4=query4.executeQuery();

            ObservableList data4= FXCollections.observableArrayList();

            while (rs4.next()){
                data4.add(new String(rs4.getString(2)));
            }
            annee.setItems(data4);
            rs4.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Afficher(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base = new BD();
            Connection connection = base.connect();


            String sql = "SELECT * FROM Cours";
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                data.add(new M_Cours(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
            }
            connection.close();

            table.setItems(data);
            tabID.setCellValueFactory(new PropertyValueFactory<M_Cours,Integer>("id"));
            tabMA.setCellValueFactory(new PropertyValueFactory<M_Cours,String>("matiere"));
            tabCA.setCellValueFactory(new PropertyValueFactory<M_Cours,String>("classe"));
            tabEn.setCellValueFactory(new PropertyValueFactory<M_Cours,String>("enseignant"));
            tabYE.setCellValueFactory(new PropertyValueFactory<M_Cours,String>("year"));

            setValueFromeTable();

        }
    catch (SQLException e) {
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
                M_Cours Co=table.getItems().get(table.getSelectionModel().getSelectedIndex());

                matiere.setValue(Co.getMatiere());
                enseignant.setValue(Co.getEnseignant());
                classe.setValue(Co.getClasse());
                annee.setValue(Co.getYear());
                ID=Co.getId();

            }
        });

    }

    public void update(ActionEvent actionEvent) {
        String MA= matiere.getValue();
        String EN=enseignant.getValue();
        String CL=classe.getValue();
        String YE=annee.getValue();

        try{
            BD base= new BD();
            Connection connection=base.connect();

            String sql="update cours set matiere=?, enseignant=? , classe=?,year=? where id=?";
            PreparedStatement query = connection.prepareStatement(sql);
            query.setString(1,MA);
            query.setString(2,EN);
            query.setString(3,CL);
            query.setString(4,YE);

            query.setInt(5,ID);

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
            PreparedStatement query = connection.prepareStatement("DELETE from cours  where id=? ");

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
