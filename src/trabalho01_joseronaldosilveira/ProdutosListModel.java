
package trabalho01_joseronaldosilveira;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ProdutosListModel implements ListModel<Produtos>{
    private final List<Produtos> produtos;
    private final List<ListDataListener> dataListeners;    ///Gerenciar a mudança nos dados - dispara eventos para controle
    
    public ProdutosListModel(List<Produtos> produtos) {
        this.produtos = produtos;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return produtos.size();
    }

    @Override
    public Produtos getElementAt(int index) {
        return produtos.get(index);
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
