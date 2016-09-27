package introducaojava.visao;

import introducaojava.controle.EquipamentoControle;
import introducaojava.controle.ManutencaoControle;
import introducaojava.modelo.Equipamento;
import introducaojava.modelo.Manutencao;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Douglas
 */
public class ManutencaoVisao implements Initializable {
    @FXML
    private ComboBox cbxEquipamentos;
    @FXML
    private TextField campoValor;
    @FXML
    private DatePicker campoData;
    @FXML
    private TextArea txtDescricao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ADICIONA EQUIPAMENTOS AO COMBOBOX */
        ArrayList<Equipamento> equips = EquipamentoControle.obterListaEquipamentos();
        if (equips.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Falta de dados");
            alert.setHeaderText(null);
            alert.setContentText("Primeiro devem ser cadastrados os Equipamentos");
            alert.showAndWait();
        } else {
            ObservableList<Equipamento> ol = FXCollections.observableArrayList(equips);
            cbxEquipamentos.setItems(ol);
            //for (Equipamento eq : equips){
            //    cbxEquipamentos.getItems().add(eq.getNome());
           // }
        }
    }
    
    public void salvar(ActionEvent event) {
        try {
            
            String descricao = txtDescricao.getText();
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date data = format.parse(campoData.getValue().toString());
            float valor = Float.parseFloat(campoValor.getText());
            EquipamentoControle.receberDadosNovaManutencao((Equipamento) cbxEquipamentos.getValue(),
                    ManutencaoControle.receberDadosNovaManutencao(descricao, data, valor));
            
            Button btnSalvar =  (Button) event.getSource();
            voltarAoMenu(btnSalvar.getScene());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
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
        
        EquipamentoControle.receberDadosNovaManutencao(equip, ManutencaoControle.receberDadosNovaManutencao(descricao, data, valor));
        
        //Menu.exibirMenu();
    }
}
