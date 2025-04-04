public class AnimalClientCode {
    public static void main(String[] args) {
        Animal Bob = new Animal();
        Bob.type = "Bob";
        Bob.trait = "Blue";

        Animal Bill = new Animal();
        Bill.type = "Bill";
        Bill.trait = "Red";

        Bob.friend[0] = Bill;
        Bill.friend[0] = Bob;

        System.out.println(Animal.hasFriend(Bill));

        int i = 0;

        while (i < 4) { // UGGGGHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
            System.out.println(Bob.eat(2));
            System.out.println(Bill.eat(2));

            i++;

        }

    }

}
