package app.test;

import Models.BD;
import Models.M_Timing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Home {

    @FXML
    private TableColumn<M_Timing, String> tabEn;

    @FXML
    private TableColumn<M_Timing, String> tabHeure;

    @FXML
    private TableColumn<M_Timing, String> tabMatiere;

    @FXML
    private TableView<M_Timing> tab;

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

            tab.setItems(data);

            tabMatiere.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("cours"));

            tabEn.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("enseignant"));

            tabHeure.setCellValueFactory(new PropertyValueFactory<M_Timing,String>("Heure"));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}
