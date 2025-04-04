package objectAdventure.world.tbaker17;

public record Notification(Object n, String m) {

    public Notification(String m) {
        this(new Object(), m);
    }

    @Override
    public String m() {
        return m;
    }
}