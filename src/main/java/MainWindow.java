import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("Snake-game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(325, 345);
        setLocation(400 ,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();


    }
}
