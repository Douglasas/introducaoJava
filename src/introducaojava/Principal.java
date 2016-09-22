package introducaojava;

import introducaojava.visao.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Douglas
 */
public class Principal extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Menu.exibirMenu();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane principal = FXMLLoader.load( getClass().getResource("visao/menu.fxml"));
        Scene scene = new Scene(principal);
        stage.setScene(scene);
        stage.show();
    }    
}
