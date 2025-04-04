public class RectangularPrism implements Shape3D {
    private double length;
    private double width;
    private double height;

    public RectangularPrism() {
        length = 0;
        width = 0;
        height = 0;
    }

    public RectangularPrism(double l, double w, double h) {
        length = l;
        width = w;
        height = h;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return (2 * ( length * width + width * height + length * height));
    }

    public double getVolume() {
        return (length * width * height);
    }

    public String toString() {
        return ("The length is: " + length + "\nThe width is: " + width + "\nThe height is: " + height + "\nThe area is: " + (2 * ( length * width + width * height + length * height)) + "\nThe volume is: " + (length * width * height));
    }

    public boolean equals(Object obj) {
        if (obj instanceof RectangularPrism) {
            RectangularPrism obg = (RectangularPrism) obj;
            return (length == obg.length && width == obg.width && height == obg.height);
        } else {
            return false;
        }
    }
}
