import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ExemploJTable {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplo JTable Melhorado");

        janela.setSize(600,400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout(10,10));

        String[] colunas = {"ID", "Nome"};

        Object[][] dados = {
            {1,"Nicolly"},
            {2,"Bruno"},
            {3, "Carlos"}
        };

        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);

        JTable tabela = new JTable(modelo);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabela.setRowHeight(25);

        TableColumnModel colunasTabela = tabela.getColumnModel();
        colunasTabela.getColumn(0).setPreferredWidth(50);
        colunasTabela.getColumn(1).setPreferredWidth(200);

        JScrollPane rolagem = new JScrollPane(tabela);

        JPanel painelBtn = new JPanel(new FlowLayout());

        JButton btnShow = new JButton("Mostrar Selecionado");

        btnShow.addActionListener(e -> {
            int linha = tabela.getSelectedRow();

            if(linha != -1){
                int id = (int) tabela.getValueAt(linha, 0);
                String nome = (String) tabela.getValueAt(linha, 1);
                JOptionPane.showMessageDialog(janela, "ID: " + id + " | Nome: " + nome); 
            } else {
                JOptionPane.showMessageDialog(janela, "Nenhuma linha selecionada!");
            }
        });

        JButton btnAdd = new JButton("Adicionar Linha");

        btnAdd.addActionListener(e -> {
            int proximoId = modelo.getRowCount() + 1;
            modelo.addRow(new Object[]{proximoId, "Nome novo" + proximoId});
        });

        painelBtn.add(btnShow);
        painelBtn.add(btnAdd);
        janela.add(rolagem, BorderLayout.CENTER);
        janela.add(painelBtn, BorderLayout.SOUTH);
        janela.setVisible(true);
    }
}
