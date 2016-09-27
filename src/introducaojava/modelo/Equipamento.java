package introducaojava.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class Equipamento {
    private String nome;
    private String patrimonio;
    private Date dataAquisicao;
    private Date dataTerminoGarantia;
    private float valor;
    private ArrayList<Manutencao> listaManutencoes = new ArrayList<>();
    
    @Override
    public String toString(){
        return this.nome + " - " + this.patrimonio;
    }
    
    public void adicionarManutencao(Manutencao man){
        this.listaManutencoes.add(man);
        ManutencaoDAO.salvar(this.listaManutencoes, this.patrimonio);
    }
    
    public void setListaManutencoes(ArrayList<Manutencao> listaManutencoes){
        this.listaManutencoes = listaManutencoes;
    }
    
    public float getTotalGastoManutencoes(){
        float total = 0;
        for (Manutencao man : this.listaManutencoes){
            total += man.getValor();
        }
        return total;
    }
    
    public ArrayList<Manutencao> getListaManutencoes(){ return this.listaManutencoes; }
    
    public String getPatrimonio() { return patrimonio; }
    public void setPatrimonio(String patrimonio) { this.patrimonio = patrimonio; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Date getDataAquisicao() { return dataAquisicao; }
    public void setDataAquisicao(Date dataAquisicao) { this.dataAquisicao = dataAquisicao; }

    public Date getDataTerminoGarantia() { return dataTerminoGarantia; }
    public void setDataTerminoGarantia(Date dataTerminoGarantia) { this.dataTerminoGarantia = dataTerminoGarantia; }

    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }
}
