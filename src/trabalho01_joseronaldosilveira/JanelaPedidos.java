
package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.util.List;
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
import javax.swing.text.Document;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class JanelaPedidos extends JFrame{
    private final List<Mesas> mesas;
    private final JList<Mesas> lstMesas = new JList<Mesas>(new DefaultListModel<>()); 
    
    private final List<Produtos> produtos;
    private final JList<Produtos> lstProdutos = new JList<Produtos>(new DefaultListModel<>());
    
    private final List<Pedido> pedidos;
    private final JList<Pedido> lstPedidos = new JList<Pedido>(new DefaultListModel<>());
    
    private final List<MoviPedidos> moviPedidos;
    private final JList<MoviPedidos> lstMoviPedidos = new JList<MoviPedidos>(new DefaultListModel<>());
    private final List<MoviPedidos> lstMovi = new ArrayList<MoviPedidos>();
    
    private final JList<HistoricoDiario> lstHistorico = new JList<HistoricoDiario>(new DefaultListModel<>());

    
    private final JPanel pnlDireita = new JPanel();
    private final JPanel pnlEsquerda = new JPanel();
    private final JPanel pnlPrincipal = new JPanel();
    private final JPanel pnlBotoes = new JPanel();
    private final JPanel pnlComponentes = new JPanel(); 
    private final JPanel pnlBotoesProduto = new JPanel(); 
    
    private final JButton btnNovo = new JButton("Novo");
    private final JButton btnGravar = new JButton("Gravar");
    private final JButton btnCancelar = new JButton("Cancelar");
    
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
    private final JTextField txtResponsavel = new JTextField();
    private final JLabel lblResponsavel = new JLabel("Responsável");
    
    private final JButton btnAdd = new JButton("ADD");
    private final JButton btnExcluir = new JButton("Excluir");
    private final JButton btnFecharMesa = new JButton("Fechar Mesa");
    
    private boolean achouMesa = false;
    private boolean vNovoPedido = false;
    private boolean vNovoProduto = false;
    
    public JanelaPedidos(List<Mesas> mesas, List<Produtos> produtos, List<Pedido> pedidos, List<MoviPedidos> moviPedidos) {
        super("Pedidos");
        
        //Define os paineis
        setLayout(new BorderLayout());
        pnlDireita.setLayout(new BorderLayout());
        pnlEsquerda.setLayout(new GridLayout(1, 1));
        pnlPrincipal.setLayout(new BorderLayout());
        pnlBotoes.setLayout(new GridLayout(1, 2));
        pnlComponentes.setLayout(new GridLayout(12, 1));
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
        preencheComboBox();
        
        //Componentes
            txtCodProduto.setEnabled(false);
            txtTotal.setEnabled(false); 
            btnFecharMesa.setEnabled(false); 
            pnlComponentes.add(cboMesas);
            
            pnlComponentes.add(lblResponsavel);
            pnlComponentes.add(txtResponsavel);
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
            pnlBotoesProduto.add(btnFecharMesa);            
            
            pnlComponentes.add(pnlBotoesProduto);
        //
        
        pnlDireita.add(pnlComponentes, BorderLayout.CENTER);
        pnlDireita.add(new JScrollPane(lstProdutos), BorderLayout.NORTH);
        pnlDireita.add(new JScrollPane(lstMoviPedidos), BorderLayout.SOUTH);
        pnlEsquerda.add(new JScrollPane(lstPedidos));  
        
        pnlBotoes.add(btnNovo);
        pnlBotoes.add(btnGravar);
        pnlBotoes.add(btnCancelar);
        
        pnlPrincipal.add(pnlDireita, BorderLayout.CENTER);
        pnlPrincipal.add(pnlEsquerda, BorderLayout.WEST);
        pnlPrincipal.add(pnlBotoes, BorderLayout.SOUTH);
        
        
        add(pnlPrincipal);
        
        //Funcionamento dos botões
        btnNovo.addActionListener(new onClickBotao());
        btnGravar.addActionListener(new onClickBotao());
        btnCancelar.addActionListener(new onClickBotao());
        btnAdd.addActionListener(new onClickBotao());
        btnExcluir.addActionListener(new onClickBotao());
        btnFecharMesa.addActionListener(new onClickBotao());
        
        //Double click na lista de pedidos em aberto
        lstPedidos.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    Pedido pedidoSelected = lstPedidos.getSelectedValue();                    
                    if(pedidoSelected.getMovimento() != null){
                        lstMoviPedidos.setModel(new MoviPedidosListModel(pedidoSelected.getMovimento()));
                    }            
                    txtResponsavel.setText(pedidoSelected.getResponsavel()); 
                    cboMesas.setEnabled(false); 
                    lstPedidos.setEnabled(false);
                    btnFecharMesa.setEnabled(true);
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
                    vNovoProduto = true;
                }
             }
         });
        
        //Double click na lista de movimento do pedido
        lstMoviPedidos.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    MoviPedidos produtoSelected = lstMoviPedidos.getSelectedValue();                    
                    txtCodProduto.setText(produtoSelected.getCodProduto() + ""); 
                    txtProduto.setText(produtoSelected.getCodProduto().getDescricao()); 
                    txtVlrUnit.setText(produtoSelected.getVlrUnitario() + ""); 
                    txtQuantidade.setText(produtoSelected.getQuatidade() + "");
                    txtTotal.setText(produtoSelected.getVlrTotal() + ""); 
                    txtQuantidade.grabFocus();
                    vNovoProduto = false;
                    //lstMoviPedidos.setEnabled(false); 
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
        
        //Enter no codigo do produto
        txtProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codProduto = 0;
                if (!txtProduto.getText().isEmpty()){
                    codProduto = Integer.parseInt(txtProduto.getText());
                    for (int i = 0; i < produtos.size(); i++){
                        if (codProduto == produtos.get(i).getCodigo()) {
                            txtCodProduto.setText(codProduto + ""); 
                            txtProduto.setText(produtos.get(i).getDescricao()); 
                            txtVlrUnit.setText(produtos.get(i).getVlrUunitario()+ ""); 
                                
                            txtQuantidade.setText("");
                            txtTotal.setText("");
                            txtQuantidade.grabFocus();
                            lstProdutos.setSelectedIndex(i); 
                            vNovoProduto = true;
                        }
                    }
                }                
            }
        });
    }
    
    private class onClickBotao implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {  
            float vlrTotal = 0;
            String idPedido = "";
            int count = 0;
            
            if(e.getSource() == btnNovo){     //Novo pedido                 
                cboMesas.setEnabled(true); 
                btnNovo.setEnabled(false);
                lstPedidos.setEnabled(false);
                vNovoPedido = true;
                txtProduto.grabFocus();
                
                Pedido p = new Pedido();
                for (int i = 0; i < pedidos.size(); i++){
                    idPedido = pedidos.get(i).getNumero();
                    count++;
                }                  
                idPedido = Integer.parseInt(idPedido) + 1 + "";

                for (int j = 0; j < mesas.size(); j++){
                    if (mesas.get(j).getDescricao() == cboMesas.getSelectedItem().toString()){
                        p.setIdMesa(mesas.get(j));
                    }
                }                    

                p.setData("25/09/2017");
                p.setNumero(idPedido);
                p.setResponsavel(txtResponsavel.getText());
                p.setTotal(0);
                pedidos.add(p);
                lstPedidos.updateUI();
                lstPedidos.setSelectedIndex(count);
                txtResponsavel.grabFocus();
                
            }else if(e.getSource() == btnGravar){     //Grava o pedido inteiro
                btnNovo.setEnabled(true);
                lstPedidos.setEnabled(true);
                
                Pedido pedidoMovi = lstPedidos.getSelectedValue();                
                for(int i = 0; i < pedidoMovi.getMovimento().size(); i++){
                    vlrTotal = vlrTotal + pedidoMovi.getMovimento().get(i).getVlrTotal();
                }
                pedidoMovi.setTotal(vlrTotal);
                pedidoMovi.setResponsavel(txtResponsavel.getText());
                
                if (vNovoPedido == true){                                     
                    preencheComboBox();
                }
                
                lstPedidos.updateUI();
                lstPedidos.clearSelection(); 
                lstMoviPedidos.setModel(new DefaultListModel());
                vNovoPedido = false;
                txtResponsavel.setText(""); 
                
            }else if(e.getSource()==btnCancelar){ 
                if(vNovoPedido == true){
                    Pedido pedidoSelected = lstPedidos.getSelectedValue();
                    lstMoviPedidos.setModel(new DefaultListModel());
                    pedidos.remove(pedidoSelected);
                    lstPedidos.updateUI();
                    lstPedidos.setEnabled(true);
                }
                LimpaProdutos();
                btnNovo.setEnabled(true);
                lstPedidos.setEnabled(true);  
                lstMoviPedidos.setModel(new DefaultListModel());
                vNovoPedido = false;
            
            }else if(e.getSource() == btnAdd){        //insere ou altera um produto
               Pedido pedidoSelected = lstPedidos.getSelectedValue();
               float vTotal = 0;
                if (ValidaCampos()){
                    if (vNovoProduto == false){
                        //Se estiver editando um pedido
                        MoviPedidos moviSelected = lstMoviPedidos.getSelectedValue();
                        moviSelected.setQuatidade(Integer.parseInt(txtQuantidade.getText()));
                        moviSelected.setVlrUnitario(Float.parseFloat(txtVlrUnit.getText()));
                        moviSelected.setVlrTotal(Float.parseFloat(txtQuantidade.getText()) * (Float.parseFloat(txtVlrUnit.getText())));
                    }else{                        
                        vTotal = Float.parseFloat(txtQuantidade.getText()) * (Float.parseFloat(txtVlrUnit.getText()));
                        MoviPedidos mp = new MoviPedidos(lstProdutos.getSelectedValue(), Integer.parseInt(txtQuantidade.getText()), Float.parseFloat(txtVlrUnit.getText()), vTotal);
                        if (vNovoPedido == true){                            
                            lstMovi.add(mp);
                            pedidoSelected.setMovimento(lstMovi);
                        }else{
                            pedidoSelected.getMovimento().add(mp);
                        }
                    }
                    
                    lstMoviPedidos.setModel(new MoviPedidosListModel(pedidoSelected.getMovimento()));
                    lstMoviPedidos.updateUI();
                    lstMoviPedidos.setEnabled(true);
                    LimpaProdutos();    
               }
            
            }else if(e.getSource() == btnExcluir){ 
                if (!lstMoviPedidos.isEnabled() == false){ 
                    Pedido pedidoSelected = lstPedidos.getSelectedValue();
                    pedidoSelected.getMovimento().remove(lstMoviPedidos.getSelectedValue());
                    lstMoviPedidos.updateUI();
                    LimpaProdutos();      
                }                
                
            }else if (e.getSource() == btnFecharMesa){
                if (!lstPedidos.isSelectionEmpty()){  
                    String message = "Deseja realmente fechar a mesa?";
                    String title = "Fechar Mesa";
                    String qtdPessoas;
        
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        qtdPessoas = JOptionPane.showInputDialog("Dividir por quantas pessoas?", "1");
                        
                        LimpaProdutos();
                        Pedido pedidoSelected = lstPedidos.getSelectedValue();
                        JanelaFechaMesa janela = new JanelaFechaMesa(pedidoSelected.getMovimento(), Integer.parseInt(qtdPessoas));
                        
                        janela.setSize(250,300);
                        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        janela.setLocationRelativeTo(null); 
                        janela.setVisible(true);  
                        janela.setTitle("Mesa Fechada");
                        pedidos.remove(pedidoSelected); 
                        lstPedidos.updateUI();
                        lstPedidos.setEnabled(true);
                        cboMesas.setEnabled(true);
                        preencheComboBox();
                        
                        lstMoviPedidos.setModel(new DefaultListModel());
                    }
                }
            }                
        }
    }
    
    private void LimpaProdutos(){
        txtCodProduto.setText("");
        txtProduto.setText("");
        txtVlrUnit.setText("");
        txtQuantidade.setText("");
        txtTotal.setText("");
    }
    
    private boolean ValidaCampos(){
        boolean retorno = true;
        float vTotal = 0;
        if (txtCodProduto.getText() == ""){
            retorno = false;
        }
        if (txtQuantidade.getText() == ""){
            retorno = false;
        }
        if (txtVlrUnit.getText() == ""){
            retorno = false;
        }
        if (txtTotal.getText() == ""){
            retorno = false;
        }
        
        vTotal = Float.parseFloat(txtQuantidade.getText()) * Float.parseFloat(txtVlrUnit.getText());
        
        if (vTotal != Float.parseFloat(txtTotal.getText())){
            retorno = false;
        }
        return retorno;
    }   
    
    private void preencheComboBox(){
        cboMesas.removeAllItems();
        for(int i = 0; i < mesas.size(); i++){
            for(int j = 0; j < pedidos.size(); j++){
                if (pedidos.get(j).VerificaMesa(mesas.get(i))){
                    achouMesa = true;
                }    
            } 
            if (achouMesa == false){
                cboMesas.addItem(mesas.get(i).getDescricao());
            }
            achouMesa = false;
        }
    }
    
}
