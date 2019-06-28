package controller;

import DAO.VeiculoDao;
import cenas.Adicionar;
import cenas.Principal;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Banco;
import model.Veiculo;

/**
 *
 * @author Manos
 */
public class AdicionarController implements Initializable {
   
    private int erros=5;
    private int errosModelo=1;
    private int errosMarca=1;
    private int errosPlaca=1;
    private int errosPreco=1;
    private int errosAno=1;
    private boolean padraoAno= false;
    private boolean padraoPlaca= false;
    private boolean padraoPreco= false;
    private String caminhoImagem;
    
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEnviar;
    
    @FXML
    private Button btnLimpar;

    @FXML
    private Label labelAdd;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtPreco;
    
    @FXML
    private TextField txtID;
    
    @FXML
    private TextField txtAno;
    
    @FXML
    private Label menssagemErros;   
    
    @FXML
    private Label menssagemErros1;

    @FXML
    private Label menssagemErros2;

    @FXML
    private Label menssagemErros3;

    @FXML
    private Label menssagemErros4;
    
    @FXML
    private ImageView imgFoto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        invisivel();
        btnCancelar.setOnMouseClicked((MouseEvent e)->{
            Principal p = new Principal();
            fecha();
            try {
                p.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnEnviar.setOnMouseClicked((MouseEvent e)->{
            cadastro();
        });
        
        btnEnviar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
               cadastro();
            }
        });
        
        btnLimpar.setOnMouseClicked((MouseEvent e)->{
            limparCampos();
        });

