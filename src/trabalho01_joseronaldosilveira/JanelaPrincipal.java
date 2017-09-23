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
                JanelaPedidos janela = new JanelaPedidos(criaMesas(), criaProdutos());
                janela.setSize(500, 400);
                janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);  
                janela.setTitle("Pedidos");    
            
            }else if(e.getSource() == btnCadastroMesa){
                JanelaMesas janela = new JanelaMesas(criaMesas());
                janela.setSize(500, 400);
                janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);  
                janela.setTitle("Cadastro de Mesas");    
            
            }else if(e.getSource() == btnCadastroProduto){
                JanelaProdutos janela = new JanelaProdutos(criaProdutos());
                janela.setSize(500, 400);
                janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);  
                janela.setTitle("Cadastro de Produtos");    
            }              
        }    
    }
    
            
    private static List<Mesas> criaMesas() {
        Mesas m1 = new Mesas(1, "Mesa 001");
        Mesas m2 = new Mesas(2, "Mesa 002");
        Mesas m3 = new Mesas(3, "Mesa 003");
        Mesas m4 = new Mesas(4, "Mesa 004");
        
        List<Mesas> mesas = new ArrayList<>();
        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);
        mesas.add(m4);
        
        return mesas;        
    }
    
    private static List<Produtos> criaProdutos() {
        Produtos prod1 = new Produtos(1, "Brahma 600ML", 7);
        Produtos prod2 = new Produtos(2, "Heineken 600ML", 10);
        Produtos prod3 = new Produtos(3, "Skol 600ML", 7);
        Produtos prod4 = new Produtos(4, "CocaCola 2lt", 9);
        
        
        List<Produtos> prods = new ArrayList<Produtos>();
        prods.add(prod1);
        prods.add(prod2);
        prods.add(prod3);
        prods.add(prod4);
        
        return prods;
    }
    
}

        
        /*
        //Cria o pedido
        Pedido p1 = new Pedido("00001", "21/09/2017", 70, m1, "Ronaldo");
        
        //Cria o movimento do pedido
        MoviPedidos movi1 = new MoviPedidos(p1, prod1, 10, 7, 70);        
        
        //Adiciona o movimento ao pedido
        p1.getMovimento().add(movi1);
        
        //cria o array com os pedidos*/
        
        
