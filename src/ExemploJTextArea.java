import java.awt.*;
import javax.swing.*;

public class ExemploJTextArea {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplo JTextArea");

        janela.setSize(500, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new GridLayout(2, 1, 10, 10));

        // Painel Bloco de Notas
        JPanel painelbn = new JPanel(new BorderLayout());
        painelbn.setBorder(BorderFactory.createTitledBorder("Bloco de notas"));

        JTextArea areaNotas = new JTextArea(5, 30);
        JScrollPane scroll = new JScrollPane(areaNotas);

        painelbn.add(scroll, BorderLayout.CENTER);

        // Painel Log
        JPanel painellog = new JPanel(new BorderLayout());
        painellog.setBorder(BorderFactory.createTitledBorder("Área de Log"));

        JTextArea arealog = new JTextArea(5, 30);
        
        //Não permite a edição
        arealog.setEditable(false);

        //Altera o fundo para preto e a cor da fonte para verde.
        arealog.setBackground(Color.BLACK);
        arealog.setForeground(Color.GREEN);

        //Altera a fonte
        arealog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrolllog = new JScrollPane(arealog);

        painellog.add(scrolllog, BorderLayout.CENTER);

        JButton btnAddLog = new JButton("Adicionar Log");

        btnAddLog.addActionListener(e -> 
            {
                arealog.append("Nova Linha de log gerada...\n");
            }
        );

        painellog.add(btnAddLog, BorderLayout.SOUTH);
        
        JPanel painelc = new JPanel(new BorderLayout());
        painelc.setBorder(BorderFactory.createTitledBorder("Contador de caracteres"));

        JTextArea areaContador = new JTextArea(5, 30);
        
        JScrollPane scrollContador = new JScrollPane(areaContador);

        painelc.add(scrollContador, BorderLayout.CENTER);

        JLabel lblc = new JLabel("Caracteres: 0");
        painelc.add(lblc, BorderLayout.SOUTH);

        areaContador.addCaretListener(e->
            {
                int total = areaContador.getText().length();
                lblc.setText("Caracteres: " + total);

            }
        );

        // Adicionando na janela
        janela.add(painelbn);
        janela.add(painellog);
        janela.add(painelc);
        
        // Tornar visível
        janela.setVisible(true);
    }
}