        imgFoto.setOnMouseClicked((MouseEvent e)->{
              selecionaFoto();  
        });
    }

    public void fecha(){
        Adicionar.getStage().close();
    }
    
    public void adicionaVeiculo() throws SQLException{
        Banco bd= new Banco();
        bd.realizaConexao();
        String modelo= txtModelo.getText();
        String marca= txtMarca.getText();
        String placa= txtPlaca.getText();
        String preco= txtPreco.getText();
        String anoLanc = txtAno.getText();
        Integer ano = Integer.parseInt(anoLanc);
        VeiculoDao veiculo= new VeiculoDao(bd.getConexao());
        //veiculo.setModelo(modelo);
        //veiculo.setMarca(marca);
        //veiculo.setPreco(preco);
        //veiculo.setAnoLancamento(ano);
        //veiculo.setPlaca(placa);
        //veiculo.setImagem(caminhoImagem);
        Veiculo vei= new Veiculo(modelo, marca, ano, preco, placa, caminhoImagem);
        veiculo.incluiVeiculo(vei);
        System.out.println("Veiculo incluido");
    }
    
    
    public void selecionaFoto(){
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("Imagem","*.jpg","*.jpeg","*.png","*.svg"));
        File file = f.showOpenDialog(new Stage());
        if(file !=null){
            imgFoto.setImage(new Image("file:///"+file.getAbsolutePath()));
            caminhoImagem = file.getAbsolutePath();
        }
    }
    
    public void invisivel(){
        menssagemErros.setVisible(false);
        menssagemErros1.setVisible(false);
        menssagemErros2.setVisible(false);
        menssagemErros3.setVisible(false);
        menssagemErros4.setVisible(false);
    }
    
    public void verificaErros(){
        if(caminhoImagem==null){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Selecione uma imagem");
            a.show(); 
        }
        if (txtModelo.getText().length() > 0 && txtModelo.getText().length() >= 3 && txtModelo.getText().length() <= 50 && (errosModelo>0 || errosModelo==0)) {
            if(errosModelo==0){
                 menssagemErros.setVisible(false);
            }
            else{
                menssagemErros.setVisible(false);
                errosModelo--;
            }
        } else if(txtModelo.getText().length() == 0){
            menssagemErros.setVisible(true);
            menssagemErros.setText("Campo obrigatório");
        }else if(txtModelo.getText().length() <= 3){
            menssagemErros.setVisible(true);
            menssagemErros.setText("Campo deve ter no mínimo 3 carácteres");
        }else if(txtModelo.getText().length() > 50){
            menssagemErros.setVisible(true);
            menssagemErros.setText("Campo deve ter no máximo 50 carácteres");
        }
        
        
        if (txtMarca.getText().length() > 0 && txtMarca.getText().length() >= 3 && txtMarca.getText().length() <= 50 && (errosMarca>0 || errosMarca==0)) {
            if(errosMarca==0){
                 menssagemErros1.setVisible(false);
            }
            else{
                menssagemErros1.setVisible(false);
                errosMarca--;
            }
        } else if(txtMarca.getText().length() == 0){
            menssagemErros1.setVisible(true);
            menssagemErros1.setText("Campo obrigatório");
        }else if(txtMarca.getText().length() <= 3){
            menssagemErros1.setVisible(true);
            menssagemErros1.setText("Campo da placa ter no mínimo 3 carácteres");
        }else if(txtMarca.getText().length() > 50 ){
            menssagemErros1.setVisible(true);
            menssagemErros1.setText("Campo deve ter no máximo 50 carácteres");
        }
        
        
        if(txtPlaca.getText().length() == 8){
            String regex="^[A-Z]{3}\\-\\d{4}$";
            Pattern pattern= Pattern.compile(regex);
            Matcher matcher = pattern.matcher(txtPlaca.getText());
            if(!matcher.find()){
                padraoPlaca = false;
                menssagemErros2.setVisible(true);
                menssagemErros2.setText("Padrão: AAA-9999");
            }
            else{
                padraoPlaca = true;
                menssagemErros2.setVisible(true);
            }
        }
        if (txtPlaca.getText().length() > 0 && txtPlaca.getText().length() ==8 && padraoPlaca==true && (errosPlaca>0 || errosPlaca==0)) {
            if(errosPlaca==0){
                 menssagemErros2.setVisible(false);
            }
            else{
                menssagemErros2.setVisible(false);
                errosPlaca--;
            }
        } else if(txtPlaca.getText().length() == 0){
            menssagemErros2.setVisible(true);
            menssagemErros2.setText("Campo obrigatório");
        }else if(txtPlaca.getText().length() <8){
            menssagemErros2.setVisible(true);
            menssagemErros2.setText("O campo da placa deve ter 8 caracteres, contando o hífen(-)");
        }else if(txtPlaca.getText().length() >=9){
            menssagemErros2.setVisible(true);
            menssagemErros2.setText("O campo da placa deve ter 8 caracteres, contando o hífen(-)");
        }       
        
        if(txtPreco.getText().length() >=4){
            String regex="^[1-9]\\d{0,2}(\\.\\d{3})*,\\d{2}$";
            Pattern pattern= Pattern.compile(regex);
            Matcher matcher = pattern.matcher(txtPreco.getText());
            if(!matcher.find()){
                padraoPreco = false;
                menssagemErros3.setVisible(true);
                menssagemErros3.setText("Padrão: R$20.000,00");
            }
            else{
                padraoPreco = true;
                menssagemErros3.setVisible(true);
            }
        }
        if (txtPreco.getText().length() > 0 && txtPreco.getText().length() >= 4 && padraoPreco == true && txtPreco.getText().length() <= 10 && (errosPreco>0 || errosPreco==0)) {
            if(errosPreco==0){
                 menssagemErros3.setVisible(false);
            }
            else{
                menssagemErros3.setVisible(false);
                errosPreco--;
            }
        } else if(txtPreco.getText().length() == 0){
            menssagemErros3.setVisible(true);
            menssagemErros3.setText("Campo obrigatório");
        }else if(txtPreco.getText().length() < 6){
            menssagemErros3.setVisible(true);
            menssagemErros3.setText("Campo de preço deve ter no mínimo 5 digitos");
        }else if(txtPreco.getText().length() >20){
            menssagemErros3.setVisible(true);
            menssagemErros3.setText("Campo deve preço ter no máximo 20 digitos");
        }
        
        if(txtAno.getText().length() == 4){
            String regex="18[0-9][0-9]|19[0-9][0-9]|20[0-1][0-9]";
            Pattern pattern= Pattern.compile(regex);
            Matcher matcher = pattern.matcher(txtAno.getText());
            if(!matcher.find()){
                padraoAno = false;
                menssagemErros4.setVisible(true);
                menssagemErros4.setText("O campo só aceitará números e anos de 1800 até 2019");
            }
            else{
                padraoAno = true;
                menssagemErros4.setVisible(true);
            }
        }
        if (txtAno.getText().length() > 0 && txtAno.getText().length() == 4 && padraoAno == true && (errosAno>0 || errosAno==0)) {
            if(errosAno==0){
                 menssagemErros4.setVisible(false);
            }
            else{
                menssagemErros4.setVisible(false);
                errosAno--;
            }
        }else if(txtAno.getText().length() == 0){
            menssagemErros4.setVisible(true);
            menssagemErros4.setText("Campo obrigatório");
        }else if(txtAno.getText().length() < 4){
            menssagemErros4.setVisible(true);
            menssagemErros4.setText("O Campo do ano deve ter 4 digitos");
        }else if(txtAno.getText().length() >= 5){
            menssagemErros4.setVisible(true);
            menssagemErros4.setText("O Campo do ano deve ter 4 digitos");
        }        
    }
    
    public void cadastro(){
        verificaErros();
        if(errosModelo==0 && errosMarca==0 && errosPlaca==0 && errosPreco==0 && errosAno==0 && caminhoImagem != null){
            Principal p = new Principal();
            fecha();
            try {
                adicionaVeiculo();
                p.start(new Stage());
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("Veiculo adicionado com sucesso");
                a.show(); 
            } catch (Exception ex) {
                Logger.getLogger(AdicionarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void limparCampos(){
        txtModelo.setText("");
        txtMarca.setText("");
        txtPlaca.setText("");
        txtPreco.setText("");
        txtAno.setText("");
    }
}
