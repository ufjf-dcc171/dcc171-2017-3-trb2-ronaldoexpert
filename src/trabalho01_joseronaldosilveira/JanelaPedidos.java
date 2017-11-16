
package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
    private final List<MoviPedidos> lstMoviFechaMesa = new ArrayList<MoviPedidos>();
    
    private final JList<HistoricoDiario> lstHistorico = new JList<HistoricoDiario>(new DefaultListModel<>());
    private JanelaPrincipal janelaPrinci = new JanelaPrincipal();
    
    private final JPanel pnlDireita = new JPanel();
    private final JPanel pnlEsquerda = new JPanel();
    private final JPanel pnlPrincipal = new JPanel();
    private final JPanel pnlBotoes = new JPanel();
    private final JPanel pnlComponentes = new JPanel(); 
    private final JPanel pnlBotoesProduto = new JPanel(); 
    private final JPanel pnlDiretaBaixo = new JPanel(); 
    
    ImageIcon IconNovo = new ImageIcon("novo.gif");
    ImageIcon IconGravar = new ImageIcon("gravar.gif");
    ImageIcon IconExcluir = new ImageIcon("excluir.gif");
    ImageIcon IconCancelar = new ImageIcon("cancelar.gif");
    ImageIcon IconFechaMesa = new ImageIcon("fechaMesa.gif");
    
    private final JButton btnNovo = new JButton("Novo", IconNovo);
    private final JButton btnGravar = new JButton("Gravar", IconGravar);
    private final JButton btnCancelar = new JButton("Cancelar", IconCancelar);
    
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
    private final JTextField txtTotalMesa = new JTextField();
    private final JLabel lblTotalMesa = new JLabel("Total Mesa:");
    private JTextField txtData = new JTextField();
    
    private final JButton btnAdd = new JButton("ADD", IconGravar);
    private final JButton btnExcluir = new JButton("Excluir", IconExcluir);
    private final JButton btnFecharMesa = new JButton("Fechar Mesa", IconFechaMesa);
    
    private boolean achouMesa = false;
    private boolean vNovoPedido = false;
    private boolean vNovoProduto = false;
    private Date dataAtaual;
    
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
        pnlDiretaBaixo.setLayout(new BorderLayout());
        
        pnlDiretaBaixo.setBackground(Color.white);
        pnlComponentes.setBackground(Color.white);
        pnlBotoesProduto.setBackground(Color.white);
        cboMesas.setBackground(Color.white);
        txtTotalMesa.setBackground(Color.white);
        
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
            btnGravar.setEnabled(false); 
            txtTotalMesa.setFont(new Font("Times New Roman", Font.BOLD, 22));  
            txtTotalMesa.setEditable(false);
            txtTotalMesa.setText("R$ 0,00");
            txtTotal.setText("R$ 0,00");
            pnlComponentes.add(cboMesas);
            txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            btnFecharMesa.setEnabled(false);
            
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
        
        pnlDiretaBaixo.add(new JScrollPane(lstMoviPedidos), BorderLayout.NORTH);
        pnlDiretaBaixo.add(lblTotalMesa, BorderLayout.CENTER);
        pnlDiretaBaixo.add(txtTotalMesa, BorderLayout.SOUTH);
        
        pnlDireita.add(pnlDiretaBaixo, BorderLayout.SOUTH);
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
                    btnGravar.setEnabled(true);
                    btnFecharMesa.setEnabled(true);
                    txtTotalMesa.setText("R$ " + CalculaTotalMesa());
                }
             } 
         });

        //Double click na lista de produtos
        lstProdutos.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    LimpaProdutos();
                    Produtos produtoSelected = lstProdutos.getSelectedValue();                    
                    txtCodProduto.setText(produtoSelected.getCodigo() + ""); 
                    txtProduto.setText(produtoSelected.getDescricao()); 
                    txtVlrUnit.setText(produtoSelected.getVlrUunitario()+ ""); 
                    
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
                    LimpaProdutos();
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
            float vlrTotalMesa = 0;
            String idPedido = "";
            int count = 0;
            
            if(e.getSource() == btnNovo){     //Novo pedido                 
                cboMesas.setEnabled(true); 
                btnNovo.setEnabled(false);
                btnGravar.setEnabled(true);
                lstPedidos.setEnabled(false);
                btnFecharMesa.setEnabled(false); 
                vNovoPedido = true;
                txtProduto.grabFocus();
                txtResponsavel.setText(""); 
                txtTotalMesa.setText("R$ 0,00");
                txtTotal.setText("R$ 0,00");
                
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
    		
                p.setData(txtData.getText());
                p.setNumero(idPedido); 
                p.setResponsavel(txtResponsavel.getText());
                p.setTotal(0);
                pedidos.add(p);
                janelaPrinci.setPedidos(pedidos);
                
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
                btnGravar.setEnabled(false);
                txtResponsavel.setText(""); 
                txtTotalMesa.setText("R$ 0,00");
                
                try {
                    gravaArquivoTXTPedido(pedidos);
                    gravaArquivoTXTMoviPedido(pedidos);
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPedidos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else if(e.getSource()==btnCancelar){ 
                if(vNovoPedido == true){
                    Pedido pedidoSelected = lstPedidos.getSelectedValue();
                    lstMoviPedidos.setModel(new DefaultListModel());
                    pedidos.remove(pedidoSelected);
                    janelaPrinci.getPedidos().remove(pedidoSelected);
                    lstPedidos.updateUI();
                    lstPedidos.setEnabled(true);
                    btnGravar.setEnabled(false);
                }
                LimpaProdutos();
                btnNovo.setEnabled(true);
                lstPedidos.setEnabled(true);  
                lstMoviPedidos.setModel(new DefaultListModel());
                txtTotal.setText("R$ 0,00");
                txtTotalMesa.setText("R$ 0,00");
                vNovoPedido = false;
                btnFecharMesa.setEnabled(false);
            
            }else if(e.getSource() == btnAdd){        //insere ou altera um produto
               Pedido pedidoSelected = lstPedidos.getSelectedValue();
               float vTotal = 0;
                if (ValidaCampos()){
                    if (vNovoProduto == false){
                        //Se estiver editando um pedido
                        MoviPedidos moviSelected = lstMoviPedidos.getSelectedValue();
                        moviSelected.setNumPedido(pedidoSelected.getNumero());
                        moviSelected.setQuatidade(Integer.parseInt(txtQuantidade.getText()));
                        moviSelected.setVlrUnitario(Float.parseFloat(txtVlrUnit.getText()));
                        moviSelected.setVlrTotal(Float.parseFloat(txtQuantidade.getText()) * (Float.parseFloat(txtVlrUnit.getText())));
                    }else{                        
                        vTotal = Float.parseFloat(txtQuantidade.getText()) * (Float.parseFloat(txtVlrUnit.getText()));
                        MoviPedidos mp = new MoviPedidos(lstProdutos.getSelectedValue(), Integer.parseInt(txtQuantidade.getText()), Float.parseFloat(txtVlrUnit.getText()), vTotal);
                        mp.setNumPedido(pedidoSelected.getNumero());
                        if (vNovoPedido == true){                            
                            lstMovi.add(mp);
                            pedidoSelected.setMovimento(lstMovi);
                        }else{
                            pedidoSelected.getMovimento().add(mp);
                        }
                    }
                    janelaPrinci.setMoviPedidos(moviPedidos);
                    txtTotalMesa.setText("R$ " + CalculaTotalMesa());
                    txtTotal.setText("R$ 0,00");
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
                    
                    txtTotalMesa.setText("R$ " + CalculaTotalMesa());
                    LimpaProdutos();      
                }                
                
            }else if (e.getSource() == btnFecharMesa){
                if (!lstPedidos.isSelectionEmpty()){  
                    String message = "Deseja realmente fechar a mesa?";
                    String title = "Fechar Mesa";
                    String qtdPessoas;
                    int indexPedido = 0;
                    
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        qtdPessoas = JOptionPane.showInputDialog("Dividir por quantas pessoas?", "1");
                        
                        LimpaProdutos();
                        Pedido pedidoSelected = lstPedidos.getSelectedValue();
                        indexPedido = lstPedidos.getSelectedIndex();
                        
                        if (lstMoviFechaMesa.size() > 0) {
                            lstMoviFechaMesa.remove(lstMoviFechaMesa.get(0));
                        }
                        while(pedidoSelected.getMovimento().size() > 0){
                            lstMoviFechaMesa.add(pedidoSelected.getMovimento().get(0));
                            pedidoSelected.getMovimento().remove(pedidoSelected.getMovimento().get(0)); 
                        }
                        
                        
                        JanelaFechaMesa janela = new JanelaFechaMesa(lstMoviFechaMesa, Integer.parseInt(qtdPessoas), pedidoSelected); 
                        
                        janela.setSize(250,300);
                        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        janela.setLocationRelativeTo(null); 
                        janela.setVisible(true);  
                        janela.setTitle("Mesa Fechada");
                        
                        pedidos.remove(pedidoSelected); 
                        lstPedidos.updateUI();
                        lstPedidos.setEnabled(true);
                        cboMesas.setEnabled(true);
                        btnGravar.setEnabled(false);
                        preencheComboBox();
                        txtTotalMesa.setText("R$ 0,00"); 
                        txtResponsavel.setText("");
                        lstMoviPedidos.setModel(new DefaultListModel());
                        
                        try {
                            gravaArquivoTXTPedido(pedidos);
                            gravaArquivoTXTMoviPedido(pedidos);
                        } catch (IOException ex) {
                            Logger.getLogger(JanelaPedidos.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
        txtTotal.setText("R$ 0,00");
    }
    
    private boolean ValidaCampos(){
        boolean retorno = true;
        
        if (txtQuantidade.getText().isEmpty()){
            retorno = false;
            txtQuantidade.grabFocus();
        }
        if (txtVlrUnit.getText().isEmpty()){
            retorno = false;
            txtVlrUnit.grabFocus();
        }
        if (txtTotal.getText().isEmpty()){
            retorno = false;
            txtQuantidade.grabFocus();
        }
        
        if (txtTotal.getText() == "R$ "){
            retorno = false;
            txtQuantidade.grabFocus();
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
    
    private float CalculaTotalMesa(){
        float vlrTotal = 0;
        Pedido pedidoMovi = lstPedidos.getSelectedValue();                
        for(int i = 0; i < pedidoMovi.getMovimento().size(); i++){
            vlrTotal = vlrTotal + pedidoMovi.getMovimento().get(i).getVlrTotal();
        }
        return vlrTotal;
    }
    
    private void gravaArquivoTXTPedido(List<Pedido> lstPedidos) throws IOException{
        int i;
        
        File file = new File("pedidos.txt");
        file.delete();
        
        FileWriter arq = new FileWriter("pedidos.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        
        for (i=0; i < lstPedidos.size(); i++) {
            gravarArq.println(
                lstPedidos.get(i).getNumero() + "," + lstPedidos.get(i).getData() + "," + 
                lstPedidos.get(i).getTotal()+ "," + lstPedidos.get(i).getIdMesa().getCodigo() + "," + 
                lstPedidos.get(i).getResponsavel());
        }
        arq.close();
    }
    
    private void gravaArquivoTXTMoviPedido(List<Pedido> moviPedidos) throws IOException{
        int i;
        
        File file = new File("moviPedidos.txt");
        file.delete();
        
        FileWriter arq = new FileWriter("moviPedidos.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        
        for (i=0; i < moviPedidos.size(); i++) {
            for(int j = 0; j < moviPedidos.get(i).getMovimento().size(); j++){
                gravarArq.println(
                    moviPedidos.get(i).getMovimento().get(j).getNumPedido() + "," + moviPedidos.get(i).getMovimento().get(j).getCodProduto().getCodigo() + "," + 
                    moviPedidos.get(i).getMovimento().get(j).getQuatidade() + "," + moviPedidos.get(i).getMovimento().get(j).getVlrUnitario() + "," + 
                    moviPedidos.get(i).getMovimento().get(j).getVlrTotal());    
            }
        }
        arq.close();
    } 
    
}
