import java.util.ArrayList;
import java.awt.Graphics2D;

/**
 * Flock
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

    public void addBoids(int num) {
        for(int i = 0; i < num; i++) {
            this.addBoid();
        }
    }

    public void addBoid() {
        boids.add(new Boid(this));
    }

    public void drawFlock(Graphics2D g) {
        for(Boid b : boids) {
            b.DrawBoid(g);
        }
    }

	public void run() {
        for(Boid b : boids) {
            b.run();
        }
    }
    
    public void setWorldSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

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
     * @return the alignment
     */
    public boolean isAlignment() {
        return alignment;
    }

    /**
     * @param alignment the alignment to set
     */
    public void setAlignment(boolean alignment) {
        this.alignment = alignment;
    }

    /**
     * @return the cohesion
     */
    public boolean isCohesion() {
        return cohesion;
    }

    /**
     * @param cohesion the cohesion to set
     */
    public void setCohesion(boolean cohesion) {
        this.cohesion = cohesion;
    }

    /**
     * @return the separation
     */
    public boolean isSeparation() {
        return separation;
    }

    /**
     * @param separation the separation to set
     */
    public void setSeparation(boolean separation) {
        this.separation = separation;
    }

	public ArrayList<Boid> getBoids() {
		return this.boids;
	}
}