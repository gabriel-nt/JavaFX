/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenas;

import controller.DeletarController;
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
public class Deletar extends Application {
    
    private static Stage stage;
    
    public Deletar(Veiculo v){
        DeletarController.setVeiculo(v);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Deletar.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Remover Veículo");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage(){
        return stage;
    }

    private void setStage(Stage stage) {
       Deletar.stage= stage;
    }
}
