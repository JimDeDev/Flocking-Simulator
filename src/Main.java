import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main { 

    public static void main(String[] args) {

        // Setting the look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Jaime's GUI App");
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2,
            (screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
    }
}