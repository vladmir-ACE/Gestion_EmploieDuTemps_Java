package app.test;

import Models.BD;
import Models.M_Classe;
import Models.M_Matiere;
import Models.M_Year;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Year {


    @FXML
    private TextField code;

    @FXML
    private DatePicker debut;

    @FXML
    private DatePicker fin;

    @FXML
    private TableColumn<M_Year, Integer> id;

    @FXML
    private TableColumn<M_Year, String> tabCode;

    @FXML
    private TableColumn<M_Year, String> tabDebut;

    @FXML
    private TableColumn<M_Year, String> tabFin;

    @FXML
    private TableView<M_Year> table;

    public ObservableList<M_Year> data = FXCollections.observableArrayList();

    public void Add(ActionEvent actionEvent) {
        String Code = code.getText();
        LocalDate Debut = debut.getValue();
        LocalDate Fin = fin.getValue();

        if (Debut == null || Fin == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une date de début et une date de fin.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base = new BD();
            Connection connection = base.connect();


            PreparedStatement query = connection.prepareStatement("insert into Years(code,dateDebut,dateFin) VALUES (?, ?, ?)");
            query.setString(1, Code);
            query.setString(2, String.valueOf(Debut));
            query.setString(3, String.valueOf(Fin));


            int status = query.executeUpdate();

            if (status == 1) {
                JOptionPane.showMessageDialog(null, "SUCSES");
                debut.setValue(null);
                fin.setValue(null);
                code.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "FAILED");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void Afficher(ActionEvent actionEvent) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base = new BD();
            Connection connection = base.connect();


            String sql = "SELECT * FROM Years";
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                data.add(new M_Year(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (M_Year year : data) {
            System.out.println(year.getId() + " " + year.getCode() + " " + year.getDateDebut());
        }
        table.setItems(data);
        id.setCellValueFactory(new PropertyValueFactory<M_Year, Integer>("id"));
        tabDebut.setCellValueFactory(new PropertyValueFactory<M_Year, String>("dateDebut"));
        tabCode.setCellValueFactory(new PropertyValueFactory<M_Year, String>("code"));
        tabFin.setCellValueFactory(new PropertyValueFactory<M_Year, String>("dateFin"));

        setValueFromeTable();

    }

    private  Integer ID;

    public void setValueFromeTable(){
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                M_Year Ye=table.getItems().get(table.getSelectionModel().getSelectedIndex());

                code.setText(Ye.getCode());
                debut.setValue(LocalDate.parse(Ye.getDateDebut()));
                fin.setValue(LocalDate.parse(Ye.getDateFin()));

                ID=Ye.getId();

            }
        });

    }

    public void update(ActionEvent actionEvent) {
        String Code = code.getText();
        LocalDate Debut = debut.getValue();
        LocalDate Fin = fin.getValue();



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            BD base= new BD();
            Connection connection=base.connect();


            String sql="update years set code=?, dateDebut=? , dateFin=? where id=?";
            PreparedStatement query = connection.prepareStatement(sql);
            query.setString(1, Code);
            query.setString(2, String.valueOf(Debut));
            query.setString(3, String.valueOf(Fin));

            query.setInt(4,ID);

            int status = query.executeUpdate();

            if (status == 1) {
                JOptionPane.showMessageDialog(null, "SUCSES");
                debut.setValue(null);
                fin.setValue(null);
                code.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "FAILED");
            }

        } catch (ClassNotFoundException ex) {
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
            PreparedStatement query = connection.prepareStatement("DELETE from years  where id=? ");

            query.setInt(1,ID);

            int status = query.executeUpdate();

            if (status == 1) {
                JOptionPane.showMessageDialog(null, "SUCSES");
                debut.setValue(null);
                fin.setValue(null);
                code.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "FAILED");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(null);
        data.clear();

    }






}
