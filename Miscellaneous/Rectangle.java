public class Rectangle {
    private int length;
    private int width;

    public Rectangle() {
        length = 0;
        width = 0;
    }

    public Rectangle(int l, int w) {
        length = (l > 0 ? l : 0);
        width = (w > 0 ? w : 0);
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public void setLength(int l) {
        length = l;
    }

    public void setWidth(int w) {
        width = w;
    }

    public boolean equals(Rectangle r) {
        return (length == r.length && width == r.width);
    }

    public String toString() {
        return "Length is: " + length + "\n Width is: " + width;
    }

    public void print() {
        System.out.print("Length is: " + length + "\n Width is: " + width);
    }

    public void copy(Rectangle r) {
        length = r.length;
        width = r.width;
    }

    public Rectangle getCopy() {
        return new Rectangle(length, width);
    }

    public int perimeter() {
        return ((2 * length) + (2 * width));
    }

    public int area() {
        return (length * width);
    }

}
