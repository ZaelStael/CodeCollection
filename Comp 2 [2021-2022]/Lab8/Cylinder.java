public class Cylinder extends CircularShapeWithHeight {

    public Cylinder() {
        super();
    }

    public Cylinder(double r, double h) {
        super(r, h);
    }

    public double getRadius() {
        return super.getRadius();
    }

    public double getArea() {
        return (getCrossSectionPerimeter() * getHeight() + 2 * getCrossSectionArea());
    }

    public double getVolume() {
        return (getCrossSectionArea() * getHeight());
    }

    public String toString() {
        return ("The radius is: " + getRadius() + "\nThe area is: " + (getCrossSectionPerimeter() * getHeight() + 2 * getCrossSectionArea()) + "\nThe Volume is: " + (getCrossSectionArea() * getHeight()));
    }

    public boolean equals(Object obj) {
        if (obj instanceof Cylinder) {
            Cylinder objl = (Cylinder) obj;

            return (getRadius() == objl.getRadius() && getHeight() == objl.getHeight());
        } else {
            return false;
        }
    }
    
}
