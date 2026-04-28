import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ExemploCRUDLista {

    // Classe interna para representar o objeto de dados
    static class Item {
        final int id; // ID único para cada item
        String nome;

        Item(int id, String nome){
            this.id = id;
            this.nome = nome;
        }

        // Sobrescrito para que o JList exiba apenas o nome do item
        @Override
        public String toString() {
            return nome;
        }
    }

    // --- Atributos da Classe ---
    private final List<Item> dados = new ArrayList<>(); // Nossa "Base de Dados" em memória
    private int proximoId = 1;                           // Gerador de ID incremental
    private JFrame janela;
    private JList<Item> lista;                           // Componente visual da lista
    private DefaultListModel<Item> modeloFiltrado;       // O "motor" que controla o que aparece na JList
    private JTextField campoNome;                        // Campo para digitar/editar nomes
    private JTextField campoPesquisa;                    // Campo de busca
    private JLabel rotuloContagem;                       // Exibe a quantidade de itens
    
    public static void main(String[] args) {
        // Inicia a aplicação na Thread de Eventos do Swing (boa prática de segurança de threads)
        SwingUtilities.invokeLater(() -> new ExemploCRUDLista().iniciar());
    }

    private void iniciar(){
        // 1. Configuração Básica da Janela
        janela = new JFrame("JList - CRUD + Pesquisa (Exemplo Didático)");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(800,500);
        janela.setLocationRelativeTo(null); // Centraliza na tela
        janela.setLayout(new BorderLayout(10,10)); // Layout principal com espaçamento

        // Dados iniciais para teste
        adicionarItemInicial("Maça");
        adicionarItemInicial("Banana");
        adicionarItemInicial("Laranja");
        adicionarItemInicial("Uva");
        adicionarItemInicial("Pera");

        // 2. Painel Superior (Pesquisa e Contagem)
        JPanel painelTopo = new JPanel(new BorderLayout(8,8));

        JPanel painelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT, 8,8));
        painelPesquisa.add(new JLabel("Pesquisa: "));
        campoPesquisa = new JTextField(25);
        JButton limparBusca = new JButton("Limpar");
        painelPesquisa.add(campoPesquisa);
        painelPesquisa.add(limparBusca);

        rotuloContagem = new JLabel("Itens: 0");
        JPanel painelDireitaTopo = new JPanel(new FlowLayout(FlowLayout.RIGHT,8,8));
        painelDireitaTopo.add(rotuloContagem);

        painelTopo.add(painelPesquisa, BorderLayout.WEST);
        painelTopo.add(painelDireitaTopo, BorderLayout.EAST);
        janela.add(painelTopo, BorderLayout.NORTH);

        // 3. Configuração da Listagem (Centro)
        modeloFiltrado = new DefaultListModel<>(); // Modelo vazio que será populado pelo filtro
        lista = new JList<>(modeloFiltrado);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um por vez
        lista.setVisibleRowCount(12); 
        lista.setFixedCellHeight(24);
        
        // Evento: Clique duplo na lista preenche o campo de texto para edição
        lista.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    Item sel = lista.getSelectedValue();
                    if(sel != null) campoNome.setText(sel.nome);
                }
            }
        });

        // Evento: Tecla Delete exclui o item selecionado
        lista.addKeyListener(new KeyAdapter() {
           @Override public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_DELETE) excluirSelecionado();
           } 
        });

        JScrollPane rolagem = new JScrollPane(lista); // Adiciona barra de rolagem à lista
        janela.add(rolagem, BorderLayout.CENTER);

        // 4. Painel de Cadastro (Rodapé)
        JPanel painelRodape = new JPanel(new GridBagLayout()); // GridBagLayout para melhor alinhamento
        painelRodape.setBorder(BorderFactory.createTitledBorder("Cadastro Simples"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1.0;
        painelRodape.add(new JLabel("Nome: "), gbc);

        campoNome = new JTextField(30);
        gbc.gridx = 1;
        painelRodape.add(campoNome, gbc);

        // Sub-painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
        JButton btnAdd = new JButton("Adicionar");
        JButton btnAlt = new JButton("Alterar");
        JButton btnDel = new JButton("Excluir");
        JButton btnCln = new JButton("Limpar");

        painelBotoes.add(btnAdd);
        painelBotoes.add(btnAlt);
        painelBotoes.add(btnDel);
        painelBotoes.add(btnCln);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.weightx = 0;
        painelRodape.add(painelBotoes, gbc);

        janela.add(painelRodape, BorderLayout.SOUTH);

        // --- Lógica de Eventos e Listeners ---

        // Monitora mudanças no campo de pesquisa em tempo real
        campoPesquisa.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { aplicarfiltro(); }
            @Override public void removeUpdate(DocumentEvent e) { aplicarfiltro(); }
            @Override public void changedUpdate(DocumentEvent e) { aplicarfiltro(); }
        });

        limparBusca.addActionListener(e -> {
            campoPesquisa.setText("");
            aplicarfiltro();
        });

        // CREATE: Adiciona novo item à lista original e atualiza a visão
        btnAdd.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if(nome.isEmpty()){
                JOptionPane.showMessageDialog(janela, "Digite um nome para adicionar.");
                return;
            }
            dados.add(new Item(proximoId++, nome));
            campoNome.setText("");
            aplicarfiltro(); // Recarrega a JList
            JOptionPane.showMessageDialog(janela, "Item adicionado com sucesso!");
        });

        // UPDATE: Altera o item selecionado
        btnAlt.addActionListener(e -> alterarSelecionado());

        // DELETE: Remove o item selecionado
        btnDel.addActionListener(e -> excluirSelecionado());

        // Limpa o campo de texto
        btnCln.addActionListener(e -> campoNome.setText(""));

        // Inicializa a lista visual
        aplicarfiltro();
        janela.setVisible(true);
    }

    // Auxiliar para preencher a lista inicialmente
    private void adicionarItemInicial(String nome){
        dados.add(new Item(proximoId++, nome));
    }

    /**
     * MÉTODO CHAVE: Sincroniza a lista de dados (List) com o modelo visual (JList).
     * Ele varre a lista original e decide o que deve ser exibido com base na pesquisa.
     */
    private void aplicarfiltro(){
        //Cria uma string chamada termo, que pega o texto do Campo de Pesquisa
        // Utilizamos o trim() para remover espaços no início e no fim
        //e toLowerCase para deixar todas as letras minúsculas.
        String termo = campoPesquisa == null ? "" : campoPesquisa.getText().trim().toLowerCase();
        modeloFiltrado.clear(); // Limpa a visão atual

        for(Item it : dados){
            // Se a pesquisa estiver vazia ou o nome contiver o termo...
            if(termo.isEmpty() || it.nome.toLowerCase().contains(termo)){
                modeloFiltrado.addElement(it); // Adiciona ao modelo que a JList usa
            }
        }
        atualizarContagem();
    }

    private void atualizarContagem(){
        //Contador da lista
        rotuloContagem.setText("Itens exibidos: " + modeloFiltrado.getSize() + " | Total: " + dados.size());
    }

    private void alterarSelecionado(){
        Item sel = lista.getSelectedValue(); // Obtém o objeto selecionado na JList
        if(sel == null){
            JOptionPane.showMessageDialog(janela, "Selecione um item para alterar.");
            return;
        }
        String novoNome = campoNome.getText().trim();
        if(novoNome.isEmpty()){
            JOptionPane.showMessageDialog(janela, "Digite o novo nome no campo 'Nome'.");
            return;
        }
        sel.nome = novoNome; // Atualiza a referência do objeto na lista original
        aplicarfiltro();     // Reflete a mudança visualmente
        JOptionPane.showMessageDialog(janela, "Item alterado com sucesso!");
    }

    private void excluirSelecionado(){
        Item sel = lista.getSelectedValue();
        if(sel == null){
            JOptionPane.showMessageDialog(janela, "Selecione um item para Excluir.");
            return;
        }
        int op = JOptionPane.showConfirmDialog(janela, "Excluir \"" + sel.nome + "\"?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if(op == JOptionPane.YES_OPTION){
            // Remove da lista original comparando IDs
            dados.removeIf(i -> i.id == sel.id);
            aplicarfiltro(); // Atualiza a interface
            JOptionPane.showMessageDialog(janela, "Item excluído com sucesso!");
        }
    }
}