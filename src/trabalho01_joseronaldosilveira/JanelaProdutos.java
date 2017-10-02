package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JanelaProdutos extends JFrame{
    private final List<Produtos> produtos;
    private final List<MoviPedidos> moviPed;
    private final JList<Produtos> lstProdutos = new JList<Produtos>(new DefaultListModel<>()); 
    
    private final JPanel painel = new JPanel();
    private final JPanel pnlTexts = new JPanel();
    private JanelaPrincipal p = new JanelaPrincipal();
    
    private final JTextField txtCodProduto = new JTextField(20);
    private final JLabel lblCodProduto = new JLabel("Código");
    private final JTextField txtDescProduto = new JTextField(20);
    private final JLabel lblDescProduto = new JLabel("Descrição");
    private final JTextField txtVlrUnitario = new JTextField(20);
    private final JLabel lblVlrUnitario = new JLabel("Vlr. Unitário");
    private final JButton btnNovo = new JButton("Novo");
    private final JButton btnGravar = new JButton("Gravar");
    private final JButton btnExcluir = new JButton("Excluir");
    private String vStatus = "";    
    
    
    public JanelaProdutos(List<Produtos> produtos, List<MoviPedidos> moviPed) throws HeadlessException {
        super("Produtos");
        
        setLayout(new BorderLayout());
        painel.setLayout(new BorderLayout());
        pnlTexts.setLayout(new GridLayout(11, 1));
        
        this.produtos = produtos;
        this.moviPed = moviPed;
        
        lstProdutos.setModel(new ProdutosListModel(produtos)); 
        pnlTexts.setBackground(Color.white);
        
        painel.add(new JScrollPane(lstProdutos));
        
        pnlTexts.add(lblCodProduto);
        pnlTexts.add(txtCodProduto);
        pnlTexts.add(lblDescProduto);
        pnlTexts.add(txtDescProduto);
        pnlTexts.add(lblVlrUnitario);
        pnlTexts.add(txtVlrUnitario);
        
        pnlTexts.add(btnNovo);
        pnlTexts.add(btnGravar);
        pnlTexts.add(btnExcluir);
        
        add(painel, BorderLayout.CENTER);
        add(pnlTexts, BorderLayout.WEST);
        
        btnGravar.setEnabled(false);
        
        
        //Funcionamento dos botões
        btnNovo.addActionListener(new onClickBotao());
        btnGravar.addActionListener(new onClickBotao());
        btnExcluir.addActionListener(new onClickBotao());
        
        //Double click na lista
        lstProdutos.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    Produtos produtoSelected = lstProdutos.getSelectedValue();
                    txtCodProduto.setText(produtoSelected.getCodigo() + "");
                    txtDescProduto.setText(produtoSelected.getDescricao());
                    txtVlrUnitario.setText(produtoSelected.getVlrUunitario() + "");
                    btnGravar.setEnabled(true);
                    vStatus = "Update";
                }
             }
         });
    }
    
    private class onClickBotao implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {  
            if(e.getSource()==btnNovo){
                LimpaCampos();
                txtCodProduto.grabFocus();
                btnNovo.setEnabled(false);
                btnGravar.setEnabled(true);
                vStatus = "Novo";
            
            }else if(e.getSource()==btnGravar){                
                if ((!txtCodProduto.getText().isEmpty()) && (!txtDescProduto.getText().isEmpty()) && (!txtVlrUnitario.getText().isEmpty())){
                    if (vStatus != "Update"){
                        Produtos m = new Produtos(Integer.parseInt(txtCodProduto.getText()), txtDescProduto.getText(), Float.parseFloat(txtVlrUnitario.getText()));
                        produtos.add(m);
                        p.setProdutos(produtos);
                        
                    }else{
                        Produtos m = lstProdutos.getSelectedValue();
                        m.setCodigo(Integer.parseInt(txtCodProduto.getText()));
                        m.setDescricao(txtDescProduto.getText());
                        m.setVlrUunitario(Float.parseFloat(txtVlrUnitario.getText()));
                        p.setProdutos(produtos);
                    }
                    
                    vStatus = "";                    
                    lstProdutos.updateUI();                    
                    txtCodProduto.grabFocus();
                    btnNovo.setEnabled(true);
                    btnGravar.setEnabled(false);
                    LimpaCampos();
                }               
            }else if(e.getSource()==btnExcluir){ 
                if (lstProdutos.isSelectionEmpty() == false){ 
                    Produtos prodSelected = lstProdutos.getSelectedValue();
                    if (verificaMoviPedidos(prodSelected.getCodigo())){                    
                        produtos.remove(prodSelected);
                        lstProdutos.updateUI();
                        lstProdutos.isSelectionEmpty();
                        btnGravar.setEnabled(false); 
                        LimpaCampos();  
                    }else{
                        JOptionPane.showMessageDialog(null, "O produto faz parte de um pedido. Não pode ser excluido.");
                    }
                } 
            }                
        }
    }
    
    private void LimpaCampos(){
        txtCodProduto.setText("");
        txtDescProduto.setText("");
        txtVlrUnitario.setText("");
    }
    
    private boolean verificaMoviPedidos(int codProduto){
        boolean retorno = true;
        for (int i =0; i < moviPed.size(); i++){
            if (codProduto == moviPed.get(i).getCodProduto().getCodigo()){
                retorno = false;
            }
        }    
        return retorno;
    }
    
    
}
