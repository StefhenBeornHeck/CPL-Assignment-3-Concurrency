public class Item {
    // The type of this item
    private final String type;

    /**
     * Default constructor.
     * @param type The type of item ("wand" / "gem").
     */
    public Item(String type) {
        this.type = type;
    }

    /**
     * Retrieve the type of this item.
     * @return The type of this item.
     */
    public String getType() {
        return type;
    }
}
