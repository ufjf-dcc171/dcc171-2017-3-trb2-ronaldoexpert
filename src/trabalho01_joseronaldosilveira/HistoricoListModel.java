
package trabalho01_joseronaldosilveira;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class HistoricoListModel implements ListModel<MoviPedidos>{
    private final List<MoviPedidos> historico;
    private final List<ListDataListener> dataListeners;    ///Gerenciar a mudança nos dados - dispara eventos para controle
    
    public HistoricoListModel(List<MoviPedidos> historico) {
        this.historico = historico;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return historico.size();
    }

    @Override
    public MoviPedidos getElementAt(int index) {
        return historico.get(index);
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
