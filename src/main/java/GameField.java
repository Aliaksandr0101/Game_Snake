import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final  int DOT_SIZE = 16;
    private final  int ALL_DOTS = 400;

    private Image dot;
    private Image apple;

    private int [] x = new int [ALL_DOTS];
    private int [] y = new int [ALL_DOTS];
    private int [] arrayAppleY = new int [3];
    private int [] arrayAppleX = new int [3];

    private int appleX;
    private int appleY;
    private int dots;
    private Timer timer;

    private boolean inGame = true;

    public void loadImage(){
        ImageIcon iia = new ImageIcon("apple.jpg");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.jpg");
        dot = iid.getImage();
    }

    public void createApple(){
        for (int i = 0; i < 3 ; i++) {
            appleX = new Random().nextInt(20) * DOT_SIZE;
            appleY = new Random().nextInt(20) * DOT_SIZE;
            arrayAppleX[i] = appleX;
            arrayAppleY[i] = appleY;
        }
    }
    public void initGame(){
        dots = 3;
        for (int i = 0; i < dots ; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
    }
    public void checkApple(){

            if (x[0] == arrayAppleX[0] && y[0] == arrayAppleY[0]) {
                dots++;
                createApple();
            }
            if (x[0] == arrayAppleX[1] && y[0] == arrayAppleY[1]) {
                dots++;
                createApple();
            }
            if (x[0] == arrayAppleX[2] && y[0] == arrayAppleY[2]) {
                dots++;
                createApple();
            }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame){
            for (int i = 0; i < 3 ; i++) {
                g.drawImage(apple, arrayAppleX[i], arrayAppleY[i], this);
            }

            for (int i = 0; i < dots ; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }else {
            String str = "GAME OVER";
            g.setColor(Color.CYAN);
            g.drawString(str, SIZE/6, SIZE/2);
        }
    }

    public void checkCollision(){

           for (int i = 0; i < dots ; i++) {
              if (x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
          }


         if (x[0] > SIZE)
             x[0] = 0;

         if (x[0] < 0)
            x[0] = SIZE;

         if (y[0] > SIZE)
             y[0] = 0;

         if (y[0] < 0)
             y[0] = SIZE;
    }
    }

