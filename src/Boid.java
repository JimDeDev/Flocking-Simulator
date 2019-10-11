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

    private int FOV = 60;
    private Vector pos, acc, vel;
    private int width, height;
    private double maxSpeed = 3.0;
    private ArrayList<Boid> boids;
    private static int size = 4;
    static final Path2D shape = new Path2D.Double();

    static {
        shape.moveTo(-size, size * 2);
        shape.lineTo(0, -size * 2);
        shape.lineTo(size, size * 2);
    }

    public Boid(int width, int height, ArrayList<Boid> boids) {

        this.boids = boids;
        Random rand = new Random();
        this.width = width;
        this.height = height;
        this.vel = new Vector();
        this.acc = new Vector(rand.nextInt(4), rand.nextInt(4));
        this.pos = new Vector(rand.nextInt(width), rand.nextInt(height));
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
        if(this.pos.getX() > width) this.pos.setX(0);
        if(this.pos.getY() > height) this.pos.setY(0);
        if(this.pos.getX() < 0) this.pos.setX(width);
        if(this.pos.getY() < 0) this.pos.setY(height);
    }

    public void run() {
        update();
        checkEdge();
    }

    public Vector align() {
        Vector avg = new Vector();
        int count = 0;
        for(Boid b : boids) {
            if(b == this) continue;
            double dist = Vector.dist(this.pos, b.pos);
            if(dist < FOV) {
                avg.add(b.vel);
                count++;
            }
        }
        if(count > 0) avg.div(count);
        return avg;
    }

    public Vector getPos() {
        return this.pos;
    }

    private void update() {
        Vector align = Vector.sub(vel, align());
        vel.add(align);
        // vel.add(cohesion());
        // vel.add(separation());
        vel.add(acc);
        vel.limit(maxSpeed);
        pos.add(vel);

    }

    private Vector cohesion() {
        return null;
    }

    private Vector separation() {
        return null;
    }

    /**
     * @return the acc
     */
    public Vector getAcc() {
        return acc;
    }

    /**
     * @return the vel
     */
    public Vector getVel() {
        return vel;
    }
}