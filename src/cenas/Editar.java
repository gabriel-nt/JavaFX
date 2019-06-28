/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenas;

import controller.EditarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Veiculo;

/**
 *
 * @author Manos
 */
public class Editar extends Application {
    
    private static Stage stage;
    
    public Editar(Veiculo v){
        EditarController.setVeiculo(v);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Editar.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Editar Veículo");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage(){
        return stage;
    }

    private void setStage(Stage stage) {
       Editar.stage= stage;
    }
}
