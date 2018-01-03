package snw.tests;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class NewGUI extends JPanel {

    private static final int S1 = 20;
    private static final int S2 = 100;
    private JLabel label = new JLabel("Hello, world!");

    public NewGUI() {
        label.setHorizontalAlignment(JLabel.CENTER);
        Border inner = BorderFactory.createEmptyBorder(S1, S1, S1, S1);
        Border outer = BorderFactory.createLineBorder(Color.black);
        label.setBorder(new CompoundBorder(outer, inner));
        this.setBorder(BorderFactory.createEmptyBorder(S2, S2, S2, S2));
        this.add(label);
    }

    private void display() {
        JFrame f = new JFrame("NewGUI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        //f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new NewGUI().display();
            }
        });
    }
}