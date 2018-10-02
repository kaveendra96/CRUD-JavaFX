/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author nipun
 */
public class HomeController implements Initializable {

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private TableView<ModelTable> tableView;
    @FXML
    private TableColumn<ModelTable,String> colName;
    @FXML
    private TableColumn<ModelTable,String> colAddress;
    @FXML
    private TableColumn<ModelTable,String> colCity;

    ObservableList<ModelTable> oblist=FXCollections.observableArrayList();
    @FXML
    private JFXButton btnView;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    private void viewPerson(MouseEvent event) {
        try {
            showInTable(viewTableInDatabase());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void addPerson(MouseEvent event) {
        try {
            
            if(addDataToDataBase()>0){
                System.out.println("add sucsess");
                showInTable(viewTableInDatabase());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("class not found");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void updatePerson(MouseEvent event) {
        
    }


    private int addDataToDataBase() throws ClassNotFoundException, SQLException {
        String sql="Insert into Person values(?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root","1234");
        PreparedStatement stm=conn.prepareStatement(sql);
        stm.setObject(1,txtName.getText());
        stm.setObject(2, txtAddress.getText());
        stm.setObject(3, txtCity.getText() );
        
        return stm.executeUpdate();
    }
    private void showInTable(ResultSet rst) throws SQLException{
        tableView.getItems().clear();
        while(rst.next()){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
       
        tableView.setItems(oblist);
            oblist.add(new ModelTable(rst.getString("name"), rst.getString("address"), rst.getString("city")));
        }
    
    }
    private ResultSet viewTableInDatabase() throws ClassNotFoundException, SQLException{
        String sql="SELECT * FROM Person";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root","1234");
        PreparedStatement stm=conn.prepareStatement(sql);
        return stm.executeQuery();
    }

 


    
}
