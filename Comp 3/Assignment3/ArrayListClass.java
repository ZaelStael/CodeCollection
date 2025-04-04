public abstract class ArrayListClass<T> implements ArrayListADT<T> {
    protected Integer length; // to store the length of the list
    protected Integer maxSize; // to store the maximum size of the list
    protected T[] list; // array to hold the list elements

    // Default constructor
    public ArrayListClass() {
        maxSize = 100;
        length = 0;
        list = (T[]) new Object[maxSize];
    }

    // Alternate Constructor
    public ArrayListClass(Integer size) {
        if (size <= 0) {
            System.err.println("The array size must be positive. Creating an array of size 100.");
            maxSize = 100;
        } else
            maxSize = size;
        length = 0;
        list = (T[]) new Object[maxSize];
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    public boolean isFull() {
        return (length == maxSize);
    }

    public int listSize() {
        return length;
    }

    public int maxListSize() {
        return maxSize;
    }

    public void print() {
        for (int i = 0; i < length; i++)
            System.out.print(list[i] + "  ");
        System.out.println();
    }

    public Object clone() {
        ArrayListClass<T> copy = null;
        try {
            copy = (ArrayListClass<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        copy.list = (T[]) list.clone();
        return copy;
    }

    public boolean isItemAtEqual(Integer location, T item) {

        if (location < 0 || location >= length) {
            System.err.println("The location of the item to be compared is out of range.");
            return false;
        }
        return list[location].equals(item);
    }

    public void clearList() {
        for (int i = 0; i < length; i++)
            list[i] = null;
        length = 0;
        System.gc(); // invoke the Java garbage collector
    }

    public void removeAt(Integer location) {
        if (location < 0 || location >= length)
            System.err.println("The location of the item to be removed is out of range.");
        else {
            for (int i = location; i < length - 1; i++)
                list[i] = list[i + 1];
            length--;
        }
    }

    public Comparable<T> retrieveAt(Integer location) {
        if (location < 0 || location >= length) {
            System.err.println("The location of the item to be retrieved is out of range.");
            return null;
        } else
            return (Comparable<T>) list[location];
    }

    public abstract void insertAt(Integer location, Comparable<T> insertItem);

    public abstract void insertEnd(Comparable<T> insertItem);

    public abstract void replaceAt(Integer location, Comparable<T> repItem);

    public abstract int search(Comparable<T> searchItem);

    public abstract void remove(Comparable<T> removeItem);
}
