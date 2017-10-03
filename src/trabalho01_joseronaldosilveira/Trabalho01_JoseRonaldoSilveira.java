/*
 * Criado por José Ronaldo Silveira Miguel
 * V01 - 21/09/2017
 * V02 - 02/10/2017
 * V02.1 - 03/10/2017
 */
package trabalho01_joseronaldosilveira;

import java.awt.Toolkit;
import javax.swing.JFrame;
    
public class Trabalho01_JoseRonaldoSilveira {

    public static void main(String[] args) {
        JanelaPrincipal janelaP = new JanelaPrincipal();
        janelaP.setSize(370, 400);
        janelaP.setIconImage(Toolkit.getDefaultToolkit().getImage("logo2.png"));
        janelaP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaP.setLocationRelativeTo(null);
        janelaP.setVisible(true);  
        janelaP.setTitle("Sistemas de Gerenciamento de Bar");
    }
    
    
    
}
