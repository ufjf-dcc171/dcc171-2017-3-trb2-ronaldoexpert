package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaPrincipal extends JFrame{
    private final JButton btnAbrePedidos = new JButton("Pedidos");
    private final JButton btnCadastroProduto = new JButton("Produtos");
    private final JButton btnCadastroMesa = new JButton("Mesas");
    
    public JanelaPrincipal() throws HeadlessException {
        super("Principal");
        setLayout(null);
        
        btnAbrePedidos.setSize(150, 50);
        btnAbrePedidos.setLocation(70, 50); //(Horizontal / Vertical)
        
        btnCadastroProduto.setSize(150, 50);
        btnCadastroProduto.setLocation(70, 130); //(Horizontal / Vertical)
        
        btnCadastroMesa.setSize(150, 50);
        btnCadastroMesa.setLocation(70, 210); //(Horizontal / Vertical)
        
        add(btnAbrePedidos);
        add(btnCadastroProduto);
        add(btnCadastroMesa);
        
        btnAbrePedidos.addActionListener(new onClickBotao());
        btnCadastroProduto.addActionListener(new onClickBotao());
        btnCadastroMesa.addActionListener(new onClickBotao());
    }
    
    
    private class onClickBotao implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAbrePedidos){
                JanelaPedidos janela = new JanelaPedidos(getSampleData());
                janela.setSize(500, 400);
                janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);  
                janela.setTitle("Pedidos");    
            
            }else if(e.getSource() == btnCadastroMesa){
                JanelaMesas janela = new JanelaMesas();
                janela.setSize(500, 400);
                janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);  
                janela.setTitle("Cadastro de Mesas");    
            
            }else if(e.getSource() == btnCadastroProduto){
                JanelaProdutos janela = new JanelaProdutos();
                janela.setSize(500, 400);
                janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);  
                janela.setTitle("Cadastro de Produtos");    
            }              
        }    
    }
    
            
    private static List<Pedido> getSampleData() {
        //Cria a mesa e o produto
        Mesas m1 = new Mesas(1, "Mesa 001");
        Produtos prod1 = new Produtos(1, "Brahma 600ML", 7);        
        
        //Cria o pedido
        Pedido p1 = new Pedido("00001", "21/09/2017", 70, m1, "Ronaldo");
        
        //Cria o movimento do pedido
        MoviPedidos movi1 = new MoviPedidos(p1, prod1, 10, 7, 70);        
        
        //Adiciona o movimento ao pedido
        p1.getMovimento().add(movi1);
        
        //cria o array com os pedidos
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(p1);
        
        return pedidos;
    }
    
}
