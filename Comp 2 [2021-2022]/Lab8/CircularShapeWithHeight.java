public class CircularShapeWithHeight extends CircularShape {
    private double height;

    public CircularShapeWithHeight() {
        super();
        height = 0;
    }

    public CircularShapeWithHeight(double r, double h) {
        super(r);
        height = h;
    }

    public double getHeight() {
        return height;
    }
    
}
