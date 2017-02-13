import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnujJFrame extends JFrame implements ActionListener {

    public JPanel p;
    public JLabel l;
    public JButton b1;
    public JButton b2;

    public AnujJFrame() {

        p = new JPanel();
        l = new JLabel("Hello World!!");

        b1 = new JButton("Change Color");
        b1.addActionListener(this);

        b2 = new JButton("New Window");
        b2.addActionListener(this);

        p.add(l);
        p.add(b1);
        p.add(b2);
        this.add(p);
        this.pack();
        this.setVisible(true);
    }

    public void changeColor() {

        l.setForeground(Color.cyan);
        p.setBackground(Color.black);

    }

    public void newWindow() {
        new AnujJFrame();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            changeColor();
        } else if (e.getSource() == b2) {
            newWindow();
        }
    }
}
