package Demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Animation implements KeyListener {

    public static int xVel = 3;
    public static int yVel = 0;

    public static void main(String[] args) {

        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(800, 450));

        f.add(p);
        f.pack();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);


        int x = 0;
        int y = 0;

        p.setFocusable(true);
        p.addKeyListener(new Animation());
        p.requestFocus();

        while (true) {
            Graphics g = p.getGraphics();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.clearRect(0, 0, 800, 450);
            g.setColor(Color.red);
            x += xVel;
            y += yVel;

            if (x > 700) {
                x = 700;
                xVel = 0;
            }
            if (y > 350) {
                y = 350;
                yVel = 0;
            }
            if (x < 0) {
                x = 0;
                xVel = 0;
            }
            if (y < 0) {
                y = 0;
                yVel = 0;
            }
            // g.drawRect(400,200,100,100);
            //g.setColor(Color.red);
            //g.fillRect(200,150,200,200);
            //g.setColor(Color.cyan);
            //g.fillOval(0,0,50,100);
            g.fillArc(x, y, 100, 100, 0, 360);
            g.dispose();      //Brush gets destroyed
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {



        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Animation.xVel = 3;
            Animation.yVel = 0;
            System.out.println("Right key pressed");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Animation.xVel = -3;
            Animation.yVel = 0;
            System.out.println("Left key pressed");
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            Animation.xVel = 0;
            Animation.yVel = -3;
            System.out.println("Up key pressed");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Animation.xVel = 0;
            Animation.yVel = 3;
            System.out.println("Down key pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
