import java.awt.Graphics;

/**
 * Boid
 */
public class Boid {

    private int x, y;
    private Vector vec;

    public Boid() {
        this.x = 200;
        this.y = 200;
        this.vec = new Vector(x, y);
    }

    public void DrawBoid(Graphics g) {
        g.fillRect(x, y, 20, 20);
    }
}