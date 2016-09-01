package introducaojava.controle;

import introducaojava.modelo.Equipamento;
import introducaojava.modelo.Manutencao;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class ManutencaoControle {
    public static void receberDadosNovaManutencao(Equipamento equip, String descricao, Date data,  float valor){
        Manutencao man = new Manutencao();
        man.setDescricao(descricao);
        man.setData(data);
        man.setValor(valor);
        
        equip.adicionarManutencao(man);
    }
}
