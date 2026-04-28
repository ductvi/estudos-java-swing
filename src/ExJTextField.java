import java.awt.*;
import javax.swing.*;

/**
 * Classe JTextField - Demonstra o uso de campos de texto (JTextField) em Java Swing
 * Implementa três exemplos de uso: login simples, validação de números e pesquisa
 */
public class ExJTextField {
    public static void main(String[] args) {
        // Cria uma janela/frame principal com título "Exemplos TextField"
        JFrame janela = new JFrame("Exemplos TextField");

        // Define o tamanho da janela: 500 pixels de largura e 400 de altura
        janela.setSize(500,400);

        // Configura a aplicação para encerrar quando a janela for fechada
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o layout da janela: GridLayout com 3 linhas, 1 coluna e espaçamentos de 10 pixels
        janela.setLayout(new GridLayout(3,1,10,10));

        JPanel painel = new JPanel(new FlowLayout());

        painel.setBorder(BorderFactory.createTitledBorder("Login simples:"));

        JLabel rotuloUsuario = new JLabel("Usuario: ");

        javax.swing.JTextField campoUsuario = new javax.swing.JTextField(10);

        JButton botaoLogin = new JButton("Entrar");

        botaoLogin.addActionListener(e -> 
            {
                JOptionPane.showMessageDialog
                (
                janela,
                "Bem Vindo, " + campoUsuario.getText() + " !",
                "Login",
                JOptionPane.INFORMATION_MESSAGE
                );
            }
        );

        painel.add(rotuloUsuario);

        painel.add(campoUsuario);

        painel.add(botaoLogin);

        JPanel painelValidacao = new JPanel(new FlowLayout());

        painelValidacao.setBorder(BorderFactory.createTitledBorder("Validação de número"));

        JLabel rotuloNumero = new JLabel("Digite um número: ");
        
        javax.swing.JTextField campoNumero = new javax.swing.JTextField(8);

        JButton botaoVerificar = new JButton("Verificar");

        botaoVerificar.addActionListener(e -> 
            {
                try {
                    int numero = Integer.parseInt(campoNumero.getText());
                    JOptionPane.showMessageDialog(janela, "Número válido: " + numero, "Validação", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(janela, "Por favor, digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        );

        painelValidacao.add(rotuloNumero);

        painelValidacao.add(campoNumero);

        painelValidacao.add(botaoVerificar);

        JPanel painelPesquisa = new JPanel(new BorderLayout());

        painelPesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisa:"));

        JPanel painelTopo = new JPanel(new FlowLayout());

        JLabel rotuloPesquisa = new JLabel("Pesquisar: ");

        javax.swing.JTextField campoPesquisa = new javax.swing.JTextField(15);

        JButton botaoPesquisar = new JButton("Pesquisar");

        JTextArea resultados = new JTextArea(5, 25);

        resultados.setEditable(false);

        JScrollPane rolagemResultados = new JScrollPane(resultados);

        botaoPesquisar.addActionListener(e -> 
            {
                String termo = campoPesquisa.getText();
                
                resultados.setText("Resultados para \"" + termo + "\":\n");
                resultados.append("Exemplo 1:\n");
                resultados.append("Exemplo 2:\n");
                resultados.append("Exemplo 3:\n");

            }
        );
        
        painelTopo.add(rotuloPesquisa);
        painelTopo.add(campoPesquisa);
        painelTopo.add(botaoPesquisar);

        painelPesquisa.add(painelTopo, BorderLayout.NORTH);
        painelPesquisa.add(rolagemResultados, BorderLayout.CENTER);

        janela.add(painelValidacao);
        janela.add(painel);
        janela.add(painelPesquisa);

        janela.setVisible(true);
    }
}
