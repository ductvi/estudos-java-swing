// Importa classes necessárias para criar interfaces gráficas com Swing
import java.awt.*; // Contém classes de layout e cores, entre outras
import javax.swing.*; // Contém componentes Swing como JFrame, JButton, JTextField, etc.
import javax.swing.tree.DefaultMutableTreeNode; // Classe usada para criar nós de árvore (JTree)

public class ComponentesBasicos {
    public static void main(String[] args) throws Exception {
        // Ponto de entrada do programa Java. Aqui o código Swing começa a ser executado.

        // Cria uma janela principal (frame) para a aplicação
        JFrame janela = new JFrame("Componentes básicos = Swing");

        // Define o tamanho da janela em pixels (largura x altura)
        janela.setSize(500, 400);

        // Configura a ação quando o usuário clica no "X" da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o gerenciador de layout da janela.
        // GridLayout organiza os componentes em uma grade com 2 colunas e espaçamento de 10 pixels.
        janela.setLayout(new GridLayout(0, 2, 10, 10));

        // Cria botões para demonstrar diferentes componentes Swing.
        // Cada botão abre uma janela de exemplo com o componente correspondente.
        JButton btnLabel = new JButton("Exemplo JLabel");
        JButton btnButton = new JButton("Exemplo JButton");
        JButton btnTextField = new JButton("Exemplo JTextField");
        JButton btnTextArea = new JButton("Exemplo JTextArea");
        JButton btnPassword = new JButton("Exemplo JPasswordField");
        JButton btnCheckBox = new JButton("Exemplo JCheckBox");
        JButton btnRadio = new JButton("Exemplo JRadio");
        JButton btnCombo = new JButton("Exemplo JComboBox");
        JButton btnList = new JButton("Exemplo JList");
        JButton btnTable = new JButton("Exemplo JTable");
        JButton btnTree = new JButton("Exemplo JTree");

        // Adiciona cada botão à janela principal.
        // Em Swing, adicionamos componentes ao contêiner que os exibirá.
        janela.add(btnLabel);
        janela.add(btnButton);
        janela.add(btnTextField);
        janela.add(btnTextArea);
        janela.add(btnPassword);
        janela.add(btnCheckBox);
        janela.add(btnRadio);
        janela.add(btnCombo);
        janela.add(btnList);
        janela.add(btnTable);
        janela.add(btnTree);

        // Define o comportamento de cada botão quando clicado.
        // addActionListener registra um ouvinte que reage ao evento de clique.
        btnLabel.addActionListener(e -> {
            // Exibe um diálogo simples com um JLabel.
            JOptionPane.showMessageDialog(
                janela,
                new JLabel("Sou um JLabel (rótulo de texto)!"));
        });

        btnButton.addActionListener(e -> {
            // Cria um botão dentro do diálogo para mostrar que um componente pode ser exibido em JOptionePane
            JButton botao = new JButton("Clique em mim!");
            JOptionPane.showMessageDialog(
                janela,
                botao,
                "Exemplo JButton",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnTextArea.addActionListener(e -> {
            // JTextArea permite texto em várias linhas
            JTextArea area = new JTextArea(5, 20);

            // JScrollPane adiciona barras de rolagem ao componente filho
            JScrollPane rolagem = new JScrollPane(area);

            JOptionPane.showMessageDialog(
                janela,
                rolagem,
                "Digite várias linhas (JTextArea)",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnPassword.addActionListener(e -> {
            // JPasswordField oculta os caracteres digitados para entrada de senha
            JPasswordField senha = new JPasswordField(10);

            JOptionPane.showMessageDialog(
                janela,
                senha,
                "Digite a senha(JPasswordField)",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnCheckBox.addActionListener(e -> {
            // JCheckBox é usado para opções que podem ser marcadas ou desmarcadas
            JCheckBox check = new JCheckBox("Aceito os termos.");
            JOptionPane.showMessageDialog(
                janela,
                check,
                "Exemplo JCheckBox",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnRadio.addActionListener(e -> {
            // JRadioButton é usado para escolha exclusiva entre várias opções
            JRadioButton radio1 = new JRadioButton("Opção 1");
            JRadioButton radio2 = new JRadioButton("Opção 2");

            // ButtonGroup agrupa os botões de rádio para que apenas um possa ser selecionado
            ButtonGroup grupo = new ButtonGroup();
            grupo.add(radio1);
            grupo.add(radio2);

            JPanel painel = new JPanel();
            painel.add(radio1);
            painel.add(radio2);

            JOptionPane.showMessageDialog(
                janela,
                painel,
                "Exemplo JRadioButton",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnCombo.addActionListener(e -> {
            // JComboBox exibe uma lista suspensa de opções
            String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"};
            JComboBox<String> combo = new JComboBox<>(opcoes);

            JOptionPane.showMessageDialog(
                janela,
                combo,
                "Exemplo JComboBox",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnList.addActionListener(e -> {
            // JList exibe uma lista de itens que o usuário pode selecionar
            String[] itens = {"Maça", "Banana", "Laranja", "Uva"};

            JList<String> lista = new JList<>(itens);
            JScrollPane rolagemLista = new JScrollPane(lista);

            JOptionPane.showMessageDialog(
                janela,
                rolagemLista,
                "Exemplo JList",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnTable.addActionListener(e -> {
            // JTable organiza dados em linhas e colunas, como uma tabela
            String[] colunas = {"ID", "Nome"};
            Object[][] dados = {
                {1, "Alice"},
                {2, "Bob"},
                {3, "Charlie"}
            };

            JTable tabela = new JTable(dados, colunas);
            JScrollPane rolagemTabela = new JScrollPane(tabela);

            JOptionPane.showMessageDialog(
                janela,
                rolagemTabela,
                "Exemplo JTable",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        btnTree.addActionListener(e -> {
            // JTree exibe uma estrutura hierárquica de nós
            DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Raiz");
            DefaultMutableTreeNode filho1 = new DefaultMutableTreeNode("Filho 1");
            DefaultMutableTreeNode filho2 = new DefaultMutableTreeNode("Filho 2");
            raiz.add(filho1);
            raiz.add(filho2);

            JTree arvore = new JTree(raiz);
            JScrollPane rolagemArvore = new JScrollPane(arvore);

            JOptionPane.showMessageDialog(
                janela,
                rolagemArvore,
                "Exemplo JTree",
                JOptionPane.PLAIN_MESSAGE
            );
        });

        // Exibe a janela criada. Antes dessa linha, a janela existe em memória mas não aparece na tela.
        janela.setVisible(true);
    }
}
