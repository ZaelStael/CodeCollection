public class UnorderedArrayList<T> extends ArrayListClass<T> {

    public UnorderedArrayList() {
        super();
    }

    public UnorderedArrayList(Integer size) {
        super(size);
    }

    // Bubble Sort
    public void bubbleSort() {
        for (int pass = 0; pass < length - 1; pass++) {
            for (int i = 0; i < length - 1; i++) {
                Comparable<T> listElem = (Comparable<T>) list[i];
                if (listElem.compareTo(list[i + 1]) > 0) {
                    T temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                }
            }
        }
    }

    // implementation for abstract methods defined in ArrayListClass
    // unordered list --> linear search
    public int search(Comparable<T> searchItem) {
        for (int i = 0; i < length; i++)
            if (list[i].equals(searchItem))
                return i;
        return -1;
    }

    public void insertAt(Integer location, Comparable<T> insertItem) {
        if (location < 0 || location >= maxSize)
            System.err.println("The position of the item to be inserted is out of range.");
        else if (length >= maxSize)
            System.err.println("Cannot insert in a full list.");
        else {
            for (int i = length; i > location; i--)
                list[i] = list[i - 1]; // shift right
            list[location] = (T) insertItem;
            length++;
        }
    }

    public void insertEnd(Comparable<T> insertItem) {

        if (length >= maxSize)
            System.err.println("Cannot insert in a full list.");
        else {
            if (insertItem != null) {
                list[length] = (T) insertItem;
                length++;
            }

        }
    }

    public void replaceAt(Integer location, Comparable<T> repItem) {
        if (location < 0 || location >= maxSize)
            System.err.println("The location of the item to be replaced is out of range.");
        else
            list[location] = (T) repItem;
    }

    public void remove(Comparable<T> removeItem) {
        int i;
        if (length == 0)
            System.err.println("Cannot delete from an empty list.");
        else {
            i = search(removeItem);
            if (i != -1)
                removeAt(i);
            else
                System.out.println("Cannot delete! The item to be deleted is not in the list.");
        }
    }

    public boolean isItemAtEqual(Integer location, Comparable<T> item) {
        if (location < 0 || location >= length) {
            System.err.println("The location of the item to be compared is out of range.");
            return false;
        }
        return list[location].equals(item);

    }

    public UnorderedArrayList<T> merge(UnorderedArrayList<T> list2) {
        final UnorderedArrayList<T> resList = new UnorderedArrayList<>(listSize() + list2.listSize());
        int c = 0;

        if (this instanceof UnorderedArrayList) {
            for (T a : list) {
                if (a != null) {
                    resList.replaceAt(c, (Comparable<T>) a);
                    resList.length++;
                    c++;
                }

            }
        } else {
            System.err.print("Incorrect call.");
        }

        if (list2 instanceof UnorderedArrayList) {
            for (T b : list2.list) {
                if (b != null) {
                    resList.replaceAt(c, (Comparable<T>) b);
                    resList.length++;
                    c++;
                }
            }
        } else {
            System.err.print("Incorrect call.");
        }

        return resList;
    }

    public void split(UnorderedArrayList<T> res1, UnorderedArrayList<T> res2, Integer splitK) {
        res1 = new UnorderedArrayList<>();
        res2 = new UnorderedArrayList<>();

        for (int i = 0; i < length; i++) {
            if (Integer.valueOf((String) list[i]) < splitK) {
                res1.insertEnd((Comparable<T>) list[i]);
            } else {
                res2.insertEnd((Comparable<T>) list[i]);
            }
        }

        // for (T el : list) {

        // if (((Integer) el) instanceof Integer) {
        // if (((Integer) el) < splitK) {
        // res1.insertEnd((Comparable<T>) el);
        // } else if ((Integer) el > splitK) {
        // res2.insertEnd((Comparable<T>) el);
        // }
        // } else {
        // System.err.print("What now..?");
        // }
        // }
    }

}