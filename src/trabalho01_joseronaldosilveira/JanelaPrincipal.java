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
    private int i = 0;
    
    //Cria as mesas
    private static Mesas m1 = new Mesas(1, "Mesa 001");
    private static Mesas m2 = new Mesas(2, "Mesa 002");
    private static Mesas m3 = new Mesas(3, "Mesa 003");
    private static Mesas m4 = new Mesas(4, "Mesa 004");
    private static Mesas m5 = new Mesas(5, "Mesa 005");
    
    //Cria os produtos
    private static Produtos prod1 = new Produtos(1, "Brahma 600ML", 7);
    private static Produtos prod2 = new Produtos(2, "Heineken 600ML", 10);
    private static Produtos prod3 = new Produtos(3, "Skol 600ML", 7);
    private static Produtos prod4 = new Produtos(4, "CocaCola 2lt", 9);
    
    //Cria os pedidos
    private static Pedido pedido01 = new Pedido("00001", "21/09/2017", 120, m1, "Ronaldo");
    private static Pedido pedido02 = new Pedido("00002", "22/09/2017", 44, m2, "Ronaldo");
    private static Pedido pedido03 = new Pedido("00003", "23/09/2017", 100, m3, "Ronaldo");
    
    //Cria os Movipedidos
    private static MoviPedidos movi1 = new MoviPedidos(pedido01, prod1, 10, 7, 70);
    private static MoviPedidos movi2 = new MoviPedidos(pedido01, prod2, 5, 10, 50); 
    
    private static MoviPedidos movi3 = new MoviPedidos(pedido02, prod3, 5, 7, 35); 
    private static MoviPedidos movi4 = new MoviPedidos(pedido02, prod4, 1, 9, 9); 
    
    private static MoviPedidos movi5 = new MoviPedidos(pedido03, prod2, 5, 10, 50); 
    private static MoviPedidos movi6 = new MoviPedidos(pedido03, prod2, 5, 10, 50); 
        
    
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
                JanelaPedidos janela = new JanelaPedidos(criaMesas(), criaProdutos(), criaPedido(), criaMoviPedido());
                janela.setSize(500,700);
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
        List<Mesas> mesas = new ArrayList<>();
        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);
        mesas.add(m4);
        mesas.add(m5);
        
        return mesas;        
    }
    
    private static List<Produtos> criaProdutos() {
        List<Produtos> prods = new ArrayList<Produtos>();
        prods.add(prod1);
        prods.add(prod2);
        prods.add(prod3);
        prods.add(prod4);
        
        return prods;
    }
    
    private static List<Pedido> criaPedido() {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos.add(pedido01);
        pedidos.add(pedido02);
        pedidos.add(pedido03);
        
        pedido01.getMovimento().add(movi1);
        pedido01.getMovimento().add(movi2);
        
        pedido02.getMovimento().add(movi3);
        pedido02.getMovimento().add(movi4);
        
        pedido03.getMovimento().add(movi5);
        pedido03.getMovimento().add(movi6);
        
        
        return pedidos;
    }
    
    private static List<MoviPedidos> criaMoviPedido() {
        List<MoviPedidos> moviPed = new ArrayList<MoviPedidos>();
        moviPed.add(movi1);
        moviPed.add(movi2);
        moviPed.add(movi3);
        moviPed.add(movi4);
        moviPed.add(movi5);
        moviPed.add(movi6);
        
        return moviPed;
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
        
        
