package introducaojava.controle;

import introducaojava.modelo.Equipamento;
import introducaojava.modelo.Manutencao;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class ManutencaoControle {
    public static Manutencao receberDadosNovaManutencao(String descricao, Date data,  float valor){
        Manutencao man = new Manutencao();
        man.setDescricao(descricao);
        man.setData(data);
        man.setValor(valor);
        return man;
    }
}
