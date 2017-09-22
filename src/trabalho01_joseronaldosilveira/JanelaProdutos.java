package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JanelaProdutos extends JFrame{
    private final List<Produtos> produtos;
    private final JList<Produtos> lstProdutos = new JList<Produtos>(new DefaultListModel<>()); 
    
    public JanelaProdutos(List<Produtos> produtos) throws HeadlessException {
        super("Produtos");
        
        this.produtos = produtos;
        lstProdutos.setModel(new ProdutosListModel(produtos)); 
        
        add(new JScrollPane(lstProdutos), BorderLayout.EAST);
        
    }
    
}
