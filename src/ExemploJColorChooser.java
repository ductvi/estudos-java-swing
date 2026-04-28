import java.awt.*;
import javax.swing.*;
public class ExemploJColorChooser{
    public static void main(String[] args) {
        JFrame f = new JFrame("JColorChooser - Seletor de cores");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,350);
        f.setLayout(new BorderLayout(8,8));

        JLabel ex = new JLabel("Exemplo de texto colorido");

        ex.setHorizontalAlignment(SwingConstants.CENTER);

        ex.setFont(ex.getFont().deriveFont(Font.BOLD, 18f));

        ex.setOpaque(true);

        ex.setBackground(Color.WHITE);

        ex.setForeground(Color.DARK_GRAY);

        JButton btnFonte = new JButton("Cor do Texto");

        JButton btnFundo = new JButton("Cor de Fundo");

        btnFonte.addActionListener(e -> {
            Color c = JColorChooser.showDialog(f, "Escolha a cor do texto", ex.getForeground());

            if (c != null) {
                ex.setForeground(c);
            }
        });

        btnFundo.addActionListener(e -> {
            Color c = JColorChooser.showDialog(f, "Escolha a cor de fundo", ex.getBackground());

            if(c != null){
                ex.setBackground(c);
            }
        });

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));

        topo.add(btnFonte);
        topo.add(btnFundo);

        f.add(topo, BorderLayout.NORTH);
        f.add(ex, BorderLayout.CENTER);

        f.setVisible(true);

    }
}