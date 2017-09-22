
package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JanelaPedidos extends JFrame{
    private final List<Mesas> mesas;
    private final JList<Mesas> lstMesas = new JList<Mesas>(new DefaultListModel<>()); 
    
    private final List<Produtos> produtos;
    private final JList<Produtos> lstProdutos = new JList<Produtos>(new DefaultListModel<>());
    
    public JanelaPedidos(List<Mesas> mesas, List<Produtos> produtos) {
        super("Pedidos");
        
        this.mesas = mesas;
        lstMesas.setModel(new MesasListModel(mesas)); 
        
        this.produtos = produtos;
        lstProdutos.setModel(new ProdutosListModel(produtos));
        
        add(new JScrollPane(lstMesas), BorderLayout.WEST);    
        add(new JScrollPane(lstProdutos), BorderLayout.EAST);
    }
    
}
