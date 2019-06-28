package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Veiculo;

public class VeiculoDao {
    
    private Connection conexao;
    
    public VeiculoDao(Connection conexao){
        this.conexao=conexao;
    }
    
    public ResultSet obterListaVeiculos() throws SQLException{
        String sql= "select idVeiculo, marca, modelo, preco, anoLancamento, placa,imagem FROM veiculo";
        PreparedStatement requisicao= this.conexao.prepareStatement(sql);
        return requisicao.executeQuery();
    }

    public void incluiVeiculo(Veiculo v) throws SQLException{
        String slq= "INSERT INTO veiculo(marca, modelo, preco, anoLancamento, placa, imagem) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement requisicao= this.conexao.prepareStatement(slq);
        requisicao.setString(1, v.getMarca());
        requisicao.setString(2, v.getModelo());
        requisicao.setString(3, v.getPreco());
        requisicao.setInt(4, v.getAnoLancamento());
        requisicao.setString(5, v.getPlaca());
        requisicao.setString(6, v.getImagem());
        requisicao.executeUpdate();
        requisicao.close(); 
    }
    
    public void atualizaVeiculo(Veiculo v) throws SQLException{
        String sql= "Update veiculo set marca=?,modelo=?,preco=?,anoLancamento=?,placa=?,imagem=? where idVeiculo=?";
        PreparedStatement requisicao= this.conexao.prepareStatement(sql);
        requisicao.setString(1, v.getMarca());
        requisicao.setString(2, v.getModelo());
        requisicao.setString(3, v.getPreco());
        requisicao.setInt(4, v.getAnoLancamento());
        requisicao.setString(5, v.getPlaca());
        requisicao.setString(6, v.getImagem());
        requisicao.setInt(7, v.getIdVeiculo());
        requisicao.executeUpdate();
    }
    
    public void removeVeiculo(Veiculo v)throws SQLException{
        String sql= "DELETE FROM veiculo WHERE idVeiculo=?";
        PreparedStatement requisicao= this.conexao.prepareStatement(sql);
        requisicao.setInt(1, v.getIdVeiculo());
        requisicao.executeUpdate();
    }
}
