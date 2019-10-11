import java.util.ArrayList;
import java.awt.Graphics2D;

/**
 * Flock
 */
public class Flock {

    private ArrayList<Boid> boids;
    private int numBoids;
    private int width, height;

    public Flock(int width, int height) {
        this(0, width, height);
    }

    public Flock(int numBoids, int width, int height) {
        this.setWorldSize(width, height);
        this.boids = new ArrayList<>();
        this.numBoids = numBoids;
        this.addBoids(numBoids);
    }

    public void addBoids(int num) {
        for(int i = 0; i < num; i++) {
            this.addBoid();
        }
    }

    public void addBoid() {
        boids.add(new Boid(width, height, boids));
        numBoids++;
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
}