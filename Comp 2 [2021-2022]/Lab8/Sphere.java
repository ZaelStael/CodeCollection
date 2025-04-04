public class Sphere implements Shape3D {
    private double radius;

    public Sphere() {
        radius = 0;
    }

    public Sphere(double r) {
        radius = r;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return (4 * Math.PI * Math.pow(radius, 2));
    }

    public double getVolume() {
        return (4.0 * Math.PI * Math.pow(radius, 3) / 3.0);
    }

    public String toString() {
        return ("The radius is: " + radius + "\nThe area is: " + (4 * Math.PI * Math.pow(radius, 2)) + "\nThe Volume is: " + (4.0 * Math.PI * Math.pow(radius, 3) / 3.0));
    }

    public boolean equals(Object obj) {
        if (obj instanceof Sphere) {
            Sphere objj = (Sphere) obj;

            return (radius == objj.radius);
        } else {
            return false;
        }
    }
}
