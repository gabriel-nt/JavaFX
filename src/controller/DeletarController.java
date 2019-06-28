/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.VeiculoDao;
import cenas.Deletar;
import cenas.Principal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Banco;
import model.Veiculo;

/**
 *
 * @author Manos
 */
public class DeletarController implements Initializable {
    
    private static Veiculo veiculo;
    
    @FXML
    private Button btnNao;

    @FXML
    private Button btnSim;

    @FXML
    private Label labelDelete;

     @FXML
    private Label lbModelo;

    @FXML
    private Label lbMarca;

    @FXML
    private Label lbPlaca;

    @FXML
    private Label lbPreco;

    @FXML
    private Label lbAno;
    
    @FXML
    private Label lbID;
    
    @FXML
    private ImageView imgFoto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        carregarVeiculo();
        
        btnNao.setOnMouseClicked((MouseEvent e)->{
            abreTelaPrincipal();
            fecha();
        });
        
        btnSim.setOnMouseClicked((MouseEvent e)->{
            Principal p = new Principal();
            fecha();
            try {
                deletarVeiculo();
                p.start(new Stage());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("Veiculo deletado com sucesso");
                a.show();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
    }
    
    public void deletarVeiculo() throws SQLException{
        Banco bd= new Banco();
        bd.realizaConexao();
        VeiculoDao veiculo= new VeiculoDao(bd.getConexao());
        String id= lbID.getText();
        Integer idVeiculo=Integer.parseInt(id);
        Veiculo vei= new Veiculo(idVeiculo);
        //veiculo.setIdVeiculo(idVeiculo);
        System.out.println("Veículo deletado com sucesso");
        veiculo.removeVeiculo(vei);
    }
    
    public void carregarVeiculo(){
        imgFoto.setImage(new Image("file:///"+veiculo.getImagem()));
        lbID.setText(veiculo.getIdVeiculo().toString());
        lbModelo.setText(veiculo.getModelo());
        lbMarca.setText(veiculo.getMarca());
        lbPlaca.setText(veiculo.getPlaca());
        lbPreco.setText(veiculo.getPreco());
        lbAno.setText(veiculo.getAnoLancamento().toString());
    }

    public static Veiculo getVeiculo() {
        return veiculo;
    }

    public static void setVeiculo(Veiculo veiculo) {
        DeletarController.veiculo = veiculo;
    }
    
    public void fecha(){
        Deletar.getStage().close();
    }
    
    public void abreTelaPrincipal(){
        Principal p = new Principal();
        try {
            p.start(new Stage());
            System.out.println("Abriu");
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
