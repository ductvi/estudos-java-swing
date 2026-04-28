import java.awt.*;
import javax.swing.*;

public class ExemploJComboBox {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplos JCombobox");
        janela.setSize(400,300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new FlowLayout());
        janela.setLayout(new GridLayout(3,1,10,10));

        JPanel painelItens = new JPanel(new FlowLayout());
        painelItens.setBorder(BorderFactory.createTitledBorder("Escolha um item: "));

        String[] itens = {"Item 1", "Item 2", "Item 3"};

        JComboBox<String> comboItens = new JComboBox<>(itens);

        JButton mostrar = new JButton("Mostrar seleção");

        mostrar.addActionListener(e -> {
            String selecionado = (String) comboItens.getSelectedItem();
            JOptionPane.showMessageDialog(janela, "Item selecionado: " + selecionado);

        });

        JPanel painelPaises = new JPanel(new FlowLayout());
        painelPaises.setBorder(BorderFactory.createTitledBorder("Escolha um país: "));

        String[] paises = {"Brasil", "Argentina", "Chile", "Estados Unidos", "Japão"};

        JComboBox<String> comboPaises = new JComboBox<>(paises);
        JButton mostrarPais = new JButton("Mostrar país selecionado");

        mostrarPais.addActionListener(e -> {
            String paisSelecionado = (String) comboPaises.getSelectedItem();
            JOptionPane.showMessageDialog(janela, "País selecionado: " + paisSelecionado);
        });

        JPanel painelCores = new JPanel(new FlowLayout());
        painelCores.setBorder(BorderFactory.createTitledBorder("Trocar cor de fundo"));

        String[] cores = {"Branco", "Amarelo", "Verde", "Azul"};
        JComboBox<String> comboCores = new JComboBox<>(cores);
        
        JPanel exemplo = new JPanel();

        exemplo.setPreferredSize(new Dimension(100,50));
        exemplo.setBackground(Color.WHITE);

        comboCores.addActionListener(e -> {
            String corSelecionada = (String) comboCores.getSelectedItem();
            switch (corSelecionada) {
                case "Branco" -> exemplo.setBackground(Color.WHITE);
                case "Amarelo" -> exemplo.setBackground(Color.YELLOW);
                case "Verde" -> exemplo.setBackground(Color.GREEN);
                case "Azul" -> exemplo.setBackground(Color.BLUE);
            }
        });


        painelItens.add(comboItens);
        painelItens.add(mostrar);

        painelPaises.add(comboPaises);
        painelPaises.add(mostrarPais);

        painelCores.add(comboCores);
        painelCores.add(exemplo);

        janela.add(painelItens);
        janela.add(painelPaises);
        janela.add(painelCores);
        janela.setVisible(true);
    }
}