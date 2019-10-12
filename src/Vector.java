import static java.lang.Math.*;

/**
 * The Vector class had methods that can be used 
 * to manipulate the vector
 * Retrieved from {@link https://rosettacode.org/wiki/Boids/Java}
 */
public class Vector {
    double x, y;
 
    Vector() {
    }
 
    Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
    void add(Vector v) {
        x += v.x;
        y += v.y;
    }
 
    void sub(Vector v) {
        x -= v.x;
        y -= v.y;
    }
 
    void div(double val) {
        x /= val;
        y /= val;
    }
 
    void mult(double val) {
        x *= val;
        y *= val;
    }
 
    double mag() {
        return sqrt(pow(x, 2) + pow(y, 2));
    }
 
    double dot(Vector v) {
        return x * v.x + y * v.y;
    }
 
    void normalize() {
        double mag = mag();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }
 
    void limit(double lim) {
        double mag = mag();
        if (mag != 0 && mag > lim) {
            x *= lim / mag;
            y *= lim / mag;
        }
    }
 
    double heading() {
        return atan2(y, x);
    }

    static Vector sub(Vector v, Vector v2) {
        return new Vector(v.x - v2.x, v.y - v2.y);
    }
 
    static double dist(Vector v, Vector v2) {
        return sqrt(pow(v.x - v2.x, 2) + pow(v.y - v2.y, 2));
    }
 
    static double angleBetween(Vector v, Vector v2) {
        return acos(v.dot(v2) / (v.mag() * v2.mag()));
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector [x=" + x + ", y=" + y + "]";
    }
}