public class Animal {
    String type = "";
    String trait = "";
    static int food = 0;
    static String[] sizeCategory = { "Smol", "Medium", "Ginormous" };
    static int currentSize = 0;
    static Animal friend[] = new Animal[1];

    public String eat(int e) {
        Animal.food = e;
        Animal.currentSize += 1;
        if (Animal.currentSize > 2) {
            Animal.currentSize = 0;
            return "The " + trait + type + " ate to much and has shrunk back down to 0...";
        }

        return "The " + trait + type + " eats " + e + " food and changes size! It's now "
                + Animal.sizeCategory[currentSize] + "!";
    }

    public static String hasFriend(Animal a) {
        if (friend[0] != null) {
            return " My friend is a " + (friend[0].type);
        } else {
            return null;
        }

    }

}
