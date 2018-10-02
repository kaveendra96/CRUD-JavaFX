/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author nipun
 */
public class Main extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage=primaryStage;
        FXMLLoader loder=new FXMLLoader(Main.class.getResource("../View/Home.fxml"));
        AnchorPane pane=loder.load();
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    
}
