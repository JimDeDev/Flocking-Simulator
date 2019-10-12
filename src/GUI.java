import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;

public class GUI extends JPanel implements ActionListener {

    private Flock flock;

    private Panel drawPanel;
    private JButton add20BoidsBtm, killBoidsBtn;
    private JCheckBox alignment, cohesion, separation;
    private Timer timer;

    private int width = 800;
    private int height = 600;

    public GUI() {
        super(new BorderLayout());

        this.flock = new Flock(width, height);
        this.flock.addBoids(5);

        JPanel controlPanel = new JPanel();

        add20BoidsBtm = new JButton("Add Boids!");
        add20BoidsBtm.addActionListener(this);
        controlPanel.add(add20BoidsBtm);

        killBoidsBtn = new JButton("Kill All Boids :(");
        killBoidsBtn.addActionListener(this);
        controlPanel.add(killBoidsBtn);

        alignment = new JCheckBox("Alignment");
        alignment.addActionListener(this);
        controlPanel.add(alignment);

        separation = new JCheckBox("Separation");
        separation.addActionListener(this);
        controlPanel.add(separation);

        cohesion = new JCheckBox("Cohesion");
        cohesion.addActionListener(this);
        controlPanel.add(cohesion);

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

        if (source == add20BoidsBtm) {
            SpinnerModel model = new SpinnerNumberModel(50, 1, 1000, 10);
            JSpinner spinner = new JSpinner(model);

            int result = JOptionPane.showConfirmDialog(this, spinner, "Add Boids!", JOptionPane.OK_CANCEL_OPTION);

            if(result == JOptionPane.OK_OPTION) {
                flock.addBoids((int) spinner.getValue());
            }
        } else if (source == killBoidsBtn) {
            flock.killAll();
        } else if (source == alignment) {
            flock.setAlignment(alignment.isSelected());
        } else if (source == separation) {
            flock.setSeparation(separation.isSelected());
        } else if (source == cohesion) {
            flock.setCohesion(cohesion.isSelected());
        } 

        updateWindowSize(); 


        flock.run();

        drawPanel.repaint();
    }

    private void updateWindowSize() {

        this.width = drawPanel.getWidth();
        this.height = drawPanel.getHeight();
        this.flock.setWorldSize(width, height);

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
