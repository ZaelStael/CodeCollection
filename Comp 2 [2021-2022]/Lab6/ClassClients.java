public class ClassClients {
    public static void main(String[] args) {
        Class_2 set = new Class_2(5, 3, 7);
        set.print();
        System.out.println(" " + set.toString());
        set.set(3, 5);
        set.print();
        set.set(3, 6, 9);
        System.out.println(" " + set.toString());
    }
}
