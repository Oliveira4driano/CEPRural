/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Adriano
 */
public class Comunidade {
    private int codigo;
    private String nome;
    private int qtd;
    private Estado estado;

    public Comunidade() {
        this.estado = new Estado();
                
    }

    public Comunidade(int codigo, String nome, int qtd, Estado estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtd = qtd;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
