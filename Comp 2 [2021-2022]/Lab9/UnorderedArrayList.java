public class UnorderedArrayList extends ArrayListClass {
    //Default constructor
    public UnorderedArrayList() {
        super();
    }

    //Alternate Constructor
    public UnorderedArrayList(int size) {
        super(size);
    }
    
    //To sort the list - use Bubble Sort
    public void bubbleSort() {
        int temp;
        for (int pass = 0; pass < length - 1; pass++) {
            for (int i = 0; i < length - 1; i++) {
                if (list[i] > list[i + 1]) {
                    temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                }
            }
        }
    }

    //implementation for abstract methods defined in ArrayListClass
    //unordered list --> linear search
    public int search(int searchItem) {
        for(int i = 0; i < length; i++)
            if(list[i] == searchItem)
                return i;
        return -1;
    } 

    public void insertAt(int location, int insertItem) {
        if (location < 0 || location >= maxSize)
            System.err.println("The position of the item to be inserted is out of range.");
        else if (length >= maxSize)  
            System.err.println("Cannot insert in a full list.");
        else {
            for (int i = length; i > location; i--)
                list[i] = list[i - 1];  //shift right
            list[location] = insertItem;
            length++;   
        }
    } 

    public void insertEnd(int insertItem) {
        if (length >= maxSize)  
            System.err.println("Cannot insert in a full list.");
        else {
            list[length] = insertItem;  
            length++; 
        }
    }


    public void replaceAt(int location, int repItem)  {
        if (location < 0 || location >= length)
            System.err.println("The location of the item to be replaced is out of range.");
        else
            list[location] = repItem;
    } 

    public void remove(int removeItem) {
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

    
    
    public void scaleByK() {
        ArrayListClass k = new UnorderedArrayList();

        for (int i = 0; i < length; i++) {
            if (list[i] <= 0) {
                this.remove(list[i]);
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < list[i]; j++) {
                
                k.insertEnd(list[i]);
                
            }
        }
        this.clearList();

        for (int i = 0; i < k.length; i++) {
            
            list[i] = k.list[i];
            length++;

        }

        

    }
}
