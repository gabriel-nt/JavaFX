/*
 * Classe para conectar o banco de dados no java. Para isso, usamos
 *uma certa biblioteca.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adminC313
 */

public class Banco {
    private boolean statusConexao = false;
    private Connection conexao;
    private String mensagemErro;
    private String nomeDriver="com.mysql.jdbc.Driver";
    private String nomeServidor="localhost";
    private String portaServidor="3306";
    private String nomeUsuario="root";
    private String senha="vertrigo";
    private String nomeBanco="veiculos";
    private String url = "jdbc:mysql://"+this.nomeServidor+":"+this.portaServidor+"/"+this.nomeBanco+"?autoReconnect=true&useSSL=false";

    public boolean getStatusConexao() {
        return statusConexao;
    }

    public Connection getConexao() {
        return conexao;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
    
    public void realizaConexao(){
        try{
            Class.forName(this.nomeDriver);
            this.conexao=DriverManager.getConnection(this.url,this.nomeUsuario,this.senha);
            this.statusConexao=true;
        }
        catch(ClassNotFoundException | SQLException ex){
           this.mensagemErro=ex.toString();
           this.statusConexao=false;
        }
    }
    
    public void encerraConexao(){
        try{
            this.conexao.close();
        }
        catch(SQLException ex){
            this.mensagemErro=ex.toString();
        }
    }
    
}
