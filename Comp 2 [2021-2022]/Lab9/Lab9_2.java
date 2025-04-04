import java.util.ArrayList;
public class Lab9_2 {
    public static void main(String[] args)  {
        ArrayList <Integer> list = new ArrayList <Integer>(); 
        list.add(2);
        list.add(4); 
        list.add(-2); 
        list.add(5); 
        list.add(3);
        list.add(0);
        list.add(7);
        System.out.println("The original list is: "); 
        print(list); 
        System.out.println("The list after method call is: ");
        scaleByK(list);
        print(list); 
    }
    
    public static void scaleByK(ArrayList<Integer> list) {
        ArrayListClass k = new UnorderedArrayList();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) <= 0) {
                list.remove(list.get(i));
            }
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i); j++) {
                
                k.insertEnd(list.get(i));
                
            }
        }
        list.clear();

        for (int i = 0; i < k.length; i++) {
            
            list.add(k.list[i]);

        }
    }
    
    public static void print(ArrayList <Integer> someList) {
        for(Integer i:someList)
            System.out.print(i + "  ");
        System.out.println();
    }
    
}
