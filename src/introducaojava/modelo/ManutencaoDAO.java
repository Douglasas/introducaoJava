package introducaojava.modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dougl
 */
public class ManutencaoDAO {
    
    public static ArrayList<Manutencao> obterLista(String id){
        ArrayList<Manutencao> mans = new ArrayList<>();
        try {
            Path arquivo = Paths.get("armazenamento/equip"+id+".txt");
            if (Files.exists(arquivo)){
                for (String linha : Files.readAllLines(arquivo)){
                    Manutencao m = new Manutencao();
                    SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy");
                    String[] at = linha.split(";");
                    m.setValor(Float.parseFloat(at[0]));
                    m.setData(dat.parse(at[1]));
                    m.setDescricao(at[2]);

                    mans.add(m);
                }
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Equipamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mans;
    }
    
    public static void salvar(ArrayList<Manutencao> mans, String id) {
        
        // SALVA NO ARQUIVO
        
        Path arquivo = Paths.get("armazenamento/equip"+id+".txt");
        SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy");
        String manutencoes = "";
        for (Manutencao m : mans){
            manutencoes +=    m.getValor() +";"
                            + dat.format(m.getData()) +";"
                            + m.getDescricao()
                            + System.getProperty("line.separator");
        }
        try {
            Files.write(arquivo, manutencoes.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE );
        } catch (IOException ex) {
            Logger.getLogger(Equipamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
