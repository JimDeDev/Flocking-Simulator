/**
 * Vector
 */
public class Vector {

    private int x, y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector sub(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector mul(Vector other) {
        return new Vector(x * other.x, y * other.y);
    }
}