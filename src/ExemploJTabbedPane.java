import javax.swing.*; // Importa os componentes da interface gráfica (Swing)
import java.awt.*;    // Importa classes de layout e design (AWT)

public class ExemploJTabbedPane {
    public static void main(String[] args){
        // Cria a janela principal (o "container" de tudo)
        JFrame f = new JFrame("Exemplo JTabbedPane");

        // Define o tamanho inicial e o comportamento de fechar o programa ao sair
        f.setSize(500,350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instancia o componente de abas
        JTabbedPane abas = new JTabbedPane();
        
        // --- ABA 1: Texto ---
        // Cria uma área de texto onde o usuário pode escrever
        JTextArea area = new JTextArea("Aba de texto...\n Voce pode digitar aqui.");
        
        // Adiciona a área de texto dentro de um JScrollPane para ter barras de rolagem
        // Adiciona essa estrutura à primeira aba com o título "Texto"
        abas.addTab("Texto", new JScrollPane(area)); 
        
        // --- ABA 2: Lista ---
        // Define um array de strings que servirá de dados para a lista
        String[] itens = {"Item 1", "Item 2", "Item 3"};
        
        // Cria o componente de lista com os itens definidos
        JList<String> lista = new JList<>(itens);
        
        //---- ABA 3: Configurações ---
        JPanel config = new JPanel(new FlowLayout());

        config.add(new JLabel("Opção"));
        config.add(new JCheckBox("Ativar"));
        
        abas.addTab("Configurações", config);

        // Adiciona a lista com barra de rolagem na segunda aba com o título "Lista"
        abas.addTab("Lista", new JScrollPane(lista));
        
        // Adiciona o conjunto de abas ao frame principal
        f.add(abas);
        
        // Torna a janela visível na tela
        f.setVisible(true);
    }
}