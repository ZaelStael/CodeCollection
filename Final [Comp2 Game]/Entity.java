public class Entity {
    String entName;
    String entDesc;
    int timesSpoke;
    boolean mad;
    // Item[] npcInv; For kev

    public Entity() {

    };

    public void Entity() {

        String entName;
        String entDesc;
        int timesSpoke = 0;
        boolean mad = false;

    }

    public static void entityList() {
        Entity Bill = new Entity();
        Bill.entName = "bill";
        Bill.entDesc = "A pimble covered young boy stands at the ticket counter, a bored face, hoping-- searching for anyhting of substance to look at.";
        Bill.timesSpoke = 0;
        Bill.mad = false;

        Entity Tyler = new Entity();
        Tyler.entName = "tyler";
        Tyler.entDesc = "A horrifying creature emodying some cruel mix of a lion, a ghost, and a bloody tampon comes shambling at you with outstreched arms.";
        Tyler.timesSpoke = 0;
        Tyler.mad = false;

        Entity Bob = new Entity();
        Bob.entName = "bob";
        Bob.entDesc = "A smug look rests upon her face, as if your presence alone was enough to ruin her day. You look down at her nametag to see who you'll be addressing... Bob...";
        Bob.timesSpoke = 0;
        Bob.mad = false;

    }

}