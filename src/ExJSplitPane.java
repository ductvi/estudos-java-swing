import javax.swing.*;

public class ExJSplitPane {
    public static void main(String[] args){

        //Criação da janela e suas configurações iniciais.
        JFrame f = new JFrame("JSplitpane - duas areas redimensionadas");
        f.setSize(700,450);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Criação de uma área de texto par\ escrever
        JTextArea editor = new JTextArea("Área esquerda (editor de texto)");

        //Configurações para que o texto se ajuste à largura da área, evitando a necessidade de rolagem horizontal.
        editor.setLineWrap(true);
        editor.setWrapStyleWord(true);

        //Criação de uma segunda área, mas que apeas sirva para visualização.
        JTextArea preview = new JTextArea("Área direita (Visualização)");

        //Impede a edição da área de preview.
        preview.setEditable(false);

        //Adiciona um listener para detectar mudanças no texto do editor e atualizar o preview em tempo real.
        editor.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updatePreview();
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updatePreview();
            }
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updatePreview();
            }

            public void updatePreview() {
                preview.setText(editor.getText());
            }
        });

        //Criação do Split para dividir a tela em duas partes.
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(editor), new JScrollPane(preview));
        split.setDividerLocation(0.5);
        split.setResizeWeight(0.5);
        
        f.add(split);
        f.setVisible(true);
    }
}