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
    private double maxForce = 0.3;
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

    private void checkEdge() {
        if(this.pos.getX() > flock.getWidth()) this.pos.setX(0);
        if(this.pos.getY() > flock.getHeight()) this.pos.setY(0);
        if(this.pos.getX() < 0) this.pos.setX(flock.getWidth());
        if(this.pos.getY() < 0) this.pos.setY(flock.getHeight());
    }

    public void run() {
        update();
        checkEdge();
    }

    public Vector getPos() {
        return this.pos;
    }

    private void update() {

        // Find all the boids that are within the field of view (FOV)
        ArrayList<Boid> localBoids = findLocalBoids(flock.getBoids());

        if(localBoids.size() > 0) {
            if(flock.isAlignment()) this.vel.add(alignment(localBoids));
            if(flock.isCohesion()) this.vel.add(cohesion(localBoids));
            // if(flock.isSeparation()) this.vel.add(separation());
        }

        // Adding an acceleration multiplier
        this.vel.mult(rand.nextDouble() + 0.8);
        
        this.vel.limit(maxSpeed);
        this.pos.add(this.vel);
    }

    private ArrayList<Boid> findLocalBoids(ArrayList<Boid> boids) {

        ArrayList<Boid> localBoids = new ArrayList<>();

        for (Boid b : flock.getBoids()) {
            if(b == this) continue;

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
        steering.limit(maxForce);
        return steering;



        Vector steering = new Vector();

        int count = 0;
        for (Boid b : flock.getBoids()) {
            if(b == this) continue;

            double dist = Vector.dist(this.pos, b.getPos());
            if (dist < FOV) {
                steering.add(b.getPos());
                count++;
            }
        }

        if(count > 0) {
            steering.div(count);
            steering.sub(this.pos);

        }
        steering.limit(maxForce);
        return steering;
    }

    private Vector separation() {
        return null;
    }

    /**
     * @return the vel
     */
    public Vector getVel() {
        return vel;
    }
}