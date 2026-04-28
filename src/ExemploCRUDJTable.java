import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

public class ExemploCRUDJTable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExemploCRUDJTable::criarTabela);
    }

    private static void criarTabela() {
        JFrame janela = new JFrame("JTable com CRUD + PESQUISA");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(900, 500);
        janela.setLocationRelativeTo(null);
        janela.setLayout(new BorderLayout(10, 10));

        String[] colunas = {"ID", "Nome"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };

        modelo.addRow(new Object[]{1, "Nicolly"});
        modelo.addRow(new Object[]{2, "Bruno"});
        modelo.addRow(new Object[]{3, "Carlos"});

        JTable tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setRowHeight(24);
        tabela.setAutoCreateRowSorter(true);

        TableColumnModel cols = tabela.getColumnModel();
        cols.getColumn(0).setPreferredWidth(80);
        cols.getColumn(1).setPreferredWidth(300);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabela.setRowSorter(sorter);

        JScrollPane rolagem = new JScrollPane(tabela);
        janela.add(rolagem, BorderLayout.CENTER);

        JPanel topo = new JPanel(new BorderLayout(8, 8));
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        painelBusca.add(new JLabel("Pesquisar:"));
        JTextField campoPesquisa = new JTextField(25);
        JButton btnLimpar = new JButton("Limpar");
        painelBusca.add(campoPesquisa);
        painelBusca.add(btnLimpar);

        JLabel lblContagem = new JLabel();
        JPanel painelContagem = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        painelContagem.add(lblContagem);

        topo.add(painelBusca, BorderLayout.WEST);
        topo.add(painelContagem, BorderLayout.EAST);
        janela.add(topo, BorderLayout.NORTH);

        DocumentListener listenerFiltro = new DocumentListener() {
            private void aplicarFiltro() {
                String termo = campoPesquisa.getText().trim();
                if (termo.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(termo)));
                }
                atualizarContagem(tabela, modelo, lblContagem);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarFiltro();
            }
        };

        campoPesquisa.getDocument().addDocumentListener(listenerFiltro);

        btnLimpar.addActionListener(e -> {
            campoPesquisa.setText("");
            sorter.setRowFilter(null);
            atualizarContagem(tabela, modelo, lblContagem);
        });

        JPanel rodape = new JPanel(new GridBagLayout());
        rodape.setBorder(BorderFactory.createTitledBorder("Cadastro Simples"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        rodape.add(new JLabel("ID:"), gbc);

        JTextField campoId = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        rodape.add(campoId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        rodape.add(new JLabel("Nome:"), gbc);

        JTextField campoNome = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        rodape.add(campoNome, gbc);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar Selecionado");
        JButton btnRemover = new JButton("Remover Selecionado");
        JButton btnLimparCampos = new JButton("Limpar Campos");
        botoes.add(btnAdicionar);
        botoes.add(btnEditar);
        botoes.add(btnRemover);
        botoes.add(btnLimparCampos);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        rodape.add(botoes, gbc);
        janela.add(rodape, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                String nome = campoNome.getText().trim();
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(janela, "Informe um nome válido.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                modelo.addRow(new Object[]{id, nome});
                campoId.setText("");
                campoNome.setText("");
                atualizarContagem(tabela, modelo, lblContagem);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(janela, "Informe um ID numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(janela, "Selecione uma linha para editar.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int linhaModelo = tabela.convertRowIndexToModel(linhaSelecionada);
            String nome = campoNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Informe um nome válido.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            modelo.setValueAt(nome, linhaModelo, 1);
            atualizarContagem(tabela, modelo, lblContagem);
        });

        btnRemover.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(janela, "Selecione uma linha para remover.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int linhaModelo = tabela.convertRowIndexToModel(linhaSelecionada);
            modelo.removeRow(linhaModelo);
            atualizarContagem(tabela, modelo, lblContagem);
        });

        btnLimparCampos.addActionListener(e -> {
            campoId.setText("");
            campoNome.setText("");
        });

        atualizarContagem(tabela, modelo, lblContagem);
        janela.setVisible(true);
    }

    // Atualiza o texto da label que mostra quantos registros estão
    // visíveis após a aplicação do filtro e quantos existem no total.
    private static void atualizarContagem(JTable tabela, DefaultTableModel modelo, JLabel lblContagem) {
        int total = modelo.getRowCount();
        int exibidos = tabela.getRowCount();
        lblContagem.setText(String.format("Exibidos: %d | Total: %d", exibidos, total));
    }
}