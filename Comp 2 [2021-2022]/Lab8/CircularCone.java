public class CircularCone extends CircularShapeWithHeight {
    
    public CircularCone() {
        super();
    }

    public CircularCone(double radius, double height) {
        super(radius, height);
    }

    public double getArea() {
        double r = getRadius();
        double h = getHeight();
        return (Math.PI * r * Math.sqrt(r * r + h * h));
    }

    public double getVolume() {
        return (getCrossSectionArea() * getHeight() / 3.0);
    }

    public String toString() {
        return ("The radius is: " + getRadius() + "\nThe area is: " + (Math.PI * getRadius() * Math.sqrt(getRadius() * getRadius() + getHeight() * getHeight()) + "\nThe Volume is: " + (getCrossSectionArea() * getHeight() / 3.0)));
    }

    public boolean equals(Object obj) {
        if (obj instanceof CircularCone) {
            CircularCone objc = (CircularCone) obj;

            return (getRadius() == objc.getRadius() && getHeight() == objc.getHeight());
        } else {
            return false;
        }
    }
}
