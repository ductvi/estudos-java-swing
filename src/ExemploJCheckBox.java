import java.awt.*;
import javax.swing.*;

public class ExemploJCheckBox {
    public static void main(String[] args){

        //Criação da janela e seus parametros.
        JFrame janela = new JFrame("Exemplos de JCheckBox");

        janela.setSize(400,300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new GridLayout(3,1,10,10));

        //Criação do painel termos e configurações iniciais.
        JPanel termos = new JPanel(new FlowLayout());
        termos.setBorder(BorderFactory.createTitledBorder("Aceitar termos"));

        JCheckBox aceitar = new JCheckBox("Aceito");

        JButton confirmar = new JButton("Confirmar");

        //Ação do botão confirmar, exibindo uma mensagem de acordo com o checkbox "aceitar".
        confirmar.addActionListener(e -> {
            if(aceitar.isSelected()){
                JOptionPane.showMessageDialog(janela, "Você aceitou os termos!");
            } else {
                JOptionPane.showMessageDialog(janela, "Você precisa aceitar os termos!");
            }
        });

        //Criação do painel opções e configuração inicial.
        JPanel opcoes = new JPanel(new FlowLayout());
        opcoes.setBorder(BorderFactory.createTitledBorder("Escolha seus hobbies"));

        JCheckBox op1 = new JCheckBox("Ler");
        JCheckBox op2 = new JCheckBox("Esportes");
        JCheckBox op3 = new JCheckBox("Música");

        JButton verificar = new JButton("Verificar seleção");

        //Ação do botão verificar, exibindo os hobbies selecionados.
        verificar.addActionListener(e -> {
            StringBuilder hobbies = new StringBuilder("Voce gosta de: ");

            if(op1.isSelected()) hobbies.append("Ler ");
            if(op2.isSelected()) hobbies.append("Esportes ");
            if(op3.isSelected()) hobbies.append("Música ");

            JOptionPane.showMessageDialog(janela, hobbies.toString());

        });

        //Criação do painel funções e configuração inicial.
        JPanel funcoes = new JPanel(new FlowLayout());
        funcoes.setBorder(BorderFactory.createTitledBorder("Ativar/Desativar notificações"));

        JCheckBox notificacao = new JCheckBox("Ativar notificações", true);

        JLabel status = new JLabel("Notificações ativas");

        //Ação do checkbox notificacao, alterando o texto e a cor do status de acordo com a seleção.
        notificacao.addActionListener(e -> {
            if(notificacao.isSelected()){
                status.setText("Notificações ativas");
                status.setForeground(Color.GREEN);
            } else {
                status.setText("Notificações desativadas");
                status.setForeground(Color.RED);
            }
        });

        //Adição dos componentes aos paineís.
        opcoes.add(op1);
        opcoes.add(op2);
        opcoes.add(op3);
        opcoes.add(verificar);

        termos.add(aceitar);
        termos.add(confirmar);

        funcoes.add(notificacao);
        funcoes.add(status);

        //Adição dos paineis à janela e exibição.
        janela.add(termos);
        janela.add(opcoes);
        janela.add(funcoes);
        janela.setVisible(true);
    }
}