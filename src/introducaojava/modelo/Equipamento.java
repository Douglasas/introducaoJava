/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introducaojava.modelo;

import introducaojava.MeioArmazenamento;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class Equipamento {
    private String nome;
    private String patrimonio;
    private Date dataAquisicao;
    private Date dataTerminoGarantia;
    private float valor;
    private ArrayList<Manutencao> listaManutencoes = new ArrayList<>();

    public static ArrayList<Equipamento> obterLista(){
            return MeioArmazenamento.EQUIPAMENTOS;
    }
    
    public static Equipamento obterPeloNumero(String numeroPatrimonio) {
        for (Equipamento equip : Equipamento.obterLista()){
            if (equip.getPatrimonio().equals(numeroPatrimonio)){
                return equip;
            }
        }
        return null;
    }

    public ArrayList<Manutencao> getListaManutencoes(){ return this.listaManutencoes; }
    
    public String getPatrimonio() { return patrimonio; }
    public void setPatrimonio(String patrimonio) { this.patrimonio = patrimonio; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Date getDataAquisicao() { return dataAquisicao; }
    public void setDataAquisicao(Date dataAquisicao) { this.dataAquisicao = dataAquisicao; }

    public Date getDataTerminoGarantia() { return dataTerminoGarantia; }
    public void setDataTerminoGarantia(Date dataTerminoGarantia) { this.dataTerminoGarantia = dataTerminoGarantia; }

    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }

    public void salvar() {
        MeioArmazenamento.EQUIPAMENTOS.add(this);
    }
}
