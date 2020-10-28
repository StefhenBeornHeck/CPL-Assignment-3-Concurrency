public class Item {
    private final String type;
    private boolean pickedUp = false;

    public Item(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void pickUp() {
        pickedUp = true;
    }

    public void putDown() {
        pickedUp = false;
    }
}
