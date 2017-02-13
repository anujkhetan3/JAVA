import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnujJFrame extends JFrame implements ActionListener {

    public JLabel l;
    public JButton b1;
    public JButton b2;
    public JPanel p;
    public AnujJFrame[] children = new AnujJFrame[2];
    public AnujJFrame[] Grandchildren = new AnujJFrame[2];
    public int count = 0;
    public int i;

    public AnujJFrame() {


        p = new JPanel();
      //  p.setPreferredSize(new Dimension(600, 300));

        l = new JLabel("Hello World!!");

        b1 = new JButton("Change color");
        b1.addActionListener(this);

        b2 = new JButton("New Window");
        b2.addActionListener(this);

        p.add(l);
        p.add(b1);
        p.add(b2);
        this.add(p);
        this.pack();


    }

    public void changeColor() {

        l.setForeground(Color.cyan);
        p.setBackground(Color.black);
        for (i = 0; i < count; i++) {

            children[i].l.setForeground(Color.cyan);
            children[i].p.setBackground(Color.black);

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            changeColor();
        } else if (e.getSource() == b2) {
            AnujJFrame frame = new AnujJFrame();
            children[count] = frame;
            count++;

            frame.setVisible(true);

        }
    }
}
