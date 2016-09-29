package introducaojava.controle;

import introducaojava.modelo.Manutencao;
import introducaojava.modelo.ManutencaoDAO;
import java.util.ArrayList;
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
    
    public static ArrayList<Manutencao> obterListaPorPatrimonio(String patrimonio){
        return ManutencaoDAO.obterLista(patrimonio);
    }
}
