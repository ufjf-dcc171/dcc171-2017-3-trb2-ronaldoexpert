
package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class JanelaFechaMesa extends JFrame{
    
    private final JPanel pnlNorth = new JPanel();
    private final JPanel pnlSouth = new JPanel();
    
    private final JList<MoviPedidos> lstPedidos = new JList<MoviPedidos>(new DefaultListModel<>()); 
    private final JTextField txtTotal = new JTextField(20);
    private final JLabel lblTotal = new JLabel("Total: ");
    
    private final JTextField txtTotalPessoa = new JTextField(20);
    private final JLabel lblTotalPessoa = new JLabel("");
    
    private float total = 0;
    private float totalPessoa = 0;
    public JanelaFechaMesa(List<MoviPedidos> pedidos, int qtdPessoas) throws HeadlessException {
        super("FechaMesa");
        setLayout(new BorderLayout());
        pnlNorth.setLayout(new BorderLayout());
        pnlSouth.setLayout(new GridLayout(5, 2));
        txtTotal.setEditable(false);
        txtTotalPessoa.setEditable(false);
        txtTotal.setFont(new Font("Times New Roman", Font.BOLD, 22));  
        lblTotalPessoa.setText("Total dividido por " + qtdPessoas + " Pessoa(s): ");
        
        lstPedidos.setModel(new HistoricoListModel(pedidos));
        lstPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        for(int i = 0; i < pedidos.size(); i++){
            total = total + (pedidos.get(i).getVlrUnitario() * pedidos.get(i).getQuatidade());
        }
        
        totalPessoa = total / qtdPessoas;
        
        txtTotal.setText(total + "");       
        txtTotalPessoa.setText(totalPessoa + "");
        
        pnlSouth.add(lblTotal);
        pnlSouth.add(txtTotal);
        pnlSouth.add(lblTotalPessoa);
        pnlSouth.add(txtTotalPessoa);
        
        pnlNorth.add(new JScrollPane(lstPedidos), BorderLayout.CENTER);
        
        add(pnlNorth, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
        
    }
    
    
}
