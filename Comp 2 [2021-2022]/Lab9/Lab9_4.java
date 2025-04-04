import java.util.ArrayList;
public class Lab9_4 {
    public static void main(String[] args)  {
        ArrayList <Integer> list = new ArrayList <Integer>(); 
        list.add(2);
        list.add(2); 
        list.add(2); 
        list.add(5); 
        list.add(5);
        list.add(8);
        list.add(9);
        list.add(9);
        System.out.println("The original list is: "); 
        print(list); 
        System.out.println("The list after method call is: ");
        removeDuplicates(list);
        print(list); 
    }
    
    public static void removeDuplicates(ArrayList<Integer> list) {
        ArrayListClass k = new OrderedArrayList();	 
        

        for(int i = 0; i < list.size(); i++) {
            if (k.search(list.get(i)) == -1) {
                k.insertEnd(list.get(i));
            }
        }

        list.clear();

        for (int j = 0; j < k.length; j++) {
            list.add(k.list[j]);
        }
    }
    
    public static void print(ArrayList <Integer> someList) {
        for(Integer i:someList)
            System.out.print(i + "  ");
        System.out.println();
    }
}
