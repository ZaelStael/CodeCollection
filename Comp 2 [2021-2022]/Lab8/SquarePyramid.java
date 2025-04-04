public class SquarePyramid implements Shape3D {
    private double length;
    private double height;

    public SquarePyramid() {
        length = 0;
        height = 0;

    }

    public SquarePyramid(double l, double h) {
        length = l;
        height = h;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return (length * (length + Math.sqrt(length * length + 4 * height *height)));
    }

    public double getVolume() {
        return (length * length * height / 3.0);
    }

    public String toString() {
        return ("The length is: " + length + "\nThe height is: " + height + "\nThe area is: " + (length * (length + Math.sqrt(length * length + 4 * height *height))) + "\nThe volume is: " + (length * length * height / 3.0));
    }

    public boolean equals(Object obj) {
        if (obj instanceof SquarePyramid) {
            SquarePyramid objS = (SquarePyramid) obj;
            return (length == (double) objS.length && height == (double) objS.height);
        } else {
            return false;
        }
        
    }
}
