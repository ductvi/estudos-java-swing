import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ExToolBar {
    // Constantes
    private static final int JANELA_LARGURA = 700;
    private static final int JANELA_ALTURA = 450;
    private static final int FONTE_PADRAO = 16;
    private static final int FONTE_MINIMA = 8;
    private static final int FONTE_MAXIMA = 64;
    private static final int PADDING = 4;
    private static final int PADDING_STATUS = 6;
    private static final String FONTE = "SansSerif";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExToolBar::criarTela);
    }

    private static JFrame janela;
    private static JTextArea area;
    private static JLabel status;
    private static Action acSalvar;

    private static void criarTela() {
        janela = new JFrame("Exemplo JToolBar");
        
        configurarJanela();
        area = criarTextArea();
        status = criarBarraStatus();
        
        JScrollPane rolagem = new JScrollPane(area);
        janela.add(rolagem, BorderLayout.CENTER);
        janela.add(status, BorderLayout.SOUTH);

        criarBarraFerramentas();
        configurarAtalhos();
        configurarDocumentListener();

        status.setText("Pronto. Use a barra de ferramentas ou atalhos.");
        janela.setVisible(true);
    }

    private static void configurarJanela() {
        janela.setSize(JANELA_LARGURA, JANELA_ALTURA);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setLayout(new BorderLayout());
    }

    private static JTextArea criarTextArea() {
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font(FONTE, Font.PLAIN, FONTE_PADRAO));

        return area;
    }

    private static JLabel criarBarraStatus() {
        JLabel status = new JLabel("Pronto");
        status.setBorder(BorderFactory.createEmptyBorder(PADDING_STATUS, 10, PADDING_STATUS, 10));
        return status;
    }

    private static void criarBarraFerramentas() {
        JToolBar barra = new JToolBar("Ferramentas");
        barra.setFloatable(true);
        barra.setRollover(true);
        barra.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        // Ações principais
        Action acNovo = criarActionNovo();
        acSalvar = criarActionSalvar();
        Action acPesquisar = criarActionPesquisar();
        Action acImprimir = criarActionImprimir();
        Action acSair = criarActionSair();

        // Adiciona botões de ação
        barra.add(acNovo);
        barra.add(acSalvar);
        barra.add(acPesquisar);
        barra.add(acImprimir);
        barra.add(acSair);
        barra.addSeparator();

        // Adiciona componentes de formatação
        adicionarComponentesFormatacao(barra);

        janela.add(barra, BorderLayout.NORTH);
    }

    private static Action criarActionNovo() {
        return new AbstractAction("Novo") {
            {
                putValue(SHORT_DESCRIPTION, "Novo (Ctrl + N)");
            }
            @Override 
            public void actionPerformed(ActionEvent e) {
                area.setText("");
                status.setText("Novo documento criado.");
                area.requestFocusInWindow();
            }
        };
    }

    private static Action criarActionSalvar() {
        Action ac = new AbstractAction("Salvar") {
            {
                putValue(SHORT_DESCRIPTION, "Salvar (Ctrl + S)");
            }
            @Override 
            public void actionPerformed(ActionEvent e) {
                String conteudo = area.getText();
                JOptionPane.showMessageDialog(janela, "Conteúdo salvo:\n" + conteudo, 
                    "Salvar", JOptionPane.INFORMATION_MESSAGE);
                status.setText("Documento salvo.");
            }
        };
        ac.setEnabled(false);
        return ac;
    }

    private static Action criarActionPesquisar() {
        return new AbstractAction("Pesquisar") {
            {
                putValue(SHORT_DESCRIPTION, "Pesquisar (Ctrl + F)");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarTexto();
            }
        };
    }

    private static Action criarActionImprimir() {
        return new AbstractAction("Imprimir") {
            {
                putValue(SHORT_DESCRIPTION, "Imprimir (Ctrl + P)");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhas = area.getLineCount();
                JOptionPane.showMessageDialog(janela, 
                    "Simulação de Impressão: " + linhas + " linha(s) no documento.",
                    "Imprimir", JOptionPane.INFORMATION_MESSAGE);
                status.setText("Impressão simulada.");
            }
        };
    }

    private static Action criarActionSair() {
        return new AbstractAction("Sair") {
            {
                putValue(SHORT_DESCRIPTION, "Sair (Ctrl + Q)");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }

    private static void adicionarComponentesFormatacao(JToolBar barra) {
        // Toggle para quebra de linha
        JToggleButton tgwrap = new JToggleButton("Quebra");
        tgwrap.setToolTipText("Ativar/Desativar quebra de linha");
        tgwrap.setSelected(true);
        tgwrap.addActionListener(e -> {
            boolean ativo = tgwrap.isSelected();
            area.setLineWrap(ativo);
            area.setWrapStyleWord(ativo);
            status.setText("Quebra de linha " + (ativo ? "ativada" : "desativada") + ".");
        });

        // Toggle para negrito
        JToggleButton tgNegrito = new JToggleButton("N");
        tgNegrito.setFont(new Font(FONTE, Font.BOLD, 16));
        tgNegrito.setToolTipText("Ativar/Desativar negrito");
        tgNegrito.addActionListener(e -> alternarNegrito(tgNegrito.isSelected()));

        // Spinner para tamanho da fonte
        JSpinner spTam = new JSpinner(new SpinnerNumberModel(FONTE_PADRAO, FONTE_MINIMA, FONTE_MAXIMA, 1));
        spTam.setToolTipText("Tamanho da fonte");
        spTam.addChangeListener(e -> alterarTamanhoFonte((Integer) spTam.getValue()));

        barra.add(tgwrap);
        barra.add(tgNegrito);
        barra.add(new JLabel("Tamanho: "));
        barra.add(spTam);
    }

    private static void alternarNegrito(boolean ativar) {
        Font f = area.getFont();
        if (ativar) {
            area.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            status.setText("Negrito ativado.");
        } else {
            area.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
            status.setText("Negrito desativado.");
        }
    }

    private static void alterarTamanhoFonte(int tamanho) {
        Font f = area.getFont();
        area.setFont(f.deriveFont((float) tamanho));
        status.setText("Tamanho da fonte: " + tamanho);
    }

    private static void configurarAtalhos() {
        JRootPane root = janela.getRootPane();
        int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        // Cria as actions para os atalhos
        Action acNovo = criarActionNovo();
        Action acPesquisar = criarActionPesquisar();
        Action acImprimir = criarActionImprimir();
        Action acSair = criarActionSair();

        // Registra atalhos
        registrarAtalho(root, mask, KeyEvent.VK_N, acNovo);
        registrarAtalho(root, mask, KeyEvent.VK_S, acSalvar);
        registrarAtalho(root, mask, KeyEvent.VK_F, acPesquisar);
        registrarAtalho(root, mask, KeyEvent.VK_P, acImprimir);
        registrarAtalho(root, mask, KeyEvent.VK_Q, acSair);
    }

    private static void registrarAtalho(JRootPane root, int mask, int keyCode, Action action) {
        String nome = (String) action.getValue(Action.NAME);
        KeyStroke ks = KeyStroke.getKeyStroke(keyCode, mask);
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, nome);
        root.getActionMap().put(nome, action);
    }

    private static void pesquisarTexto() {
        String termo = JOptionPane.showInputDialog(janela, "O que deseja buscar?");
        if (termo == null || termo.isEmpty()) {
            return;
        }

        String txt = area.getText();
        int index = txt.toLowerCase().indexOf(termo.toLowerCase());

        if (index >= 0) {
            area.requestFocusInWindow();
            area.select(index, index + termo.length());
            status.setText("Encontrado: \"" + termo + "\"");
        } else {
            JOptionPane.showMessageDialog(janela, "Termo não encontrado");
            status.setText("Termo não encontrado: \"" + termo + "\"");
        }
    }

    private static void configurarDocumentListener() {
        area.getDocument().addDocumentListener(new DocumentListener() {
            private void atualizarEstadoSalvar() {
                acSalvar.setEnabled(area.getDocument().getLength() > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                atualizarEstadoSalvar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                atualizarEstadoSalvar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                atualizarEstadoSalvar();
            }
        });
    }
}