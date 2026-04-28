import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class ExMenuBar {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplo JMenuBar");

        janela.setSize(600,400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        //Criamos uma barra com escrita "Pronto" na parte inferior da janela
        JLabel barraStatus = new JLabel("Pronto");

        barraStatus.setBorder(BorderFactory.createEmptyBorder(6,10,6,10));
        janela.add(barraStatus, BorderLayout.SOUTH);

        //Criamos uma barra de menu, e adicionamos 4 menus, cada um com um atalho de teclado (mnemonico)
        JMenuBar menuBar = new JMenuBar();

        //Menus adicionados
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuEditar = new JMenu("Editar");
        JMenu menuExibir = new JMenu("Exibir");
        JMenu menuAjuda = new JMenu("Ajuda");

        //Atalhos desses menus VK_A = Alt + A.
        menuArquivo.setMnemonic(KeyEvent.VK_A);
        menuEditar.setMnemonic(KeyEvent.VK_E);
        menuExibir.setMnemonic(KeyEvent.VK_X);
        menuAjuda.setMnemonic(KeyEvent.VK_J);
        
        //Criamos os itens do menu "Arquivo", e seus atalhos.
        JMenuItem itemNovo = new JMenuItem("Novo");
        JMenuItem itemAbrir = new JMenuItem("Abrir");
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemSair = new JMenuItem("Sair");

        itemNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        //Criamos um submenu dentro do menu "Arquivo" para arquivos recentes.
        JMenu menuRecentes = new JMenu("Recentes");
        JMenuItem itemRecente1 = new JMenuItem("Arquivo1.txt");
        JMenuItem itemRecente2 = new JMenuItem("Relatorio.pdf");
        JMenuItem itemRecente3 = new JMenuItem("notas.md");

        //Submenu do "Editar".
        JMenuItem itemRecortar = new JMenuItem("Recortar");
        JMenuItem itemCopiar = new JMenuItem("Copiar");
        JMenuItem itemColar = new JMenuItem("Colar");

        //Submenu do "Exibir"
        JCheckBoxMenuItem itemStatus = new JCheckBoxMenuItem("Mostrar Barra de Status", true);

        //Submenu do "Ajuda"
        JMenuItem itemSobre = new JMenuItem("Sobre...");

        //Ações dos submenus
        itemNovo.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Criar um novo arquivo..."));
        itemAbrir.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Abrir um arquivo existente..."));
        itemSalvar.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Salvar o arquivo atual..."));
        itemRecente1.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Abrindo Arquivo1.txt..."));
        itemRecente2.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Abrindo Relatorio.pdf..."));
        itemRecente3.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Abrindo notas.md..."));
        itemRecortar.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Recortando..."));
        itemCopiar.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Copiando..."));
        itemColar.addActionListener(e -> JOptionPane.showMessageDialog(janela, "Colando..."));
        itemSobre.addActionListener(e -> JOptionPane.showMessageDialog(janela, 
            "Exemplo simples de JMenuBar em Java Swing.\nAutor: Eduardo Custódio\n2025",
            "Sobre",
            JOptionPane.INFORMATION_MESSAGE
        ));
        itemStatus.addActionListener(e -> {
            boolean visivel = itemStatus.isSelected();
            barraStatus.setVisible(visivel);
        });

        
        //Adição dos itens aos respectivos menus.
        menuRecentes.add(itemRecente1);
        menuRecentes.add(itemRecente2);
        menuRecentes.add(itemRecente3);

        menuArquivo.add(itemNovo);
        menuArquivo.add(itemAbrir);
        menuArquivo.add(itemSalvar);
        menuArquivo.add(menuRecentes);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);

        menuEditar.add(itemRecortar);
        menuEditar.add(itemCopiar);
        menuEditar.add(itemColar);

        menuExibir.add(itemStatus);

        menuAjuda.add(itemSobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuEditar);
        menuBar.add(menuExibir);
        menuBar.add(menuAjuda);

        //Adiconamos a barra de menu á janela e um JTextArea.
        janela.setJMenuBar(menuBar);
        janela.add(new JScrollPane(new JTextArea("Área de trabalho... \nUse os menus acima.")), BorderLayout.CENTER);
        janela.setVisible(true);
    }
}