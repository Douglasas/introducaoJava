package introducaojava.visao;

import java.util.Scanner;

/**
 *
 * @author Douglas
 */
public class Menu {
    public static void exibirMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcao = -1;
        System.out.println("======= MENU =======");
        System.out.println("Selecione a opcao: ");
        System.out.println("1. Cadastro de equipamento");
        System.out.println("2. Listagem de equipamentos");
        System.out.println("0. Sair");
        System.out.print("O que voce deseja fazer? ");
        do {
            try {
                opcao = Integer.parseInt(entrada.nextLine());
                System.out.println("O usuario digitou " + opcao);
                if (opcao < 0 || 2 < opcao)
                    System.out.print("Opcao invalida, informe novamente: ");
            } catch (Exception e) {
                System.out.print("Nao deu certo, informe novamente: ");
            }
        } while (opcao < 0 || 2 < opcao);
        switch (opcao) {
            case 1:
                EquipamentoVisao.exibirFormularioCadastroEquipamento();
                break;
            case 2:
                EquipamentoVisao.exibirListagemEquipamentos();
                break;
        }
    }
}
