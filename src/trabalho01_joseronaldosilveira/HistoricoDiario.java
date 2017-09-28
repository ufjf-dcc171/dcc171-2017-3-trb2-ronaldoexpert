package trabalho01_joseronaldosilveira;

import java.util.List;

public class HistoricoDiario {
    private List<MoviPedidos> pedidos;

    public HistoricoDiario(List<MoviPedidos> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return pedidos.toString();  
           
    }

    public List<MoviPedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<MoviPedidos> pedidos) {
        this.pedidos = pedidos;
    }
}
