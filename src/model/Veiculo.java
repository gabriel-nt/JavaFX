/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;

/**
 *
 * @author adminC313
 */
public class Veiculo {
    private Integer idVeiculo;
    private String modelo;
    private String marca;
    private Integer anoLancamento;
    private String preco;
    private String placa;
    private String imagem;
    private Connection conexao;
    
    public Veiculo(Integer idVeiculo){
        this.idVeiculo = idVeiculo;
    }
    
    public Veiculo(){
       
    }

    public Veiculo( String modelo, String marca, Integer anoLancamento, String preco, String placa, String imagem) {
        this.modelo = modelo;
        this.marca = marca;
        this.anoLancamento = anoLancamento;
        this.preco = preco;
        this.placa = placa;
        this.imagem = imagem;
    }
    
    public Veiculo(Integer idVeiculo, String modelo, String marca, Integer anoLancamento, String preco, String placa, String imagem) {
        this.idVeiculo = idVeiculo;
        this.modelo = modelo;
        this.marca = marca;
        this.anoLancamento = anoLancamento;
        this.preco = preco;
        this.placa = placa;
        this.imagem = imagem;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
