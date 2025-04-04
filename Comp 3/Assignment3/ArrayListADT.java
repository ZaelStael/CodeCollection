
public interface ArrayListADT<T> extends Cloneable {
    public boolean isEmpty(); // Method to determine whether the list is empty.

    public boolean isFull(); // Method to determine whether the list is full.

    public int listSize(); // Method to return the number of elements in the list.

    public int maxListSize(); // Method to return the maximum size of the list.

    public void print(); // Method to output the elements of the list.

    public boolean isItemAtEqual(Integer location, Comparable<T> item); // Method to determine whether item is the same
                                                                        // as the item
    // in
    // the list at location.

    public void insertAt(Integer location, Comparable<T> insertItem); // Method to insert insertItem in the list at the
                                                                      // position

    public void insertEnd(Comparable<T> insertItem); // Method to insert insertItem at the end of the list.

    public void removeAt(Integer location); // Method to remove the item from the list at location.

    public Comparable<T> retrieveAt(Integer location); // Method to retrieve the element from the list at location.

    public void replaceAt(Integer location, Comparable<T> repItem); // Method to replace the element in the list at
                                                                    // location with
    // repItem.

    public void clearList(); // Method to remove all the elements from the list.

    public int search(Comparable<T> searchItem); // Method to determine whether searchItem is in the list.

    public void remove(Comparable<T> removeItem); // Method to remove an item from the list.
}
