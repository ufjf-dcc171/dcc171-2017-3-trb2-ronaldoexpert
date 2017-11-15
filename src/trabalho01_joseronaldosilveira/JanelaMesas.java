package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class JanelaMesas extends JFrame {
    private final JPanel painel = new JPanel();
    private final JPanel pnlTexts = new JPanel();
    
    private final List<Mesas> mesas;
    private final List<Pedido> pedidos;
    private final JList<Mesas> lstMesas = new JList<Mesas>(new DefaultListModel<>()); 
    
    JanelaPrincipal p = new JanelaPrincipal();
    ImageIcon IconNovo = new ImageIcon("novo.gif");
    ImageIcon IconGravar = new ImageIcon("gravar.gif");
    ImageIcon IconExcluir = new ImageIcon("excluir.gif");
    
    private final JTextField txtCodMesa = new JTextField(20);
    private final JLabel lblCodMesa = new JLabel("Código");
    private final JTextField txtDescMesa = new JTextField(20);
    private final JLabel lblDescMesa = new JLabel("Descrição");
    private final JButton btnNovo = new JButton("Novo", IconNovo);
    private final JButton btnGravar = new JButton("Gravar", IconGravar);
    private final JButton btnExcluir = new JButton("Excluir", IconExcluir);
    
    private String vStatus = "";
    
    public JanelaMesas(List<Mesas> sampleData, List<Pedido> pedidos) throws HeadlessException {
        super("Mesas");
        
        setLayout(new BorderLayout());
        painel.setLayout(new BorderLayout());
        pnlTexts.setLayout(new GridLayout(11, 1));
        
        this.mesas = sampleData; 
        this.pedidos = pedidos;
        //Le Array e coloca na grid
        lstMesas.setModel(new MesasListModel(mesas));               
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pnlTexts.setBackground(Color.white);
        
        
        painel.add(new JScrollPane(lstMesas));         
        
        pnlTexts.add(lblCodMesa);
        pnlTexts.add(txtCodMesa);
        pnlTexts.add(lblDescMesa);
        pnlTexts.add(txtDescMesa);
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
        lstMesas.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    Mesas mesaSelected = lstMesas.getSelectedValue();
                    txtCodMesa.setText(mesaSelected.getCodigo() + "");
                    txtDescMesa.setText(mesaSelected.getDescricao());
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
                txtCodMesa.grabFocus();
                btnNovo.setEnabled(false);
                btnGravar.setEnabled(true);
                vStatus = "Novo";
            
            }else if(e.getSource()==btnGravar){                
                if ((!txtCodMesa.getText().isEmpty()) && (!txtDescMesa.getText().isEmpty())){
                    if (vStatus != "Update"){
                        Mesas m = new Mesas(Integer.parseInt(txtCodMesa.getText()), txtDescMesa.getText());
                        mesas.add(m);
                        
                        p.setMesas(mesas); 
                    }else{
                        Mesas m = lstMesas.getSelectedValue();
                        m.setCodigo(Integer.parseInt(txtCodMesa.getText()));
                        m.setDescricao(txtDescMesa.getText());
                        
                        p.setMesas(mesas);
                    }
                    vStatus = "";                    
                    lstMesas.updateUI();                    
                    txtCodMesa.grabFocus();
                    btnNovo.setEnabled(true);
                    btnGravar.setEnabled(false);
                    LimpaCampos(); 
                    try {
                        gravaArquivoTXT(mesas);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaProdutos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }else if(e.getSource()==btnExcluir){  
                if (lstMesas.isSelectionEmpty() == false){ 
                    Mesas mesaSelected = lstMesas.getSelectedValue();
                    if (verificaMesas(mesaSelected.getCodigo())){
                        mesas.remove(mesaSelected);
                        lstMesas.updateUI();
                        lstMesas.isSelectionEmpty();
                        btnGravar.setEnabled(false); 
                        LimpaCampos();
                        try {
                        gravaArquivoTXT(mesas);
                        } catch (IOException ex) {
                            Logger.getLogger(JanelaProdutos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "A mesa está aberta em um pedido. Não pode ser excluida.");
                    }     
                }              
            }                
        }
    }
    
    private void LimpaCampos(){
        txtCodMesa.setText("");
        txtDescMesa.setText("");
    }
    
    private boolean verificaMesas(int codMesa){
        boolean retorno = true;
        for (int i =0; i < pedidos.size(); i++){
            if (codMesa == pedidos.get(i).getIdMesa().getCodigo()){
                retorno = false;
            }
        }    
        return retorno;
    }
    
    private void gravaArquivoTXT(List<Mesas> mesas) throws IOException{
        int i;
        
        File file = new File("mesas.txt");
        file.delete();
        
        FileWriter arq = new FileWriter("mesas.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        for (i=0; i < mesas.size(); i++) {
          gravarArq.println(
          mesas.get(i).getCodigo() + "," +  mesas.get(i).getDescricao());
        }

        arq.close();
    }
}
