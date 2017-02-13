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

    private static int playerXcord = 93;
    private static int playerYcord = 293;
    private static int playerYVelo = 0;
    private static int playerAccel = 0;
    private static int logXcord = 100;
    private static int logXvelo = 0;
    private static int branchXcord = 1100;
    private static int branchXvelo;
    private static int branchYcord = 250;
    private static AudioClip flap, backSound, hit;
    private static int flag;
    private static int keyFlag;
    private static int HeadFlag;
    private static int forkHandleXcord = 1500;
    private static int forkHandleXvelo;
    private static int forkHandleYcord = 250;
    private static int forkHeadXcord = 1500;
    private static int forkHeadYcord = 195;
    private static int forkHeadInvertedXcord;
    private static int forkHeadInvertedYcord;
    private static int flapKey;
    private static JFrame f;

    public static void main(String[] args) {

        f = new JFrame();
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(1000, 500));
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


        Image[] player = new Image[8];
        Image background = null;
        Image ground = null;
        Image log = null;
        Image branch = null;
        Image forkHandle = null;
        Image forkHead = null;
        Image forkHeadInverted = null;
        Image playerInverted = null;


        try {

            player[0] = ImageIO.read(Game.class.getResource("images/player1.png"));
            player[1] = ImageIO.read(Game.class.getResource("images/player2.png"));
            player[2] = ImageIO.read(Game.class.getResource("images/player3.png"));
            player[3] = ImageIO.read(Game.class.getResource("images/player4.png"));
            player[4] = ImageIO.read(Game.class.getResource("images/player5.png"));
            player[5] = ImageIO.read(Game.class.getResource("images/player6.png"));
            player[6] = ImageIO.read(Game.class.getResource("images/player7.png"));
            player[7] = ImageIO.read(Game.class.getResource("images/player8.png"));
            background = ImageIO.read(Game.class.getResource("images/bg_combined.png"));
            ground = ImageIO.read(Game.class.getResource("images/ground.png"));
            log = ImageIO.read(Game.class.getResource("images/log.png"));
            flap = Applet.newAudioClip(Game.class.getResource("audios/flap.wav"));
            branch = ImageIO.read(Game.class.getResource("images/branch.png"));
            forkHandle = ImageIO.read(Game.class.getResource("images/fork_handle.png"));
            forkHead = ImageIO.read(Game.class.getResource("images/fork_head.png"));
            forkHeadInverted = ImageIO.read(Game.class.getResource("images/fork_head_inverted.png"));
            playerInverted = ImageIO.read(Game.class.getResource("images/pappu1Inverted.png"));
            backSound = Applet.newAudioClip(Game.class.getResource("audios/pappu-pakia2.3.wav"));
            hit = Applet.newAudioClip(Game.class.getResource("audios/jump2.wav"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Random r = new Random();
        Random r1 = new Random();


        int count = 0;

        Rectangle branchRectangle = new Rectangle();
        Rectangle playerRectangle = new Rectangle();
        Rectangle logRectangle = new Rectangle();
        Rectangle forkHandleRectangle = new Rectangle();
        Rectangle forkHeadRectangle = new Rectangle();
        Rectangle forkHeadInvertedRectangle = new Rectangle();

        backSound.loop();


        while (true) {

            try {
                Thread.sleep(18);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            count %= 8;

            playerYVelo = playerYVelo + playerAccel;
            playerYcord = playerYcord + playerYVelo;

            branchXcord += branchXvelo;
            forkHandleXcord += forkHandleXvelo;
            logXcord += logXvelo;
            forkHeadXcord = forkHandleXcord - 5;
            forkHeadInvertedXcord = forkHandleXcord - 3;


            if (branchXcord < -2) {
                branchXcord = 1100;
                if (r.nextInt(4) == 0) {
                    branchYcord = -300;
                    System.out.println("0");
                } else if (r.nextInt(4) == 1) {
                    branchYcord = -200;
                    System.out.println("1");
                } else if (r.nextInt(4) == 2) {
                    branchYcord = 300;
                    System.out.println("2");
                } else {
                    branchYcord = 200;
                    System.out.println("3");
                }
            }


            if (forkHandleXcord < -22) {
                forkHandleXcord = branchXcord + 400;
                if (r1.nextInt(4) == 0) {
                    forkHandleYcord = 0;
                    forkHeadInvertedYcord = 307;
                    HeadFlag = 2;
                } else if (r1.nextInt(4) == 1) {
                    forkHandleYcord = -100;
                    forkHeadInvertedYcord = 207;
                    HeadFlag = 2;
                } else if (r1.nextInt(4) == 2) {
                    forkHandleYcord = 160;
                    forkHeadYcord = 105;
                    HeadFlag = 1;
                } else {
                    forkHandleYcord = 250;
                    forkHeadYcord = 195;
                    HeadFlag = 1;
                }
            }
            playerRectangle.setBounds(playerXcord, playerYcord, 60, 56);
            branchRectangle.setBounds(branchXcord, branchYcord, 31, 500);
            logRectangle.setBounds(logXcord, 330, 71, 119);
            forkHandleRectangle.setBounds(forkHandleXcord, forkHandleYcord, 22, 312);
            forkHeadRectangle.setBounds(forkHeadXcord, forkHeadYcord, 33, 59);
            forkHeadInvertedRectangle.setBounds(forkHeadInvertedXcord, forkHeadInvertedYcord, 33, 59);


            if (playerRectangle.intersects(branchRectangle)) {
                flag = 1;
                keyFlag = 1;
                playerYVelo = 20;
                playerAccel = 0;
                branchXvelo = 0;
                forkHandleXvelo = 0;


            }
            if (playerRectangle.intersects(forkHandleRectangle)) {
                flag = 1;
                keyFlag = 1;
                playerYVelo = 20;
                playerAccel = 0;
                branchXvelo = 0;
                forkHandleXvelo = 0;

            }
           /* if(playerRectangle.intersects(forkHeadRectangle)){
                flag = 1;
                keyFlag = 1;
                playerYVelo = 10;
                playerAccel = 0;
                branchXvelo = 0;
                forkHandleXvelo = 0;
            }
            if(playerRectangle.intersects(forkHeadInvertedRectangle)){
                flag = 1;
                keyFlag = 1;
                playerYVelo = 10;
                playerAccel = 0;
                branchXvelo = 0;
                forkHandleXvelo = 0;
            }*/


            Graphics g = p.getGraphics();


            g.drawImage(background, 0, 0, null);
            g.drawImage(log, logXcord, 330, null);
            g.drawImage(ground, 0, 0, null);

            if (logRectangle.intersects(playerRectangle)) {
                g.drawImage(player[1], playerXcord, playerYcord, null);
            } else if (flag == 1) {
                g.drawImage(playerInverted, playerXcord, playerYcord, null);
                hit.play();
            } else if (keyFlag == 2) {
                g.drawImage(player[count], playerXcord, playerYcord, null);
            }

            g.drawImage(branch, branchXcord, branchYcord, null);
            g.drawImage(forkHandle, forkHandleXcord, forkHandleYcord, null);
            if (HeadFlag == 1) {
                g.drawImage(forkHead, forkHeadXcord, forkHeadYcord, null);
            } else if (HeadFlag == 2) {
                g.drawImage(forkHeadInverted, forkHeadInvertedXcord, forkHeadInvertedYcord, null);
            }


            if (playerYcord < -48 || playerYcord > 500) {

                keyFlag = 1;
                playerYVelo = 0;
                playerAccel = 0;
                branchXvelo = 0;
                forkHandleXvelo = 0;

             /*   g.setColor(Color.red);
                JPanel p1 = new JPanel();
                p1.setPreferredSize(new Dimension(1000, 500));
                p1.setBackground(Color.red);
                JLabel l = new JLabel("Game Over");

                p1.add(l);
                f.add(p1);

                f.pack();
                f.setVisible(true);*/
            }
            g.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (keyFlag == 1) {

            } else {
                keyFlag = 2;
                playerYVelo = -10;
                playerAccel = 1;
                logXvelo = -10;
                branchXvelo = -10;
                forkHandleXvelo = -10;
                forkHandleXvelo = -10;
                flap.play();
                backSound.stop();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            flapKey = 1;
        }

    }


}
