import java.util.ArrayList;
import java.awt.Graphics2D;

/**
 * The Flock class contains an {@code ArrayList} of {@code Boid} objects
 * It also contains methods for adding and removing boids and running the 
 * simulation.
 */
public class Flock {

    private ArrayList<Boid> boids;
    private int width, height;
    private boolean alignment, cohesion, separation;

    public Flock(int width, int height) {
        this(0, width, height);
    }

    public Flock(int numBoids, int width, int height) {
        this.alignment = false;
        this.cohesion = false;
        this.separation = false;

        this.boids = new ArrayList<>();
        this.setWorldSize(width, height);
        this.addBoids(numBoids);
    }

    /**
     * Adds x amount of boids to the simulation
     * @param num of boids to be added
     */
    public void addBoids(int num) {
        for(int i = 0; i < num; i++) {
            this.addBoid();
        }
    }

    /**
     * This method adds a single boid
     */
    public void addBoid() {
        boids.add(new Boid(this));
    }

    /**
     * This method iterates through the flock and draws each boid.
     * @param g
     */
    public void drawFlock(Graphics2D g) {
        for(Boid b : boids) {
            b.DrawBoid(g);
        }
    }

    /**
     * This method iterates through all the boids and updates their positions.
     */
	public void run() {
        for(Boid b : boids) {
            b.run();
        }
    }
    
    /**
     * This method sets the world size for the simulation.
     * @param width
     * @param height
     */
    public void setWorldSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * This method will remove all boids from the simulation.
     */
	public void killAll() {
        this.boids.clear();
	}

    /**
     * @return the world width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the world height
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method will return whether the 
     * alignment rule is enabled.
     * @return alignment boolean
     */
    public boolean isAlignment() {
        return alignment;
    }

    /**
     * This method will enable or disable the 
     * alignment rule
     */
    public void setAlignment(boolean alignment) {
        this.alignment = alignment;
    }

    /**
     * This method will return whether the 
     * cohesion rule is enabled.
     * @return cohesion boolean
     */
    public boolean isCohesion() {
        return cohesion;
    }

    /**
     * This method will enable or disable the 
     * cohesion rule
     */
    public void setCohesion(boolean cohesion) {
        this.cohesion = cohesion;
    }

    /**
     * This method will return whether the 
     * separation rule is enabled.
     * @return separation boolean
     */
    public boolean isSeparation() {
        return separation;
    }

    /**
     * This method will enable or disable the 
     * separation rule
     */
    public void setSeparation(boolean separation) {
        this.separation = separation;
    }

    /**
     * This method returns the boids
     * @return
     */
	public ArrayList<Boid> getBoids() {
		return this.boids;
	}
}