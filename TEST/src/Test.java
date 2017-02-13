import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test implements ActionListener {

    public static JLabel l1;
    public static JLabel l2;
    public static JButton b1;
    public static JButton b2;
    public static JPanel p1;
    public static JPanel p2;

    public static void main(String[] args) {

        Test t=new Test();
        JFrame f1 = new JFrame();
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(600, 300));
        l1 = new JLabel("Hello world!");
        b1 = new JButton("Change color");
        b1.addActionListener(t);


        JFrame f2 = new JFrame();
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(600, 300));
        l2 = new JLabel("Hello world!");
        b2 = new JButton("Change color");
        b2.addActionListener(t);

        p1.add(l1);
        p1.add(b1);
        f1.add(p1);
        f1.pack();
        f1.setVisible(true);

        p2.add(l2);
        p2.add(b2);
        f2.add(p2);
        f2.pack();
        f2.setVisible(true);


    }

    public void color1() {
        l1.setForeground(Color.CYAN);
        p1.setBackground(Color.black);
    }

    public void color2() {
        l2.setForeground(Color.CYAN);
        p2.setBackground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            color2();
        }

        else if(e.getSource()==b2){
            color1();
        }
    }
}