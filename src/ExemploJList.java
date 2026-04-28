import java.awt.*;
import javax.swing.*;

public class ExemploJList{
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplos JList");
        janela.setSize(500,300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new GridLayout(3,1,10,10));

        JPanel painelSimples = new JPanel(new BorderLayout());

        painelSimples.setBorder(BorderFactory.createTitledBorder("Seleção simples"));

        String[] frutas = {"Maça", "Banana", "Laranja", "Uva", "Pera"};

        JList<String> listaSimples = new JList<>(frutas);

        listaSimples.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton botaoMostrarSimples = new JButton("Mostrar Seleção");

        botaoMostrarSimples.addActionListener(e -> {
            String selecionado = listaSimples.getSelectedValue();
            JOptionPane.showMessageDialog(janela,
                selecionado != null ? "Você escolheu" + selecionado : "Nenhuma fruta selecionada."
            );
        });

        JPanel painelMultipla = new JPanel(new BorderLayout());
        painelMultipla.setBorder(BorderFactory.createTitledBorder("Seleção múltipla"));

        String[] cores = {"Vermelho", "Azul", "Verde", "Amarelo", "Preto", "Branco"};
        
        JList<String> listaMultipla = new JList<>(cores);

        listaMultipla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JButton botaoMostrarMultipla = new JButton("Mostrar Seleção");

        botaoMostrarMultipla.addActionListener(e -> {
            java.util.List<String> selecionados = listaMultipla.getSelectedValuesList();
            if(selecionados.isEmpty()){
                JOptionPane.showMessageDialog(janela, "Nenhuma cor selecionada.");
            } else {
                JOptionPane.showMessageDialog(janela, "Você escolheu: " + String.join(", ", selecionados));
            }
        });

        JPanel painelFixado = new JPanel(new BorderLayout());
        painelFixado.setBorder(BorderFactory.createTitledBorder("Lista com altura fixa"));

        String[] animais = { "Cachorro", "Gato", "Elefante", "Cavalo", "Leão", "Tigre"};
        JList<String> listaAnimais = new JList<>(animais);

        listaAnimais.setVisibleRowCount(3);

        painelFixado.add(new JScrollPane(listaAnimais), BorderLayout.CENTER);
        painelSimples.add(new JScrollPane(listaSimples), BorderLayout.CENTER);
        painelSimples.add((botaoMostrarSimples), BorderLayout.SOUTH);
        painelMultipla.add(new JScrollPane(listaMultipla), BorderLayout.CENTER);
        painelMultipla.add(botaoMostrarMultipla, BorderLayout.SOUTH);

        janela.add(painelSimples);
        janela.add(painelMultipla);
        janela.add(painelFixado);
        janela.setVisible(true);

    }
}