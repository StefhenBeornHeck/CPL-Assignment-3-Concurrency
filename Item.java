public class Item {
    private final String type;
    private boolean pickedUp = false;

    public Item(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean isWand() {
        return getType().equals("wand");
    }

    public boolean isGem() {
        return getType().equals("gem");
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean isPickedUp) {
        this.pickedUp = isPickedUp;
    }
}
