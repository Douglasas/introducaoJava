package introducaojava;

import introducaojava.modelo.Equipamento;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class MeioArmazenamento {
    public static ArrayList<Equipamento> EQUIPAMENTOS = new ArrayList<>();
    
    public static void salvarDados() {
        try {
            
            FileOutputStream fout = new FileOutputStream("dados.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(EQUIPAMENTOS);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MeioArmazenamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MeioArmazenamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void recuperarDados(){
        try {
            
            FileInputStream fin = new FileInputStream("dados.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            EQUIPAMENTOS = (ArrayList<Equipamento>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MeioArmazenamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MeioArmazenamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
