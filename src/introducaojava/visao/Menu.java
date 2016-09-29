package introducaojava.visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Douglas
 */
public class Menu implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void novaManutencao(ActionEvent event) throws IOException{
        Button btnNovaManutencao = (Button) event.getSource();
        Scene sceneAtual = btnNovaManutencao.getScene();
        Stage stageAtual = (Stage) sceneAtual.getWindow();
        
        Pane mainNovo = FXMLLoader.load( getClass().getResource("nova_manutencao.fxml") );
        Scene sceneNovo = new Scene(mainNovo);
        stageAtual.setScene(sceneNovo);
        stageAtual.show();
    }
    public void novoEquipamento(ActionEvent event) throws IOException{
        Button btnNovoEquipamento = (Button) event.getSource();
        Scene sceneAtual = btnNovoEquipamento.getScene();
        Stage stageAtual = (Stage) sceneAtual.getWindow();
        
        Pane mainNovo = FXMLLoader.load( getClass().getResource("novo_equipamento.fxml") );
        Scene sceneNovo = new Scene(mainNovo);
        stageAtual.setScene(sceneNovo);
        stageAtual.show();
    }
    public void verEquipamentos(ActionEvent event) throws IOException {
        Button btnVerEquipamentos = (Button) event.getSource();
        Scene sceneAtual = btnVerEquipamentos.getScene();
        Stage stageAtual = (Stage) sceneAtual.getWindow();
        
        Pane mainNovo = FXMLLoader.load( getClass().getResource("lista_equipamentos.fxml") );
        Scene sceneNovo = new Scene(mainNovo);
        stageAtual.setScene(sceneNovo);
        stageAtual.show();
    }
}
