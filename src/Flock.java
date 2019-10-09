import java.util.ArrayList;
import java.awt.Graphics;

/**
 * Flock
 */
public class Flock {

    private ArrayList<Boid> boids;
    private int numBoids;

    public Flock() {
        this(0);
    }

    public Flock(int numBoids) {
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
        boids.add(new Boid());
        numBoids++;
    }

    public void drawFlock(Graphics g) {
        for(Boid b : boids) {
            b.DrawBoid(g);
        }
    }
}