public class CircularShape implements Shape3D {
    private double radius;

    public CircularShape() {
        radius = 0;
    }

    public CircularShape(double r) {
        radius = r;
    }

    public double getDiameter() {
        return (2 * radius);
    }

    public double getRadius() {
        return radius;
    }

    public double getVolume() {
        return (Math.PI * Math.pow(radius, 3));
    }

    public double getArea() {
        return (4 * Math.PI * Math.pow(radius, 2));
    }

    public double getCrossSectionArea() {
        return (Math.PI * Math.pow(radius, 2));
    }

    public double getCrossSectionPerimeter() {
        return (2 * Math.PI * radius);
    }
    
}
