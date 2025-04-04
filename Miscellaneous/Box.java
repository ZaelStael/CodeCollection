public class Box extends Rectangle {
    private int height;

    public Box() {
        super();
        height = 0;
    }

    public Box(int l, int w, int h) {
        super(l, w);
        height = (h > 0 ? h : 0);
    }

    public void set(int l, int w, int h) {
        super.setLength(l);
        super.setLength(w);
        height = (h > 0 ? h : 0);
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        return (super.toString() + "\nHeight is: " + height);
    }

    public void print() {
        super.print();
        System.out.print("\nHeight is: " + height);
    }

    public boolean equals(Box b) {
        return (super.equals(b) && height == b.height);
    }

    public void copy(Box b) {
        super.copy(b);
        height = b.height;
    }

    public Box getCopy() {
        return new Box(getLength(), getWidth(), height);
    }

    public int surfaceArea() {
        return ((getLength() * height * 4) + (getWidth()

                * height * 2));
    }

    public int volume() {
        return (getLength() * getWidth() * getHeight());
    }
}
