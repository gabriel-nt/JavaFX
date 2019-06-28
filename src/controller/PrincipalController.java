package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import cenas.Adicionar;
import cenas.Deletar;
import cenas.Editar;
import cenas.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Banco;
import model.Veiculo;
import DAO.VeiculoDao;

public class PrincipalController implements Initializable {
    
    private Veiculo selecionado;
    
    @FXML
    private Label label;
 
    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Veiculo> tbTabela;

    @FXML
    private TableColumn<Veiculo, Integer> clID;

    @FXML
    private TableColumn<Veiculo, String> clModelo;

    @FXML
    private TableColumn<Veiculo, String> clMarca;

    @FXML
    private TableColumn<Veiculo, String> clPreco;

    @FXML
    private TableColumn<Veiculo,Integer > clAno;
    
    @FXML
    private TableColumn<Veiculo, String> clPlaca;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;
    
    @FXML
    private CheckBox checkBtn;
    
    @FXML
    private ImageView imgFoto;

    @FXML
    private Label lbID;

    @FXML
    private Label lbModelo;

    @FXML
    private Label lbPlaca;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        mostraDetalhes();
        tbTabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               selecionado = (Veiculo) newValue;
               mostraDetalhes();
            }  
        });
        
        tabela();
        Banco bd= new Banco();
        bd.realizaConexao();
        System.out.println(bd.getMensagemErro());
        System.out.println(bd.getStatusConexao());
        btnAdd.setOnMouseClicked((MouseEvent e)->{
            Adicionar add = new Adicionar();
            fecha();
            try {
                add.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnEditar.setOnMouseClicked((MouseEvent e)->{
             if(selecionado!=null){
                Editar edit = new Editar(selecionado);
                fecha();
                try {
                    edit.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
             }else{
                 alerta();
             } 
        });
        
        btnDeletar.setOnMouseClicked((MouseEvent e)->{
            if(selecionado!=null){
                Deletar delete = new Deletar(selecionado);
                fecha();
                try {
                    delete.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                alerta();
            }     
        });

    }
    
   @FXML
    public void tabela(){
         Banco bd= new Banco();
         bd.realizaConexao();
         VeiculoDao veiculo= new VeiculoDao(bd.getConexao());
         clID.setCellValueFactory(new PropertyValueFactory("idVeiculo"));
         clModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
         clMarca.setCellValueFactory(new PropertyValueFactory("marca"));
         clPreco.setCellValueFactory(new PropertyValueFactory("preco"));
         clAno.setCellValueFactory(new PropertyValueFactory("anoLancamento"));
         clPlaca.setCellValueFactory(new PropertyValueFactory("placa"));
         ObservableList<Veiculo> lista= FXCollections.observableArrayList();
         try {
             ResultSet listaVeiculo=veiculo.obterListaVeiculos();
             while(listaVeiculo.next()){ 
                Veiculo veiculoAux = new Veiculo();
                veiculoAux.setIdVeiculo(listaVeiculo.getInt("idVeiculo"));
                veiculoAux.setModelo(listaVeiculo.getString("modelo"));
                veiculoAux.setMarca(listaVeiculo.getString("marca"));
                veiculoAux.setPreco(listaVeiculo.getString("preco"));
                veiculoAux.setAnoLancamento(listaVeiculo.getInt("anoLancamento"));
                veiculoAux.setPlaca(listaVeiculo.getString("placa"));
                veiculoAux.setImagem(listaVeiculo.getString("imagem"));
                lista.add(veiculoAux);
           }
             tbTabela.setItems(lista);
         } catch (SQLException ex) {
             Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @FXML
    void onOff(ActionEvent event) {
         if(checkBtn.isSelected()){
             btnAdd.setDisable(true);
             btnEditar.setDisable(true);
             btnDeletar.setDisable(true);
         }
         else{
             btnAdd.setDisable(false);
             btnEditar.setDisable(false);
             btnDeletar.setDisable(false);
         }
    }
    
    public void mostraDetalhes(){
        if(selecionado !=null){
            imgFoto.setImage(new Image("file:///"+selecionado.getImagem()));
            lbID.setText(selecionado.getIdVeiculo().toString());
            lbModelo.setText(selecionado.getModelo());
            lbPlaca.setText(selecionado.getPlaca());
        }
        else{
            lbID.setText("Nenhum veículo selecionado");
            lbModelo.setText("Nenhum veículo selecionado");
            lbPlaca.setText("Nenhum veículo selecionado");
        }
    }
    
    public void alerta(){
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("Selecione um veículo!!!");
        a.show();
    }
    
    public void fecha(){
        Principal.getStage().close();
    }
}
