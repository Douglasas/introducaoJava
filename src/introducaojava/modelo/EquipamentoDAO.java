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
 * @author 5932432
 */
public class EquipamentoDAO {
    public static ArrayList<Equipamento> obterLista(){
        ArrayList<Equipamento> equips = new ArrayList<>();
        try {
            Path arquivo = Paths.get("armazenamento/equips.txt");
            if (Files.exists(arquivo)){
                for (String linha : Files.readAllLines(arquivo)){
                    Equipamento e = new Equipamento();
                    SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy");
                    String[] at = linha.split(";");
                    e.setNome(at[0]);
                    e.setPatrimonio(at[1]);
                    e.setDataAquisicao(dat.parse(at[2]));
                    e.setDataTerminoGarantia(dat.parse(at[3]));
                    e.setValor(Float.parseFloat(at[4]));
                    
                    e.setListaManutencoes(ManutencaoDAO.obterLista(e.getPatrimonio()));

                    equips.add(e);
                }
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Equipamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equips;
    }
    
    public static Equipamento obterPeloNumero(String numeroPatrimonio) {
        for (Equipamento equip : EquipamentoDAO.obterLista()){
            if (equip.getPatrimonio().equals(numeroPatrimonio)){
                return equip;
            }
        }
        return null;
    }
    
    public static void salvar(Equipamento eq) {
        //MeioArmazenamento.EQUIPAMENTOS.add(this);
        
        // SALVA NO ARQUIVO
        
        Path arquivo = Paths.get("armazenamento/equips.txt");
        SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy");
        String equip = eq.getNome() +";"
                        + eq.getPatrimonio() +";"
                        + dat.format(eq.getDataAquisicao()) +";"
                        + dat.format(eq.getDataTerminoGarantia()) +";"
                        + eq.getValor()
                        + System.getProperty("line.separator");
        ManutencaoDAO.salvar(eq.getListaManutencoes(), eq.getPatrimonio());
        try {
            Files.write(arquivo, equip.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND );
        } catch (IOException ex) {
            Logger.getLogger(Equipamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
