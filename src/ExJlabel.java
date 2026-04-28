import java.awt.*;
import javax.swing.*;

public class ExJlabel {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplo JLabel melhorado.");
        
        janela.setSize(400,300);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.setLayout(new BorderLayout(10,10));

        JLabel r1 = new JLabel("Sou um JLabel padrão.");

        JLabel r2 = new JLabel("Fonte maior em negrito.");

        r2.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel r3 = new JLabel("texto em Azul.");

        r3.setForeground(Color.BLUE);

        JLabel r4 = new JLabel("Texto com fundo amarelo.");

        r4.setOpaque(true);

        r4.setBackground(Color.yellow);

        ImageIcon i = new ImageIcon("D:\\Curso de Java\\InterfaceSwift\\java.png");

        Image img = i.getImage();

        Image red = img.getScaledInstance(100, 200, Image.SCALE_SMOOTH);

        JLabel r5 = new JLabel(new ImageIcon(red));

        JPanel painel = new JPanel(new GridLayout(5,1,10,10));

        painel.add(r1);

        painel.add(r2);

        painel.add(r3);

        painel.add(r4);

        JPanel pimg = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pimg.add(r5);

        janela.add(painel, BorderLayout.CENTER);

        janela.add(pimg, BorderLayout.SOUTH);

        janela.pack();

        janela.setLocationRelativeTo(null);
        
        janela.setVisible(true);
    }
}