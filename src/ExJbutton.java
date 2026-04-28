// Importações das bibliotecas necessárias
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Classe principal para criar uma interface com JButton
public class ExJbutton {
    public static void main(String[] args) {
        // Tenta aplicar o look and feel do sistema operacional
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) {
            // Ignora exceções se o look and feel não puder ser aplicado
        }    

        // Cria a janela principal
        JFrame janela = new JFrame("JButton Melhorado");

        // Define a ação ao fechar a janela (encerrar a aplicação)
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o layout da janela como BorderLayout com espaçamento de 10px
        janela.setLayout(new BorderLayout(10,10));

        // Cria um painel com layout de 4 linhas e 1 coluna
        JPanel painel = new JPanel(new GridLayout(4,1,10,10));

        // Define as margens (borders) do painel com 10px de espaçamento
        painel.setBorder(new EmptyBorder(10,10,10,10));

        // Cria um botão com o texto "Clique Aqui"
        JButton botao1 = new JButton("Clique Aqui");

        // Define o texto que aparece ao passar o mouse sobre o botão
        botao1.setToolTipText("Exemplo de botão básico");

        // Adiciona um listener para capturar cliques no botão
        botao1.addActionListener(e -> 
            {
                // Exibe uma caixa de diálogo quando o botão é clicado
                JOptionPane.showMessageDialog(janela, "Você clicou no botão!");
            }
        );

        // Cria um segundo botão com o texto "Botão Azul"
        JButton botao2 = new JButton("Botão Azul");

        // Personaliza a aparência do botão com uma classe anônima BasicButtonUI
        botao2.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            // Método que instala a interface de usuário personalizada no componente
            public void installUI(JComponent c) {
                super.installUI(c);
                c.setBackground(new Color(70, 130, 180)); // Define a cor de fundo como azul
                c.setForeground(Color.WHITE); // Define a cor do texto como branco
                c.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte como Arial, negrito, tamanho 16
                c.setBorder(BorderFactory.createLineBorder(new Color(0,70,170)));
                if (c instanceof JButton) {
                    ((JButton) c).setFocusPainted(false); // Remove a borda de foco ao clicar
                }
            }
        });

        botao2.addActionListener(e ->
            {
                // Exibe uma caixa de diálogo quando o segundo botão é clicado
                JOptionPane.showMessageDialog(janela, "Você clicou no botão azul!");
            }
        );

        JButton botao3 = new JButton("Fonte Grande");

        botao3.setFont(new Font("Arial", Font.BOLD, 24)); // Define a fonte do botão como Arial, negrito, tamanho 24
        botao3.addActionListener(e ->
            {
                JOptionPane.showMessageDialog(
                    janela,
                    "Você clicou no botão com fonte grande!"
                );
            }
        );

        URL url = ExJbutton.class.getResource("/java.png");
        
        ImageIcon icone = null;

        if(url != null) {
            icone = new ImageIcon(url);
            int largura = 32; // Largura desejada do ícone
            int altura = 32; // Altura desejada do ícone
            Image imgEscalada = icone.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            botao1.setIcon(new ImageIcon(imgEscalada)); // Define o ícone do botão

        } else {
            System.err.println("Ícone não encontrado!");
        }

        JButton botao4 = (icone != null) ? new JButton("Botão com Ícone", new ImageIcon(icone.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH))) : new JButton("Botão sem Ícone");

        botao4.addActionListener(e ->
            {
                JOptionPane.showMessageDialog(janela, "Você clicou no botão com ícone!");
            }
        );

        // Adiciona o botão ao painel
        painel.add(botao1);
        painel.add(botao2);
        painel.add(botao3);
        painel.add(botao4);

        // Adiciona o painel à janela (no centro)
        janela.add(painel, BorderLayout.CENTER);

        // Ajusta o tamanho da janela ao tamanho dos componentes
        janela.pack();

        // Define o tamanho mínimo da janela
        janela.setMinimumSize(new Dimension(420, 360));

        // Centraliza a janela na tela
        janela.setLocationRelativeTo(null);

        // Torna a janela visível
        janela.setVisible(true);


    }
}