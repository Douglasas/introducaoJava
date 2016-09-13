package introducaojava.visao;

import introducaojava.controle.EquipamentoControle;
import introducaojava.modelo.Equipamento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Douglas
 */
public class EquipamentoVisao {
    public static void exibirFormularioCadastroEquipamento(){
        System.out.println("==== TELA CADASTRO DE EQUIPAMENTO ====");

        Scanner in = new Scanner(System.in);
        String nome, patrimonio;
        Date dataAquisicao, dataTerminoGarantia;
        float valor;

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        formatadorData.setLenient(false); // Passa a ser mais exigente com datas incorretas

        System.out.print("Informe o nome: ");
        nome = in.nextLine();

        System.out.print("Informe o numero do patrimonio: ");
        patrimonio = in.nextLine();

        System.out.print("Informe a data de aquisicao: ");
        do {
            try {
                dataAquisicao = formatadorData.parse(in.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Data invalida, informe novamente: ");
            }
        } while (true);

        System.out.print("Informe a data de termino da garantia: ");
        do {
            try {
                dataTerminoGarantia = formatadorData.parse(in.nextLine());
                if (dataTerminoGarantia.before(dataAquisicao))
                    System.out.print("Data de termino deve ser posterior a data de aquisicao, informe novamente: ");
                else
                    break;
            } catch (Exception e) {
                System.out.print("Data invalida, informe novamente: ");
            }
        } while (true);

        System.out.print("Informe o valor (R$): ");
        do {
            try {
                valor = Float.parseFloat(in.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Valor invalido, informe novamente: ");
            }
        } while (true);
        
        EquipamentoControle.receberDadosCadastroEquipamento(nome, patrimonio, dataAquisicao, dataTerminoGarantia, valor);
    }
    public static void exibirListagemEquipamentos(){
            System.out.println("=== TELA DE LISTAGEM DE EQUIPAMENTOS ===");
            System.out.println("NOME\tNº PATRIMONIO\tNUM MANUTENCOES\tTOT VALOR MANUTENCOES");
            ArrayList<Equipamento> lista = EquipamentoControle.obterListaEquipamentos();
            for (Equipamento eq : lista) {
                System.out.println(eq.getNome() + "\t" + eq.getPatrimonio() + "\t" + eq.getListaManutencoes().size() + "\tR$" + eq.getTotalGastoManutencoes());
            }
            System.out.println("O que voce deseja fazer?");
            System.out.println("0. Voltar ao menu");
            System.out.println("Nº patrimonio. Cadastrar manutenção para o equipamento");             
            Scanner in = new Scanner(System.in);
            String valorDigitado = in.nextLine();
            if (!valorDigitado.equals("0")) {
                Equipamento equip = EquipamentoControle.obterEquipamentoPorNumPatrimonio(valorDigitado);
                if (equip == null) {
                    System.out.println("Equipamento nao encontrado, tente novamente");
                    EquipamentoVisao.exibirListagemEquipamentos();
                } else {
                    ManutencaoVisao.exibirFormularioCadastroManutencao(equip);
                }
            }
    }
}
