package introducaojava.visao;

import introducaojava.controle.ManutencaoControle;
import introducaojava.modelo.Equipamento;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Douglas
 */
public class ManutencaoVisao {
    public static void exibirFormularioCadastroManutencao(Equipamento equip){
        System.out.println("=== TELA CADASTRO MANUTENCAO EQUIPAMENTO ===");
        
        Scanner in = new Scanner(System.in);
        String descricao = null;
        Date data = null;
        float valor = 0;
        
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        formatadorData.setLenient(false);
        
        System.out.print("Informe a descricao: ");
        descricao = in.nextLine();
        
        System.out.print("Informe a data: ");
        do {
            try {
                data = formatadorData.parse(in.nextLine());
                break;
            } catch (Exception e){
                System.out.print("Data invalida, informe novamente: ");
            }
        } while (true);
        
        System.out.print("Informe o valor (R$): ");
        do {
            try {
                valor = Float.parseFloat(in.nextLine());
                break;
            } catch (Exception e){
                System.out.print("Valor invalido, informe novamente: ");
            }
        } while (true);
        
        ManutencaoControle.receberDadosNovaManutencao(equip, descricao, data, valor);
        
        Menu.exibirMenu();
    }
}
