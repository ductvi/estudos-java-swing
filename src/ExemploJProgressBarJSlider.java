import java.awt.*;
import javax.swing.*;
public class ExemploJProgressBarJSlider {
    public static void main(String[] args) {
        JFrame f = new JFrame("JProgressBar + JSlider");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,250);
        f.setLayout(new BorderLayout(10,10));

        JProgressBar progresso = new JProgressBar(0,100);

        progresso.setStringPainted(true);

        JSlider slider = new JSlider(0,100,0);

        slider.addChangeListener(e -> {
            progresso.setValue(slider.getValue());
        });

        JButton simular = new JButton("Simular tarefa (0 -> 100)");

        simular.addActionListener(e -> {
            simular.setEnabled(false);
            new SwingWorker<Void, Integer>() {

                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);
                        publish(i);
                    }
                    return null;
                }
                
                @Override
                protected void process(java.util.List<Integer>  chunks){
                    int v = chunks.get(chunks.size() - 1);
                    progresso.setValue(v);
                    slider.setValue(v);
                }

                @Override
                protected void done() {
                    simular.setEnabled(true);
                }
            }.execute();
        });

        JPanel centro = new JPanel(new GridLayout(2,1,8,8));

        centro.add(progresso);
        centro.add(slider);

        f.add(centro, BorderLayout.CENTER);
        f.add(simular, BorderLayout.SOUTH);
        f.setVisible(true);
    }
}