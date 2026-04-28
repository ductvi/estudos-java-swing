import java.awt.*;
import javax.swing.*;

public class ExemploJPasswordField {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplo de JPasswordField");

        janela.setSize(450, 350);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new GridLayout(3, 1, 10, 10));

        JPanel painellogin = new JPanel(new FlowLayout());

        painellogin.setBorder(BorderFactory.createTitledBorder("Login"));
        JLabel rotuloUsuario = new JLabel("Usuario");

        JTextField campoUsuario = new JTextField(10);

        JLabel rotuloSenha = new JLabel("Senha");
        JPasswordField campoSenha = new JPasswordField(10);

        JButton botaoEntrar = new JButton("Entrar");

        botaoEntrar.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            if (usuario.equals("admin") && senha.equals("1234")) {
                JOptionPane.showMessageDialog(janela, "Login bem-sucedido!");
            } else {
                JOptionPane.showMessageDialog(janela, "Login falhou! Usuario ou senha incorretos.");
            }

        });

        JPanel painelMostrar = new JPanel(new FlowLayout());

        painelMostrar.setBorder(BorderFactory.createTitledBorder("Mostrar senha"));
        JPasswordField camposenha2 = new JPasswordField(10);
        JCheckBox mostrarSenha = new JCheckBox("Mostrar senha");

        mostrarSenha.addActionListener(e -> {
            if (mostrarSenha.isSelected()) {
                camposenha2.setEchoChar((char) 0);
            } else {
                camposenha2.setEchoChar('*');
            }
        });

        JPanel painelforca = new JPanel(new BorderLayout());

        painelforca.setBorder(BorderFactory.createTitledBorder("Força da senha"));

        JPasswordField campoSenhaForca = new JPasswordField(15);
        JLabel labelForca = new JLabel("Digite a senha: ");

        labelForca.setHorizontalAlignment(SwingConstants.CENTER);

        campoSenhaForca.addCaretListener( e -> 
            {
                String senha = new String(campoSenhaForca.getPassword());

                if(senha.isEmpty()){
                    labelForca.setText("Digite a senha: ");
                    labelForca.setForeground(Color.BLACK);
                } else if(senha.length() < 4){
                    labelForca.setText("Senha fraca");
                    labelForca.setForeground(Color.RED);
                } else if(senha.length() < 8){
                    labelForca.setText("Senha média");
                    labelForca.setForeground(Color.ORANGE);
                } else {
                    labelForca.setText("Senha Forte");
                    labelForca.setForeground(Color.GREEN);
                }
            }
        );

        painelforca.add(labelForca, BorderLayout.NORTH);
        painelforca.add(campoSenhaForca, BorderLayout.CENTER);
    
        painelMostrar.add(camposenha2);
        painelMostrar.add(mostrarSenha);

        painellogin.add(rotuloUsuario);
        painellogin.add(campoUsuario);
        painellogin.add(rotuloSenha);
        painellogin.add(campoSenha);
        painellogin.add(botaoEntrar);

        janela.add(painellogin);
        janela.add(painelMostrar);
        janela.add(painelforca);
        janela.setVisible(true);
    }
}
