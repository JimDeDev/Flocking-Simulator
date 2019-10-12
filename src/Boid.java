import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.Color;
import static java.lang.Math.*;

/**
 * Boid
 */
public class Boid {

    private Random rand;

    private double maxSpeed = 3;
    private double maxForce = 0.2;
    private int FOV = 50;
    private Vector pos, vel;
    private Flock flock;
    private static int size = 4;
    static final Path2D shape = new Path2D.Double();

    static {
        shape.moveTo(-size, size * 2);
        shape.lineTo(0, -size * 2);
        shape.lineTo(size, size * 2);
    }

    public Boid(Flock flock) {
        this.rand = new Random();

        this.flock = flock;
        double velX = -1.0 +(1.0 - -1.0) * rand.nextDouble();
        double velY = -1.0 +(1.0 - -1.0) * rand.nextDouble();
        this.vel = new Vector(velX, velY);
        this.pos = new Vector(rand.nextInt(flock.getWidth()), rand.nextInt(flock.getHeight()));
    }

    public void DrawBoid(Graphics2D g) {

        AffineTransform trans = g.getTransform();

        g.translate(pos.getX(), pos.getY());
        g.rotate(vel.heading() + PI / 2);
        g.setColor(Color.white);
        g.draw(shape);
        
        g.setTransform(trans);
    }

    /**
     * This method will update the boids current position 
     * by adding forces from the different rules
     */
    public void run() {
        // Find all the boids that are within the field of view (FOV)
        ArrayList<Boid> localBoids = findLocalBoids(flock.getBoids());

        if(localBoids.size() > 0) {
            if(flock.isAlignment()) this.vel.add(alignment(localBoids));
            if(flock.isCohesion()) this.vel.add(cohesion(localBoids));
            if(flock.isSeparation()) this.vel.add(separation(localBoids));
        }
        this.vel.add(avoidWalls());

        // Adding an acceleration multiplier
        this.vel.mult(rand.nextDouble() + 0.6);
        
        this.vel.limit(maxSpeed);
        this.pos.add(this.vel);    
    }

    public Vector getPos() {
        return this.pos;
    }

    private Vector avoidWalls() {
        Vector steering = new Vector();

        // Avoid side walls
        if(flock.getWidth() - this.pos.getX() < 50) {
            steering.add(new Vector(-0.2, 0));
        } else if(this.pos.getX() < 50) {
            steering.add(new Vector(0.2, 0));
        } 
        // Avoid top and bottom walls
        if(flock.getHeight() - this.pos.getY() < 50) {
            steering.add(new Vector(0, -0.2));
        } else if(this.pos.getY() < 50) {
            steering.add(new Vector(0, 0.2));
        } 

        return steering;
    }

    /**
     * This method will iterate through all of the boids and 
     * add the boids that are within the field of view to 
     * a separate list.
     * @param boids
     * @return An {@code ArrayList<Boid>} of local boids
     */
    private ArrayList<Boid> findLocalBoids(ArrayList<Boid> boids) {

        ArrayList<Boid> localBoids = new ArrayList<>();

        for (Boid b : flock.getBoids()) {
            if(b == this) continue;

            // Get the distance between this boid and the other
            double dist = Vector.dist(this.pos, b.getPos());
            if (dist < FOV) {
                localBoids.add(b);
            }
        }

        return localBoids;
    }

    private Vector alignment(ArrayList<Boid> localBoids) {
        Vector steering = new Vector();

        for (Boid b : localBoids) {
            steering.add(b.getVel());
        }

        steering.div(localBoids.size());
        steering.limit(maxForce);
        return steering;
    }

    private Vector cohesion(ArrayList<Boid> localBoids) {
        Vector steering = new Vector();

        for (Boid b : localBoids) {
            steering.add(b.getPos());
        }

        steering.div(localBoids.size());
        steering.sub(this.pos);
        steering.limit(maxForce);
        return steering;
    }

    private Vector separation(ArrayList<Boid> localBoids) {
        Vector steering = new Vector();

        for (Boid b : localBoids) {
            double dist = Vector.dist(this.pos, b.getPos());
            Vector diff = Vector.sub(this.pos, b.getPos());
            diff.div(dist);
            steering.add(diff);
        }

        steering.div(localBoids.size());
        steering.limit(maxForce);
        return steering;
       }

    /**
     * @return the vel
     */
    public Vector getVel() {
        return vel;
    }
}