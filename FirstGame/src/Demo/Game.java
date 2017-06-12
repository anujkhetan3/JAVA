package Demo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;


public class Game implements KeyListener {

    private static int playerYCord = 315;
    private static int playerYVel = 0;
    private static int playerYAcc = 0;
    private static AudioClip jumpAudio;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(800, 450));
        f.add(p);
        f.pack();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p.addKeyListener(new Game());
        p.setFocusable(true);
        p.requestFocus();

        Image grassImage;
        Image[] playerImages = new Image[8];
        Image jumpImage = null;
        Image blockImage = null;
        AudioClip hitAudio = null;
        try {
            grassImage = ImageIO.read(Game.class.getResource("images/grass.png"));
            playerImages[0] = ImageIO.read(Game.class.getResource("images/run_anim1.png"));
            playerImages[1] = ImageIO.read(Game.class.getResource("images/run_anim2.png"));
            playerImages[2] = ImageIO.read(Game.class.getResource("images/run_anim3.png"));
            playerImages[3] = ImageIO.read(Game.class.getResource("images/run_anim4.png"));
            playerImages[4] = ImageIO.read(Game.class.getResource("images/run_anim5.png"));
            playerImages[5] = ImageIO.read(Game.class.getResource("images/run_anim4.png"));
            playerImages[6] = ImageIO.read(Game.class.getResource("images/run_anim3.png"));
            playerImages[7] = ImageIO.read(Game.class.getResource("images/run_anim2.png"));
            jumpImage = ImageIO.read(Game.class.getResource("images/jump.png"));
            blockImage = ImageIO.read(Game.class.getResource("images/block.png"));
            Game.jumpAudio = Applet.newAudioClip(Game.class.getResource("audios/onjump.wav"));
            hitAudio = Applet.newAudioClip(Game.class.getResource("audios/hit.wav"));
        } catch (IOException e) {
            System.out.println("unable to load image");
            return;
        }

        int currentIndex = 0;
        int blockXCord = 900;
        int blockYCord = 355;
        int playerXCord = 400;
        Random r = new Random();

        Rectangle playerRect = new Rectangle();
        Rectangle blockRect = new Rectangle();


        boolean blockVisible = true;

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                continue;
            }


            currentIndex++;
            currentIndex %= 8;

            Game.playerYVel += Game.playerYAcc;
            Game.playerYCord = Game.playerYCord + Game.playerYVel;

            if(Game.playerYCord > 315) {
                Game.playerYCord = 315;
                Game.playerYAcc = 0;
                Game.playerYVel = 0;
            }

            blockXCord -= 5;

            if(blockXCord < -20) {
                blockXCord = 900;
                blockVisible = true;

                if(r.nextInt(2) == 0) {
                    blockYCord = 355;
                } else {
                    blockYCord = 295;
                }
            }


            playerRect.setBounds(playerXCord, Game.playerYCord, 72, 90);
            blockRect.setBounds(blockXCord, blockYCord, 20, 50);

            if(blockVisible && playerRect.intersects(blockRect)) {
                hitAudio.play();
                playerXCord -= 100;
                blockVisible = false;
            }



            Graphics g = p.getGraphics();
            g.setColor(Color.blue);
            g.fillRect(0,0,800,450);
            g.drawImage(grassImage,0,405,null);

            if(Game.playerYCord < 315) {
                g.drawImage(jumpImage,playerXCord,Game.playerYCord,null);
            } else {
                g.drawImage(playerImages[currentIndex], playerXCord, Game.playerYCord, null);
            }

            if(blockVisible) {
                g.drawImage(blockImage, blockXCord, blockYCord, null);
            }
            g.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && Game.playerYCord == 315) {
            Game.jumpAudio.play();
            Game.playerYVel = -20;
            Game.playerYAcc = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
