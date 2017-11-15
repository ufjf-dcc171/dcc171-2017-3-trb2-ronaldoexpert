package trabalho01_joseronaldosilveira;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JanelaPrincipal extends JFrame{
    private final JButton btnAbrePedidos = new JButton("Pedidos");
    private final JButton btnCadastroProduto = new JButton("Produtos");
    private final JButton btnCadastroMesa = new JButton("Mesas");
    private final int i = 0;
    private final Date dataAtual = new Date(); 
    private final JPanel painel = new JPanel();
    
    //Cria as mesas
    /*private static Mesas m1 = new Mesas(1, "Mesa 001");
    private static Mesas m2 = new Mesas(2, "Mesa 002");
    private static Mesas m3 = new Mesas(3, "Mesa 003");
    private static Mesas m4 = new Mesas(4, "Mesa 004");
    private static Mesas m5 = new Mesas(5, "Mesa 005");
    
    //Cria os produtos
    //private static Produtos prod1 = new Produtos(1, "Brahma 600ML", 7);
    //private static Produtos prod2 = new Produtos(2, "Heineken 600ML", 10);
    //private static Produtos prod3 = new Produtos(3, "Skol 600ML", 7);
    //private static Produtos prod4 = new Produtos(4, "CocaCola 2lt", 9);
    
    //Cria os pedidos
    private static Pedido pedido01 = new Pedido("00001", "21/09/2017", 70, m1, "Ronaldo");
    private static Pedido pedido02 = new Pedido("00002", "22/09/2017", 35, m2, "Ronaldo");
    private static Pedido pedido03 = new Pedido("00003", "23/09/2017", 50, m3, "Ronaldo");
    
    //Cria os Movipedidos
    //private static MoviPedidos movi1 = new MoviPedidos(prod1, 10, 7, 70);    
    //private static MoviPedidos movi2 = new MoviPedidos(prod3, 5, 7, 35);   
    //private static MoviPedidos movi3 = new MoviPedidos(prod2, 5, 10, 50); 
    */
    private JLabel statusbar = new JLabel("Lab. Prog. III - Prof. Igor Knop - UFJF - 2017");
    
    List<Mesas> mesas = new ArrayList<>();
    List<Produtos> produtos = new ArrayList<>();
    List<Pedido> pedidos = new ArrayList<>();
    List<MoviPedidos> moviPedidos = new ArrayList<>();
    
    ImageIcon icon = new ImageIcon("logo.png");
    JLabel label = new JLabel(icon);
    
    
    public JanelaPrincipal() throws HeadlessException {
        super("Principal");        
        /*
        //Adiciona mesas ao novo ArrayList
            mesas.add(m1);
            mesas.add(m2);
            mesas.add(m3);
            mesas.add(m4);
            mesas.add(m5);
        //FIM
        
        //Adiciona PRODUTOS ao novo ArrayList
        //    produtos.add(prod1);
        //    produtos.add(prod2);
        //    produtos.add(prod3);
        //    produtos.add(prod4);
        //FIM
        
        if (pedidos.size() == 0){
        //Adiciona MOVIPEDIDOS ao novo ArrayList
            moviPedidos.add(movi1);
            moviPedidos.add(movi2);
            moviPedidos.add(movi3);
        //FIM

        //Adiciona PEDIDOS ao novo ArrayList
            pedidos.add(pedido01);
            pedidos.add(pedido02);
            pedidos.add(pedido03);
                
            for (int i = 0; i < pedidos.size(); i++){
                if (pedidos.get(i).getMovimento().size() > 0){
                    pedidos.get(i).getMovimento().remove(pedidos.get(i).getMovimento().get(0));
                }
            }
                
            pedidos.get(0).getMovimento().add(movi1);
            pedidos.get(1).getMovimento().add(movi2);
            pedidos.get(2).getMovimento().add(movi3);
        //FIM 
             
        }*/
        
        painel.setLayout(null);        
        painel.setBackground(Color.WHITE);
        
        btnAbrePedidos.setSize(150, 50);
        btnAbrePedidos.setLocation(100, 130); //(Horizontal / Vertical)
        
        btnCadastroProduto.setSize(150, 50);
        btnCadastroProduto.setLocation(100, 200); //(Horizontal / Vertical)
        
        btnCadastroMesa.setSize(150, 50);
        btnCadastroMesa.setLocation(100, 270); //(Horizontal / Vertical)
        
        label.setSize(200, 119);
        label.setLocation(70, 10); //(Horizontal / Vertical)
        
        statusbar.setBorder(BorderFactory.createEtchedBorder());
        add(statusbar, BorderLayout.SOUTH);
        
        painel.add(label);
        painel.add(btnAbrePedidos);
        painel.add(btnCadastroProduto);
        painel.add(btnCadastroMesa);
        
        add(painel);
        
        btnAbrePedidos.addActionListener(new onClickBotao());
        btnCadastroProduto.addActionListener(new onClickBotao());
        btnCadastroMesa.addActionListener(new onClickBotao());
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<MoviPedidos> getMoviPedidos() {
        return moviPedidos;
    }

    public void setMoviPedidos(List<MoviPedidos> moviPedidos) {
        this.moviPedidos = moviPedidos;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
    
    public List<Mesas> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesas> mesas) {
        this.mesas = mesas;
    }
    
    
    private class onClickBotao implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btnAbrePedidos){
                JanelaPedidos janela;
                try {
                    janela = new JanelaPedidos(leMesas(), leProdutos(), pedidos, moviPedidos);
                    janela.setSize(500,700);
                    janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    janela.setIconImage(Toolkit.getDefaultToolkit().getImage("logo2.png"));
                    janela.setLocationRelativeTo(null);
                    janela.setVisible(true);  
                    janela.setTitle("Pedidos");
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }                    
            
            }else if(e.getSource() == btnCadastroMesa){
                JanelaMesas janela;
                try {
                    janela = new JanelaMesas(leMesas(), pedidos);
                    janela.setSize(500, 400);
                    janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    janela.setIconImage(Toolkit.getDefaultToolkit().getImage("logo2.png"));
                    janela.setLocationRelativeTo(null);
                    janela.setVisible(true);  
                    janela.setTitle("Cadastro de Mesas"); 
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
            
            }else if(e.getSource() == btnCadastroProduto){                
                JanelaProdutos janela;                
                try {
                    janela = new JanelaProdutos(leProdutos(), moviPedidos);
                    janela.setSize(500, 400);
                    janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    janela.setIconImage(Toolkit.getDefaultToolkit().getImage("logo2.png"));
                    janela.setLocationRelativeTo(null);
                    janela.setVisible(true);                  
                    janela.setTitle("Cadastro de Produtos"); 
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }                   
            }              
        }    
    }
    
    //Le arquivo .txt de produtos
    public static ArrayList<Produtos> leProdutos() throws FileNotFoundException, IOException {
        ArrayList<Produtos> lstProdutos = new ArrayList<>();
        
        FileReader arq = new FileReader("produtos.txt");
        BufferedReader lerArq = new BufferedReader(arq);

        String linha = lerArq.readLine(); // lê a primeira linha
        // a variável "linha" recebe o valor "null" quando o processo
        // de repetição atingir o final do arquivo texto
        while (linha != null) {
            String[] linhaArquivo = linha.split(",");
            Integer codigo =  Integer.parseInt(linhaArquivo[0]);
            String  descricao = linhaArquivo[1];
            String vlrUnit = linhaArquivo[2];
            
            float vlrUnitProd = Float.parseFloat(vlrUnit);
            
            Produtos produto = new Produtos(codigo, descricao, vlrUnitProd);
            lstProdutos.add(produto);
            linha = lerArq.readLine(); // lê da segunda até a última linha
        }

        return lstProdutos;
    }
    
    //Le arquivo .txt de mesas
    public static ArrayList<Mesas> leMesas() throws FileNotFoundException, IOException {
        ArrayList<Mesas> lstMesas = new ArrayList<>();
        
        FileReader arq = new FileReader("mesas.txt");
        BufferedReader lerArq = new BufferedReader(arq);

        String linha = lerArq.readLine(); // lê a primeira linha
        // a variável "linha" recebe o valor "null" quando o processo
        // de repetição atingir o final do arquivo texto
        while (linha != null) {
            String[] linhaArquivo = linha.split(",");
            Integer codigo =  Integer.parseInt(linhaArquivo[0]);
            String  descricao = linhaArquivo[1];
            
            Mesas mesa = new Mesas(codigo, descricao);
            lstMesas.add(mesa);
            linha = lerArq.readLine(); // lê da segunda até a última linha
        }

        return lstMesas;
    }
}
        
        
