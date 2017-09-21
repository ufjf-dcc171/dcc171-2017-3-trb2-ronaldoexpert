
package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JanelaPedidos extends JFrame{
    private final List<Pedido> pedidos;
    private final JList<Pedido> lstPedidos = new JList<Pedido>(new DefaultListModel<>()); 
    
    public JanelaPedidos(List<Pedido> sampleData) {
        super("Pedidos");
        
        this.pedidos = sampleData;
        lstPedidos.setModel(new PedidosListModel(pedidos)); 
        
        add(new JScrollPane(lstPedidos), BorderLayout.WEST);
        
    }
    
}
