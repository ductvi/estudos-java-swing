import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class ExemploJTree {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Exemplo de JTree");
        janela.setSize(300,300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Pastas");
        DefaultMutableTreeNode documentos = new DefaultMutableTreeNode("Documentos");
        DefaultMutableTreeNode imagens = new DefaultMutableTreeNode("Imagens");

        raiz.add(documentos);
        raiz.add(imagens);

        JTree tree = new JTree(raiz);
        JScrollPane rolagem = new JScrollPane(tree);

        janela.add(rolagem);
        janela.setVisible(true);
    }
}