package introducaojava.visao;

import introducaojava.controle.EquipamentoControle;
import introducaojava.modelo.Equipamento;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Douglas
 */
public class EquipamentoVisao implements Initializable {
    
    @FXML
    private TextField campoNome, campoId, campoValor;
    @FXML
    private DatePicker campoDataAq, campoDataGar;
    
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
        ArrayList<Equipamento> lista = EquipamentoControle.obterListaEquipamentos();
        System.out.println("=== TELA DE LISTAGEM DE EQUIPAMENTOS ===");
        System.out.println("NOME\tNº PATRIMONIO\tNUM MANUTENCOES\tTOT VALOR MANUTENCOES");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void salvarEquipamento(ActionEvent event){
        try {
            Button btnSalvar = (Button) event.getSource();
            String nome = campoNome.getText();
            String patrimonio = campoId.getText();
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date dataAquisicao = format.parse(campoDataAq.getValue().toString());
            Date dataTerminoGarantia = format.parse(campoDataGar.getValue().toString());
        
            float valor = Float.parseFloat(campoValor.getText());
            EquipamentoControle.receberDadosCadastroEquipamento(nome, patrimonio, dataAquisicao, dataTerminoGarantia, valor);
            voltarAoMenu(btnSalvar.getScene());
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Dados inválidos");
            alert.setHeaderText(null);
            alert.setContentText("Campos preenchidos incorretamente");
            alert.showAndWait();
        }
    }
    
    public void cancelar(ActionEvent event) throws IOException {
        Button btnCancelar = (Button) event.getSource();
        voltarAoMenu(btnCancelar.getScene());
    }
    
    public void voltarAoMenu(Scene sceneAtual) throws IOException{
        Stage stageAtual = (Stage) sceneAtual.getWindow();
        
        Pane mainNovo = FXMLLoader.load( getClass().getResource("menu.fxml") );
        Scene sceneNovo = new Scene(mainNovo);
        stageAtual.setScene(sceneNovo);
        stageAtual.show();
    }
}
