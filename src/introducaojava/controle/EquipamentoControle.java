/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introducaojava.controle;

import introducaojava.modelo.Equipamento;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class EquipamentoControle {
    public static void receberDadosCadastroEquipamento(String nome, String patrimonio, Date dataAquisicao, Date dataTerminoGarantia, float valor) {
        Equipamento equip = new Equipamento();
        equip.setNome(nome);
        equip.setPatrimonio(patrimonio);
        equip.setDataAquisicao(dataAquisicao);
        equip.setDataTerminoGarantia(dataTerminoGarantia);
        equip.setValor(valor);
        equip.salvar();
    }
	
    public static ArrayList<Equipamento> obterListaEquipamentos(){
            return Equipamento.obterLista();
    }
    
    public static Equipamento obterEquipamentoPorNumPatrimonio(String numeroPatrimonio){
        return Equipamento.obterPeloNumero(numeroPatrimonio);
    }
}
