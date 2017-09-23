
package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class JanelaPedidos extends JFrame{
    private final List<Mesas> mesas;
    private final JList<Mesas> lstMesas = new JList<Mesas>(new DefaultListModel<>()); 
    
    private final List<Produtos> produtos;
    private final JList<Produtos> lstProdutos = new JList<Produtos>(new DefaultListModel<>());
    
    private final List<Pedido> pedidos;
    private final JList<Pedido> lstPedidos = new JList<Pedido>(new DefaultListModel<>());
    
    private final List<MoviPedidos> moviPedidos;
    private final JList<MoviPedidos> lstMoviPedidos = new JList<MoviPedidos>(new DefaultListModel<>());
    
    private final JPanel pnlDireita = new JPanel();
    private final JPanel pnlEsquerda = new JPanel();
    private final JPanel pnlPrincipal = new JPanel();
    private final JPanel pnlBotoes = new JPanel();
    private final JPanel pnlComponentes = new JPanel(); 
    private final JPanel pnlBotoesProduto = new JPanel(); 
    
    private final JButton btnNovo = new JButton("Novo");
    private final JButton btnGravar = new JButton("Gravar");
    private JComboBox cboMesas = new JComboBox(); 
    
    private final JTextField txtCodProduto = new JTextField();
    private final JTextField txtProduto = new JTextField();
    private final JLabel lblProduto = new JLabel("Produto");    
    private final JTextField txtVlrUnit = new JTextField();
    private final JLabel lblVlrUnit = new JLabel("Vlr. Unitário");    
    private final JTextField txtQuantidade = new JTextField();
    private final JLabel lblQuantidade = new JLabel("Quantidade");    
    private final JTextField txtTotal = new JTextField();
    private final JLabel lblTotal = new JLabel("Total");
    
    private final JButton btnAdd = new JButton("ADD");
    private final JButton btnExcluir = new JButton("Excluir");
    private boolean achouMesa = false;
    
    public JanelaPedidos(List<Mesas> mesas, List<Produtos> produtos, List<Pedido> pedidos, List<MoviPedidos> moviPedidos) {
        super("Pedidos");
        
        //Define os paineis
        setLayout(new BorderLayout());
        pnlDireita.setLayout(new BorderLayout());
        pnlEsquerda.setLayout(new GridLayout(1, 1));
        pnlPrincipal.setLayout(new BorderLayout());
        pnlBotoes.setLayout(new GridLayout(1, 2));
        pnlComponentes.setLayout(new GridLayout(11, 1));
        pnlBotoes.setLayout(new GridLayout(1, 2));
        
        //Passa os dados para a lista e defina o modelo para as listas com o conjunto de dados
        this.mesas = mesas;
        lstMesas.setModel(new MesasListModel(mesas));
        
        this.produtos = produtos;
        lstProdutos.setModel(new ProdutosListModel(produtos));
        
        this.pedidos = pedidos;
        lstPedidos.setModel(new PedidosListModel(pedidos));
        
        //Seleção unica nas listas
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstMoviPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.moviPedidos = null;
        
        //Preenche combobox com as mesas vazias
        for(int i = 0; i < mesas.size(); i++){
            for(int j = 0; j < pedidos.size(); j++){
                if (pedidos.get(i).VerificaMesa(mesas.get(i))){
                    achouMesa = true;
                }    
            } 
            if (achouMesa == false){
                cboMesas.addItem(mesas.get(i).getDescricao());
            }
            achouMesa = false;
        }
        
        //Componentes
            txtCodProduto.setEnabled(false);
            txtTotal.setEnabled(false); 
            pnlComponentes.add(cboMesas);
            
            pnlComponentes.add(txtCodProduto);
            pnlComponentes.add(lblProduto);
            pnlComponentes.add(txtProduto);
            pnlComponentes.add(lblVlrUnit);
            pnlComponentes.add(txtVlrUnit);
            pnlComponentes.add(lblQuantidade);
            pnlComponentes.add(txtQuantidade);
            pnlComponentes.add(lblTotal);
            pnlComponentes.add(txtTotal);
            
            pnlBotoesProduto.add(btnAdd);
            pnlBotoesProduto.add(btnExcluir);
            
            pnlComponentes.add(pnlBotoesProduto);
        //
        
        pnlDireita.add(pnlComponentes, BorderLayout.NORTH);
        pnlDireita.add(new JScrollPane(lstProdutos), BorderLayout.CENTER);
        pnlDireita.add(new JScrollPane(lstMoviPedidos), BorderLayout.SOUTH);
        pnlEsquerda.add(new JScrollPane(lstPedidos));  
        
        pnlBotoes.add(btnNovo);
        pnlBotoes.add(btnGravar);
        
        
        pnlPrincipal.add(pnlDireita, BorderLayout.CENTER);
        pnlPrincipal.add(pnlEsquerda, BorderLayout.WEST);
        pnlPrincipal.add(pnlBotoes, BorderLayout.SOUTH);
        
        
        add(pnlPrincipal);
        
        //Funcionamento dos botões
        btnNovo.addActionListener(new onClickBotao());
        btnGravar.addActionListener(new onClickBotao());
        
        //Double click na lista de pedidos em aberto
        lstPedidos.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    Pedido mesaSelected = lstPedidos.getSelectedValue();                    
                    lstMoviPedidos.setModel(new MoviPedidosListModel(mesaSelected.getMovimento())); 
                    cboMesas.setEnabled(false); 
                }
             }
         });

        //Double click na lista de produtos
        lstProdutos.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    Produtos produtoSelected = lstProdutos.getSelectedValue();                    
                    txtCodProduto.setText(produtoSelected.getCodigo() + ""); 
                    txtProduto.setText(produtoSelected.getDescricao()); 
                    txtVlrUnit.setText(produtoSelected.getVlrUunitario()+ ""); 
                    
                    txtQuantidade.setText("");
                    txtTotal.setText("");
                    txtQuantidade.grabFocus();
                }
             }
         }); 
        
        //Enter no textbox quantidaede
        txtQuantidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!txtVlrUnit.getText().isEmpty()) && (!txtQuantidade.getText().isEmpty())) {
                    txtTotal.setText(Float.parseFloat(txtVlrUnit.getText()) * Float.parseFloat(txtQuantidade.getText()) + "");
                }                
            }
        });
    }
    
    private class onClickBotao implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {  
            if(e.getSource()==btnNovo){
                cboMesas.setEnabled(true); 
            }else if(e.getSource()==btnGravar){                
                               
            }                
        }
    }
    
}
