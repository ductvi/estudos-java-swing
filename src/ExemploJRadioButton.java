import java.awt.*;
import javax.swing.*;

public class ExemploJRadioButton {
    public static void main(String[] args){
        JFrame janela = new JFrame("Exemplos JRadioButton");
        janela.setSize(400,300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new GridLayout(3,1,10,10));

        JPanel opcoes = new JPanel(new FlowLayout());
        opcoes.setBorder(BorderFactory.createTitledBorder("Escolha uma opcao:"));
        
        JRadioButton op1 = new JRadioButton("Opcao 1");
        JRadioButton op2 = new JRadioButton("Opcao 2");

        ButtonGroup g1 = new ButtonGroup();

        g1.add(op1);
        g1.add(op2);

        JButton mostrar = new JButton("Mostrar seleção");

        mostrar.addActionListener(e -> {
            String escolha = op1.isSelected()
                ? "Você escolheu a opção 1"
                : op2.isSelected() ? "Você escolheu a opção 2" : "Nenhuma opção selecionada";
            JOptionPane.showMessageDialog(janela, escolha);
        });

        JPanel genero = new JPanel(new FlowLayout());
        genero.setBorder(BorderFactory.createTitledBorder("Gênero:"));
        
        JRadioButton masc = new JRadioButton("Masculino");
        JRadioButton fem = new JRadioButton("Feminino");

        ButtonGroup g2 = new ButtonGroup();


        JPanel pagamento = new JPanel(new FlowLayout());
        pagamento.setBorder(BorderFactory.createTitledBorder("Forma de Pagamento:"));

        JRadioButton cartao = new JRadioButton("Cartão");
        JRadioButton boleto = new JRadioButton("Boleto");
        JRadioButton pix = new JRadioButton("Pix");

        ButtonGroup g3 = new ButtonGroup();

        JButton confirmarPagamento = new JButton("Confirmar Pagamento");
        
        confirmarPagamento.addActionListener(e -> {
            String pagamentos;

            if(cartao.isSelected()) pagamentos = "Cartão";
            else if(boleto.isSelected()) pagamentos = "Boleto";
            else if(pix.isSelected()) pagamentos = "Pix";
            else pagamentos = "Nenhuma forma de pagamento selecionada";

            JOptionPane.showMessageDialog(janela, "Forma de pagamento selecionada: " + pagamentos);
        });

        g3.add(cartao);
        g3.add(boleto);
        g3.add(pix);

        g2.add(masc);
        g2.add(fem);

        pagamento.add(cartao);
        pagamento.add(boleto);
        pagamento.add(pix);
        pagamento.add(confirmarPagamento);
        
        genero.add(fem);
        genero.add(masc);

        opcoes.add(op1);
        opcoes.add(op2);
        opcoes.add(mostrar);

        janela.add(opcoes);
        janela.add(genero);
        janela.add(pagamento);

        janela.setVisible(true);
    }
}