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

    private static int playerXcord = 300;
    private static int playerYcord = 315;
    private static int playerYVelo = 0;
    private static int playerAccel = 0;
    private static AudioClip jumpAudio, hitAudio;
    private static int BlockXcord = 900;
    private static int BlockXcord1 = BlockXcord + 300;
    private static int BlockXcord2 = BlockXcord1 + 300;
    private static int BlockXcord3 = BlockXcord2 + 300;
    private static int BlockYcord = 355;
    private static int BlockYcord1 = 355;
    private static int BlockYcord2 = 355;
    private static int BlockYcord3 = 280;
    private static int BlockXvelo = -10;
    private static JFrame f;
    private static int flag;

    public static void main(String[] args) {

        f = new JFrame();
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(800, 450));
        f.add(p);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p.setFocusable(true);
        p.requestFocus();

        p.addKeyListener(new Game());

        Image grass = null;
        Image[] player = new Image[8];
        Image jump = null;
        Image block = null;
        Image duck = null;

        try {
            grass = ImageIO.read(Game.class.getResource("images/grass.png"));
            player[0] = ImageIO.read(Game.class.getResource("images/run_anim1.png"));
            player[1] = ImageIO.read(Game.class.getResource("images/run_anim2.png"));
            player[2] = ImageIO.read(Game.class.getResource("images/run_anim3.png"));
            player[3] = ImageIO.read(Game.class.getResource("images/run_anim4.png"));
            player[4] = ImageIO.read(Game.class.getResource("images/run_anim5.png"));
            player[5] = ImageIO.read(Game.class.getResource("images/run_anim4.png"));
            player[6] = ImageIO.read(Game.class.getResource("images/run_anim3.png"));
            player[7] = ImageIO.read(Game.class.getResource("images/run_anim2.png"));
            jump = ImageIO.read(Game.class.getResource("images/jump.png"));
            jumpAudio = Applet.newAudioClip(Game.class.getResource("audios/onjump.wav"));
            hitAudio = Applet.newAudioClip(Game.class.getResource("audios/hit.wav"));
            block = ImageIO.read(Game.class.getResource("images/block.png"));
            duck = ImageIO.read(Game.class.getResource("images/duck.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int animCount = 0;
        Random r = new Random();

        Rectangle playerRect = new Rectangle();
        Rectangle blockRect = new Rectangle();
        Rectangle blockRect1 = new Rectangle();
        Rectangle blockRect2 = new Rectangle();
        Rectangle blockRect3 = new Rectangle();

        while (true) {

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            animCount++;
            animCount %= 8;


            BlockXcord += BlockXvelo;
            BlockXcord1 += BlockXvelo;
            BlockXcord2 += BlockXvelo;
            BlockXcord3 += BlockXvelo;

            if (playerYcord > 315) {
                playerYcord = 315;
                playerAccel = 0;
                playerYVelo = 0;
            }

            if (BlockXcord < -2) {
                BlockXcord = BlockXcord3 + 300;

                if (r.nextInt(2) == 0) {
                    BlockYcord = 355;
                } else {
                    BlockYcord = 280;
                }

            }

            if (BlockXcord1 < -2) {
                BlockXcord1 = BlockXcord + 300;

                if (r.nextInt(2) == 0) {
                    BlockYcord1 = 355;
                } else {
                    BlockYcord1 = 280;
                }

            }
            if (BlockXcord2 < -2) {
                BlockXcord2 = BlockXcord1 + 300;

                if (r.nextInt(2) == 0) {
                    BlockYcord2 = 355;
                } else {
                    BlockYcord2 = 280;
                }

            }
            if (BlockXcord3 < -2) {
                BlockXcord3 = BlockXcord2 + 300;

                if (r.nextInt(2) == 0) {
                    BlockYcord3 = 355;
                } else {
                    BlockYcord3 = 280;
                }

            }


            playerYVelo = playerYVelo + playerAccel;
            playerYcord = playerYcord + playerYVelo;


            playerRect.setBounds(playerXcord, playerYcord, 72, 97);
            blockRect.setBounds(BlockXcord, BlockYcord, 20, 50);
            blockRect1.setBounds(BlockXcord1, BlockYcord1, 20, 50);
            blockRect2.setBounds(BlockXcord2, BlockYcord2, 20, 50);
            blockRect3.setBounds(BlockXcord3, BlockYcord3, 20, 50);

            if (flag == 1) {
                playerRect.setBounds(playerXcord, playerYcord + 25, 72, 97);
            }

            if (playerRect.intersects(blockRect)) {
                playerXcord = playerXcord - 100;
                BlockXcord = BlockXcord3 + 300;
                hitAudio.play();
            }
            if (playerRect.intersects(blockRect1)) {
                playerXcord = playerXcord - 100;
                BlockXcord1 = BlockXcord + 300;
                hitAudio.play();
            }
            if (playerRect.intersects(blockRect2)) {
                playerXcord = playerXcord - 100;
                BlockXcord2 = BlockXcord1 + 300;
                hitAudio.play();
            }
            if (playerRect.intersects(blockRect3)) {
                playerXcord = playerXcord - 100;
                BlockXcord3 = BlockXcord2 + 300;
                hitAudio.play();
            }


            Graphics g = p.getGraphics();
            g.setColor(Color.cyan);
            g.fillRect(0, 0, 800, 450);


            g.drawImage(grass, 0, 405, null);

            if (playerYcord < 315) {
                g.drawImage(jump, playerXcord, playerYcord, null);
            } else if (flag == 1) {
                g.drawImage(duck, playerXcord, playerYcord, null);

            } else if (flag == 0) {
                g.drawImage(player[animCount], playerXcord, playerYcord, null);

            } else {
                g.drawImage(player[animCount], playerXcord, playerYcord, null);
            }

            g.drawImage(block, BlockXcord, BlockYcord, null);
            g.drawImage(block, BlockXcord1, BlockYcord1, null);
            g.drawImage(block, BlockXcord2, BlockYcord2, null);
            g.drawImage(block, BlockXcord3, BlockYcord3, null);


            if (playerXcord < -2) {

                g.setColor(Color.red);
                JPanel p1 = new JPanel();
                p1.setPreferredSize(new Dimension(800, 450));
                p1.setBackground(Color.red);
                JLabel l = new JLabel("Game Over");

                p1.add(l);
                f.add(p1);

                f.pack();
                f.setVisible(true);
            }


            g.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE && playerYcord == 315) {

            playerYVelo = -20;
            playerAccel = 2;
            jumpAudio.play();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            flag = 1;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            flag = 0;
        }
    }
}
