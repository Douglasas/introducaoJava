package introducaojava.visao;

import introducaojava.controle.ManutencaoControle;
import introducaojava.modelo.Equipamento;
import introducaojava.modelo.Manutencao;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author dougl
 */
public class ListaManutencaoVisao implements Initializable{
    private Equipamento equipamento = new Equipamento();
    @FXML
    private TableView tableManutencoes;
    @FXML
    private TableColumn<Manutencao, String> colData, colDescricao;
    @FXML
    private TableColumn<Manutencao, Float> colValor;
    @FXML
    private Label lblTitulo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setEquipamento(Equipamento equipamento){
        this.equipamento = equipamento;
        this.lblTitulo.setText("Manutenções do "+equipamento.getNome());
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        colData.setCellValueFactory( cellData -> new SimpleStringProperty( dateFormat.format(cellData.getValue().getData()) ));
        colValor.setCellValueFactory( cellData -> new SimpleFloatProperty( cellData.getValue().getValor() ).asObject());
        colDescricao.setCellValueFactory( cellData -> new SimpleStringProperty( cellData.getValue().getDescricao() ));
        
        ArrayList<Manutencao> mans = ManutencaoControle.obterListaPorPatrimonio(this.equipamento.getPatrimonio());
        ObservableList<Manutencao> ol = FXCollections.observableArrayList(mans);
        tableManutencoes.setItems(ol);
    }
    public void verEquipamentos(ActionEvent event) throws IOException {
        Button btnVoltar = (Button) event.getSource();
        Scene sceneAtual = btnVoltar.getScene();
        Stage stageAtual = (Stage) sceneAtual.getWindow();
        
        Pane mainNovo = FXMLLoader.load( getClass().getResource("lista_equipamentos.fxml") );
        Scene sceneNovo = new Scene(mainNovo);
        stageAtual.setScene(sceneNovo);
        stageAtual.show();
    }
}
