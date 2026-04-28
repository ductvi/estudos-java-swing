import javax.swing.*;
import java.awt.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExPopupMenu {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplo de JPopupMenu");
        janela.setSize(600,400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());
        
        JTextArea areaTexto = new JTextArea("Clique com o botão direito aqui!");
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        JScrollPane rolagem = new JScrollPane(areaTexto);

        JLabel status = new JLabel("Linha: 1, Coluna: 1  | Seleção: 0");
        status.setBorder(BorderFactory.createEmptyBorder(6,10,6,10));

        areaTexto.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {

                try {
                    int pos = areaTexto.getCaretPosition();
                    int linha = areaTexto.getLineOfOffset(pos) + 1;
                    int coluna = pos - areaTexto.getLineStartOffset(linha - 1) + 1;
                    int selecao = Math.abs(areaTexto.getCaret().getDot() - areaTexto.getCaret().getMark());
                    status.setText("Linha: " + linha + ", Coluna: " + coluna + "  | Seleção: " + selecao);

                } catch (Exception ignore) {
                    status.setText("Linha: 1, Coluna: 1  | Seleção: 0");
                }

            }
            
        });

        JPopupMenu popup = new JPopupMenu();

        JMenuItem itemRecortar = new JMenuItem("Recortar");

        JMenuItem itemCopiar = new JMenuItem("Copiar");

        JMenuItem itemColar = new JMenuItem("Colar");

        JMenuItem itemSelecionarTudo = new JMenuItem("Selecionar tudo");

        JMenuItem itemInserirDataHora = new JMenuItem("Inserir Data/Hora");

        JMenuItem itemLimpar = new JMenuItem("Limpar");

        itemRecortar.addActionListener(e -> areaTexto.cut());
        
        itemCopiar.addActionListener(e -> areaTexto.copy());

        itemColar.addActionListener(e -> areaTexto.paste());

        itemInserirDataHora.addActionListener(e -> {
            String agora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            areaTexto.replaceSelection(agora);
        });

        itemLimpar.addActionListener(e -> areaTexto.setText(""));

        InputMap im = areaTexto.getInputMap(JComponent.WHEN_FOCUSED);

        ActionMap am = areaTexto.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "recortar");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "copiar");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "colar");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "selecionar_tudo");

        am.put("recortar", new AbstractAction() {
           @Override
           public void actionPerformed(java.awt.event.ActionEvent e){areaTexto.cut();
           } 
        });

        am.put("copiar", new AbstractAction() {
           @Override
           public void actionPerformed(java.awt.event.ActionEvent e){areaTexto.copy();
           } 
        });

        am.put("colar", new AbstractAction() {
           @Override
           public void actionPerformed(java.awt.event.ActionEvent e){areaTexto.paste();
           } 
        });

        am.put("selecionar_tudo", new AbstractAction() {
           @Override
           public void actionPerformed(java.awt.event.ActionEvent e){areaTexto.selectAll();
           } 
        });

        popup.add(itemRecortar);
        popup.add(itemCopiar);
        popup.add(itemColar);
        popup.addSeparator();
        popup.add(itemSelecionarTudo);
        popup.add(itemInserirDataHora);
        popup.addSeparator();
        popup.add(itemLimpar);

        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e){
                boolean temSelecao = areaTexto.getSelectionStart() != areaTexto.getSelectionEnd();
                itemRecortar.setEnabled(temSelecao);
                itemCopiar.setEnabled(temSelecao);
                itemLimpar.setEnabled(areaTexto.getText().length() > 0);
                itemColar.setEnabled(clipBoardTemTexto());
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e){
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e){
            }
        });

        areaTexto.setComponentPopupMenu(popup);

        areaTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            
            private void talvezMostrar(java.awt.event.MouseEvent e ){
                if(e.isPopupTrigger()){
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }    

            @Override
            public void mousePressed(java.awt.event.MouseEvent e){
                talvezMostrar(e);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e){
                talvezMostrar(e);
            }

        });

        janela.add(rolagem, BorderLayout.CENTER);
        janela.add(status, BorderLayout.SOUTH);
        
        janela.setVisible(true);
    }

    private static boolean clipBoardTemTexto() {
       try
       {
            Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            return t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor);
       } catch (Exception e)
       {
            return false;
       }
    }
}