
package trabalho01_joseronaldosilveira;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String numero;
    private String data;
    private List<MoviPedidos> movimento;
    private String responsavel;
    private Float total;
    private Mesas idMesa;

    public Pedido() {
    }

    public Pedido(String numero, String data, float total, Mesas idMesa, String responsavel) {
        this.numero = numero;
        this.data = data;
        this.total = total;
        this.idMesa = idMesa;
        this.movimento = new ArrayList<>();
        this.responsavel = responsavel;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return  idMesa.getDescricao() + " - " + responsavel + " - " + total;
    }   

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<MoviPedidos> getMovimento() {
        return movimento;
    }

    public void setMovimento(List<MoviPedidos> movimento) {
        this.movimento = movimento;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Mesas getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Mesas idMesa) {
        this.idMesa = idMesa;
    }
    
    public boolean VerificaMesa(Mesas codMesa){
        if (codMesa.getCodigo() != this.idMesa.getCodigo()){
            return false;
        }else{
            return true;
        }        
    }
}
