public class Council {
    protected static Item[] items = new Item[]{
            new Item("gem"),
            new Item("wand"),
            new Item("gem"),
            new Item("wand"),
            new Item("gem"),
            new Item("wand"),
    };

    public static void main(String[] args) {
        // Instantiate 6 Wizards and let them cast their spells
        for (int i = 0; i < 6; i++) {
            int left = i - 1;
            int right = i + 1;
            if (left < 0) left = 5;
            if (right > 5) right = 0;
            Wizard w = new Wizard(Council.items[left], Council.items[right]);
            (new Thread(w)).start();
        }
    }
}
