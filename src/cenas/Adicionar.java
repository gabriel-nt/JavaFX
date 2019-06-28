/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Manos
 */
public class Adicionar extends Application {
    
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Adicionar.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Adicionar novo Veículo");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage(){
        return stage;
    }

    private void setStage(Stage stage) {
       Adicionar.stage= stage;
    }
}
