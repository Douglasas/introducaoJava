package introducaojava.visao;

import introducaojava.controle.EquipamentoControle;
import introducaojava.modelo.Equipamento;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author dougl
 */
public class ListaEquipamentosVisao implements Initializable {
    @FXML
    private TableView tableEquipamentos;
    @FXML
    private TableColumn<Equipamento, String> colPat, colNome, colDataAq, colDataGar;
    @FXML
    private TableColumn<Equipamento, Float> colValor;
    @FXML
    private TableColumn<Equipamento, Integer> colMans;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Seta de onde serao pegos os valores para preenchimento da tabela */
        colPat.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getPatrimonio()));
        colNome.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colValor.setCellValueFactory( cellData -> new SimpleFloatProperty( cellData.getValue().getValor() ).asObject());
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        colDataAq.setCellValueFactory( cellData -> new SimpleStringProperty( dateFormat.format(cellData.getValue().getDataAquisicao()) ));
        colDataGar.setCellValueFactory( cellData -> new SimpleStringProperty( dateFormat.format(cellData.getValue().getDataTerminoGarantia()) ));
        colMans.setCellValueFactory( cellData -> new SimpleIntegerProperty( cellData.getValue().getListaManutencoes().size() ).asObject());
        
        /* CLIQUE NA LINHA */
        tableEquipamentos.setOnMouseClicked((MouseEvent event) -> {
                Node node = ((Node) event.getTarget()).getParent();
                TableRow row = null;
                if (node instanceof TableRow) {
                    row = (TableRow) node;
                } else {
                    // clicking on text part
                    if (node.getParent() instanceof TableRow)
                        row = (TableRow) node.getParent();
                }
                Equipamento equip = null;
                if (row != null)
                    equip = (Equipamento) row.getItem();
                if (equip != null)
                    try {
                        //Scene sceneAtual = row.getScene();
                        //Stage stageAtual = (Stage) sceneAtual.getWindow();
                        Stage novoStage = new Stage();

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lista_manutencoes.fxml"));
                        Pane mainNovo = fxmlLoader.load();
                        
                        ListaManutencaoVisao listaManutencaoVisao = fxmlLoader.<ListaManutencaoVisao>getController();
                        listaManutencaoVisao.setEquipamento(equip);

                        Scene sceneNovo = new Scene(mainNovo);
                        novoStage.setScene(sceneNovo);
                        novoStage.showAndWait();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ListaEquipamentosVisao.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
        
        ArrayList<Equipamento> equips = EquipamentoControle.obterListaEquipamentos();
        ObservableList<Equipamento> ol = FXCollections.observableArrayList(equips);
        tableEquipamentos.setItems(ol);
    }
    public void voltar(ActionEvent event) throws IOException {
        Button btnVoltar = (Button) event.getSource();
        voltarAoMenu(btnVoltar.getScene());
    }
    public void voltarAoMenu(Scene sceneAtual) throws IOException{
        Stage stageAtual = (Stage) sceneAtual.getWindow();
        
        Pane mainNovo = FXMLLoader.load( getClass().getResource("menu.fxml") );
        Scene sceneNovo = new Scene(mainNovo);
        stageAtual.setScene(sceneNovo);
        stageAtual.show();
    }
}
