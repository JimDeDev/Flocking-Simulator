import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUI extends JPanel implements ActionListener {

    public Flock flock;
    private Panel drawPanel;
    private JButton add20BoidsBtm, killBoidsBtn;
    private JCheckBox alignment, cohesion, separation;
    private Timer timer;

    public GUI() {
        super(new BorderLayout());

        this.flock = new Flock(800, 600);
        this.flock.addBoids(200);

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

            int result = JOptionPane.showConfirmDialog(this, spinner, "How Many Boids do you want to add?", JOptionPane.OK_CANCEL_OPTION);
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

        // Move the flock
        flock.run();
        // Repaint the draw panel
        drawPanel.repaint();
    }

    /**
     * The Panel class is where the boids roam
     */
    private class Panel extends JPanel {
        public Panel(){
            super();
            this.addComponentListener(new ComponentAdapter() {
                /**
                 * This method is used to change the world size 
                 * so that the flock knows how large the window is
                 */
                @Override
                public void componentResized(ComponentEvent e) {
                    flock.setWorldSize(drawPanel.getWidth(), drawPanel.getHeight());
                }
              });

            super.setBackground(Color.DARK_GRAY);
            super.setPreferredSize(new Dimension(800, 600));      
        }
        //draws the panel
        public void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1;

            flock.drawFlock(g);
        }



    }
}
