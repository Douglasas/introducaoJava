package introducaojava.controle;

import introducaojava.modelo.Equipamento;
import introducaojava.modelo.EquipamentoDAO;
import introducaojava.modelo.Manutencao;
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
        EquipamentoDAO.salvar(equip);
    }
    
    public static void receberDadosNovaManutencao(Equipamento equip, Manutencao man){
        equip.adicionarManutencao(man);
    }
	
    public static ArrayList<Equipamento> obterListaEquipamentos(){
            return EquipamentoDAO.obterLista();
    }
    
    public static Equipamento obterEquipamentoPorNumPatrimonio(String numeroPatrimonio){
        return EquipamentoDAO.obterPeloNumero(numeroPatrimonio);
    }
}
