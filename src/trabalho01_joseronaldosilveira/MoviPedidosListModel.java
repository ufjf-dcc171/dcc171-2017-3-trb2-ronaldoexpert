package trabalho01_joseronaldosilveira;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class MoviPedidosListModel implements ListModel<MoviPedidos> {
    private final List<MoviPedidos> moviPedidos;
    private final List<ListDataListener> dataListeners;    ///Gerenciar a mudança nos dados - dispara eventos para controle
    
    public MoviPedidosListModel(List<MoviPedidos> moviPedidos) {
        this.moviPedidos = moviPedidos;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return moviPedidos.size();
    }

    @Override
    public MoviPedidos getElementAt(int index) {
        return moviPedidos.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListeners.remove(l);
    }
    
}
