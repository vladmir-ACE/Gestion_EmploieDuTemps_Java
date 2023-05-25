package app.test;

import Models.BD;

import Models.M_Matiere;
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

public class Matiere {
    @FXML
    private TextField code;

    @FXML
    private TableColumn<M_Matiere, Integer> id;

    @FXML
    private TextField intituler;

    @FXML
    private TableColumn<M_Matiere, String> tabCode;

    @FXML
    private TableColumn<M_Matiere, String> tabIntituler;

    @FXML
    private TableView<M_Matiere> table;

    PreparedStatement query;

    public ObservableList<M_Matiere> data= FXCollections.observableArrayList();

    private  Integer ID;



    @FXML
    void Add(ActionEvent event) {

        String Intiu=intituler.getText();
        String Code=code.getText();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base= new BD();
            Connection connection=base.connect();


            query=connection.prepareStatement("insert into Matieres(intituler,code) VALUES (?, ?)");
            query.setString(1,Intiu);
            query.setString(2,Code);


            int status= query.executeUpdate();

            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");
                intituler.setText("");
                code.setText("");}
                else {
                JOptionPane.showMessageDialog(null,"FAILED");
            }

        }catch (ClassNotFoundException ex){
            System.out.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



        @FXML
    void Afficher(ActionEvent event) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                BD base= new BD();
                Connection connection=base.connect();


                String sql="SELECT * FROM Matieres";
                query=connection.prepareStatement(sql);
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

            setValueFromeTable();
    }


    public void setValueFromeTable(){
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                M_Matiere Ma=table.getItems().get(table.getSelectionModel().getSelectedIndex());

                code.setText(Ma.getCode());
                intituler.setText(Ma.getIntituler());

                ID=Ma.getId();

            }
        });

    }

    public void update(ActionEvent actionEvent) {

        String Intiu=intituler.getText();
        String Code=code.getText();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base= new BD();
            Connection connection=base.connect();


            String sql="update Matieres set code=?, intituler=? where id=?";
            query=connection.prepareStatement(sql);
            query.setString(1,Code);
            query.setString(2,Intiu);
            query.setInt(3,ID);

            int status= query.executeUpdate();

            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");
                intituler.setText("");
                code.setText("");}
            else {
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
            query=connection.prepareStatement("DELETE from Matieres  where id=? ");

            query.setInt(1,ID);

            int status= query.executeUpdate();



            if(status==1){
                JOptionPane.showMessageDialog(null,"SUCSES");
                intituler.setText("");
                code.setText("");}
            else {
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
