import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final  int DOT_SIZE = 16;
    private final  int ALL_DOTS = 400;

    private Image snake;
    private Image apple;

    private int [] x = new int [ALL_DOTS];
    private int [] y = new int [ALL_DOTS];
    private int [] arrayAppleY = new int [3];
    private int [] arrayAppleX = new int [3];

    private int sizeSnake;
    private Timer timer;


    private boolean inGame = true;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    public void loadImage(){
        ImageIcon addPictureApple = new ImageIcon("apple.jpg");
        apple = addPictureApple.getImage();
        ImageIcon addPictureSnake = new ImageIcon("snake.jpg");
        snake = addPictureSnake.getImage();
    }

    public void createApple(){
        for (int i = 0; i < 3 ; i++) {
            arrayAppleX[i] = new Random().nextInt(19) * DOT_SIZE;
            arrayAppleY[i] = new Random().nextInt(19) * DOT_SIZE;
        }
    }
    public void initGame(){
        sizeSnake = 3;
        for (int i = 0; i < sizeSnake ; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(150, this);
        timer.start();
        createApple();
    }
    public void checkApple(){

        for (int i = 0; i < 3 ; i++) {

            if (x[0] == arrayAppleX[i] && y[0] == arrayAppleY[i]) {
                sizeSnake++;
                createApple();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame){
            for (int i = 0; i < 3 ; i++) {
                g.drawImage(apple, arrayAppleX[i], arrayAppleY[i], this);
            }

            for (int i = 0; i < sizeSnake ; i++) {
                g.drawImage(snake, x[i], y[i], this);
            }
        }else {
            String str = "end of this game";
            g.setColor(Color.WHITE);
            g.drawString(str, SIZE/3, SIZE/3);
        }
    }

    public void checkCollision(){

           for (int i = sizeSnake; i > 0 ; i--) {
              if (x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
          }

         if (x[0] > SIZE-1)
             x[0] = 0;

         if (x[0] < 0)
            x[0] = SIZE-1;

         if (y[0] > SIZE)
             inGame = false;

         if (y[0] < 0)
             inGame = false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();;
            checkCollision();;
            move();
        }
        repaint();
    }

    public  GameField(){
        setBackground(Color.BLACK);
        loadImage();
        initGame();
        addKeyListener(new FiledKeyListener());
        setFocusable(true);
    }
    public void  move(){
        for (int i = sizeSnake; i > 0 ; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left)
            x[0] -= DOT_SIZE;
        if (right)
            x[0] += DOT_SIZE;
        if (up)
            y[0] -= DOT_SIZE;
        if (down)
            y[0] += DOT_SIZE;
    }

    class FiledKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            }
        }
    }
}



