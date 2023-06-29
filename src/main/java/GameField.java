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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (inGame) {

        }
    }
}
