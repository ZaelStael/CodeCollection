public class DoubleLinkedList<T> implements DoubleLinkedListADT<T> {
    // Double linked list node class
    public class DoubleLinkedListNode<T> {
        T info;
        DoubleLinkedListNode<T> next;
        DoubleLinkedListNode<T> back;

        public DoubleLinkedListNode() {
            info = null;
            next = null;
            back = null;
        }

        public String toString() {
            return info.toString();
        }

    }

    protected int count; // number of nodes
    protected DoubleLinkedListNode<T> first; // reference to first node
    protected DoubleLinkedListNode<T> last; // reference to last node

    public void initializeList() { // Needs Work
        DoubleLinkedListNode<T> current;
        DoubleLinkedListNode<T> trail;

        current = trail = first;
        // list starter?
        if (current == null) {
            System.out.println("List empty. Adding new node.");
            this.insertNode(null);
            count++;
        }
        // List clear
        while (current != null) {
            if (current.back != null) {
                trail.info = current.back.info;
                this.deleteNode(trail.info);
            } else if (current.next == null && current.info != null) {
                this.deleteNode(current.info);
                current = current.next;
            } else {

                current = current.next;

            }

        }

    }

    public boolean isEmptyList() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    public T front() {
        return first.info;
    }

    public T back() {
        return last.info;
    }

    public int length() {
        return count;
    }

    public void print() {
        DoubleLinkedListNode<T> current;

        current = first;

        if (current == null) {
            System.out.println("Now how are you gonna printg from an empty list?");

        } else {
            System.out.print("The nodes are: ");
            while (current != null) {
                System.out.print(current.info + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    public void reversePrint() {

        DoubleLinkedListNode<T> current;

        current = last;

        if (current == null) {
            System.out.println("Now how are you gonna print from an empty list?");
        } else {
            System.out.print("The node backwards are: ");
            while (current != null) {
                System.out.print(current.info + " ");
                current = current.back;
            }
            System.out.println();
        }

    }

    public boolean search(T searchItem) {
        DoubleLinkedListNode<T> current = first;

        if (current == null) {
            // System.out.println("You want to search through an empty list...yeah no.");
            return false;
        } else {
            while (current != null) {
                if (current.info == searchItem) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

    }

    public void insertNode(T insertItem) {
        boolean found;
        DoubleLinkedListNode<T> current;// moving reference
        DoubleLinkedListNode<T> trailCurrent = null; // just before current
        // Set up node to be inserted
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
        newNode.info = insertItem;
        newNode.next = null;
        newNode.back = null;
        // if the list is empty, newNode is the only node
        if (first == null) {
            first = newNode;
            last = newNode;
            count++;
        } else {
            found = false;
            current = first;
            // search the list
            while (current != null && !found) {
                Comparable<T> temp = (Comparable<T>) current.info;
                if (temp.compareTo(insertItem) >= 0)
                    found = true;
                else {
                    trailCurrent = current;
                    current = current.next;
                }
            }
            // insert new node before first
            if (current == first) {
                first.back = newNode;
                newNode.next = first;
                first = newNode;
                count++;
            } else {
                // insert newNode between trailCurrent and current
                if (current != null) {
                    trailCurrent.next = newNode;
                    newNode.back = trailCurrent;
                    newNode.next = current;
                    current.back = newNode;
                } else {
                    // insert new node after last
                    trailCurrent.next = newNode;
                    newNode.back = trailCurrent;
                    last = newNode;
                }
                count++;
            }
        }
    }

    public void deleteNode(T deleteItem) {
        DoubleLinkedListNode<T> current; // moving reference
        DoubleLinkedListNode<T> trailCurrent;// just before current
        boolean found;
        if (first == null)
            System.err.println("Cannot delete from an empty list.");
        // if node to be deleted is the first node
        else if (first.info.equals(deleteItem)) {
            current = first;
            first = first.next;
            if (first != null)
                first.back = null;
            else
                last = null;
            count--;
        } else {
            found = false;
            current = first;
            // search the list
            while (current != null && !found) {
                Comparable<T> temp = (Comparable<T>) current.info;
                if (temp.compareTo(deleteItem) >= 0)
                    found = true;
                else
                    current = current.next;
            }
            if (current == null)
                System.out.println("The item to be deleted is not in the list.");
            else if (current.info.equals(deleteItem)) {
                trailCurrent = current.back;
                trailCurrent.next = current.next;
                if (current.next != null)
                    current.next.back = trailCurrent;
                if (current == last)
                    last = trailCurrent;
                count--;
            } else
                System.out.println("The item to be deleted is not in list.");
        }
    }

    @Override
    public String toString() {
        DoubleLinkedListNode<T> current;

        current = first;

        String resultString = "";

        if (current == null) {
            resultString += ("Now how are you gonna printf from an empty list?");

        } else {
            resultString += (" [");
            while (current != null) {
                resultString += (current.info + " ");
                current = current.next;
            }
            resultString += ("]");
        }

        return resultString;
    }

    public String recursiveToString() { // Will change to just return and helper call

        String recResultString = "";

        if (first == null) {
            recResultString += " ";
            return recResultString;
        } else {
            while (first != null) {
                recResultString += (first.info + " " + recursiveToString(first.next));
                return recResultString;
            }
        }

        return recResultString;

    }

    public String recursiveToString(DoubleLinkedListNode<T> recCurr) {

        String recResultString = "";

        if (recCurr == null) {
            recResultString += " ";
            return recResultString;

        } else {
            while (recCurr != null) {
                recResultString += (recCurr.info + " " + recursiveToString(recCurr.next));
                return recResultString;

            }
        }
        return recResultString;

    }

    public String backwardsString() {
        DoubleLinkedListNode<T> current;

        current = last;

        String resultString = "";

        if (current == null) {
            resultString += ("Now how are you gonna print from an empty list?");

        } else {
            resultString += (" [");
            while (current != null) {
                resultString += (current.info + " ");
                current = current.back;
            }
            resultString += ("]");
        }

        return resultString;
    }

    public String recursiveBackwardsString() { // Will change to just return and helper call

        String brecResultString = "";

        if (last == null) {
            brecResultString += " ";
            return brecResultString;
        } else {
            while (last != null) {
                brecResultString += (last.info + " " + recursiveBackwardsString(last.back));
                return brecResultString;
            }
        }

        return brecResultString;
    }

    public String recursiveBackwardsString(DoubleLinkedListNode<T> brecCurr) {

        String brecResultString = "";

        if (brecCurr == null) {
            brecResultString += " ";
            return brecResultString;

        } else {
            while (brecCurr != null) {
                brecResultString += (brecCurr.info + " " + recursiveBackwardsString(brecCurr.back));
                return brecResultString;

            }
        }
        return brecResultString;
    }

    public boolean equals(Object o) {
        if (o instanceof DoubleLinkedList) {
            DoubleLinkedList<T> other = (DoubleLinkedList<T>) o;
            DoubleLinkedListNode<T> current = first;
            DoubleLinkedListNode<T> oCurrent = other.first;
            int count = 0;

            if (first == null) {
                return ((other.first == null) ? true : false);
            } else {
                while (current != null) {
                    if (current.info == oCurrent.info) {
                        count++;
                    }

                    current = current.next;
                    oCurrent = oCurrent.next;
                }
            }

            if (count == this.length() && count == other.length()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void copy(DoubleLinkedList<T> otherList) {
        DoubleLinkedListNode<T> oCurrent = otherList.first;

        if (otherList.first == null) {
            System.out.println("Cannot copy an empty list.");
        } else {
            while (oCurrent != null) {
                this.insertNode((T) oCurrent.info);
                oCurrent = oCurrent.next;
            }
        }
    }

    public void reverse() {
        DoubleLinkedListNode<T> tmp = null;
        DoubleLinkedListNode<T> current = first;

        while (current != null) {
            tmp = current.back;
            current.back = current.next;
            current.next = tmp;
            current = current.back;
        }

        if (tmp != null) {
            first = tmp.back;
        }
    }

    public void addNode(T o) {
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
        newNode.info = o;
        newNode.back = null;
        newNode.next = first;

        if (first != null) {
            first.back = newNode;
        }

        first = newNode;

        if (first.next != null) {
            first.next.back = first;
        }

    }

    public void addNodeBack(T o) {
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
        newNode.info = o;
        newNode.back = last;
        newNode.next = null;

        if (last != null) {
            last.next = newNode;
        }

        last = newNode;

        if (last.back != null) {
            last.back.next = last;
        }
    }

    public void reversedCopy(DoubleLinkedList<T> otherList) {
        DoubleLinkedListNode<T> oCurrent = otherList.first;
        this.initializeList();

        if (otherList.last == null) {
            System.out.println("Cannot copy an empty list.");
        } else {

            while (oCurrent != null) {
                this.addNode(oCurrent.info);
                oCurrent = oCurrent.next;
            }

            DoubleLinkedListNode<T> last = new DoubleLinkedListNode<>();

            last.info = otherList.first.info;
            last.next = null;

            DoubleLinkedListNode<T> tmp = first;
            DoubleLinkedListNode<T> trv = tmp.next;
            while (tmp.next != null) {
                trv.back = tmp;
                tmp = tmp.next;
                trv = tmp.next;

                if (tmp.next == null) {
                    last.back = tmp.back;
                    this.last = last;
                }
            }

        }
    }

}
