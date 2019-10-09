import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JPanel implements ActionListener {

    private Flock flock;

    private Panel drawPanel;
    private JButton btnOne, btnTwo;
    private Timer timer;

    public GUI(Flock flock) {
        super(new BorderLayout());

        this.flock = flock;

        JPanel controlPanel = new JPanel();

        btnOne = new JButton("New");
        btnOne.addActionListener(this);
        controlPanel.add(btnOne);

        btnTwo = new JButton("Start");
        btnTwo.addActionListener(this);
        controlPanel.add(btnTwo);
        controlPanel.setBorder(new TitledBorder("Controls"));

        this.add(controlPanel, BorderLayout.SOUTH);

        drawPanel = new Panel();
        this.add(drawPanel,BorderLayout.CENTER);

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == btnOne) {
            System.out.println("btnOne pressed");

        } else if (source == btnTwo) {
            System.out.println("btnTwo pressed");
        }

        drawPanel.repaint();
    }

    private class Panel extends JPanel {
        public Panel(){
            super();
            super.setBackground(Color.DARK_GRAY);
            super.setPreferredSize(new Dimension(800,600));      
        }
        //draws the panel
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            flock.drawFlock(g);
            g.fillRect(10, 10, 50, 50);
        }
    }
}
