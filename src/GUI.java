import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;

public class GUI extends JPanel implements ActionListener {

    private Flock flock;

    private Panel drawPanel;
    private JButton addBoidBtn, add20BoidsBtm;
    private Timer timer;

    private int width = 800;
    private int height = 600;

    public GUI() {
        super(new BorderLayout());

        this.flock = new Flock(5, width, height);

        JPanel controlPanel = new JPanel();

        addBoidBtn = new JButton("Add Boid");
        addBoidBtn.addActionListener(this);
        controlPanel.add(addBoidBtn);

        add20BoidsBtm = new JButton("Add 20 Boids");
        add20BoidsBtm.addActionListener(this);
        controlPanel.add(add20BoidsBtm);
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

        if (source == addBoidBtn) {
            System.out.println("addBoid pressed");
            flock.addBoid();

        } else if (source == add20BoidsBtm) {
            System.out.println("add 20 Boids pressed");

            flock.addBoids(20);
        }

        flock.run();

        drawPanel.repaint();
    }

    private class Panel extends JPanel {
        public Panel(){
            super();
            super.setBackground(Color.DARK_GRAY);
            super.setPreferredSize(new Dimension(width, height));      
        }
        //draws the panel
        public void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1;

            flock.drawFlock(g);
        }
    }
}
