/**
 * Compile
 *  - javac GatheringWizards.java
 *
 * Run
 *  - java GatheringWizards
 */

public class GatheringWizards {
    public static void main(String[] args) {
        Wizard[] wizards = new Wizard[6];
        Item[] items = new Item[]{
                new Item("gem"),
                new Item("wand"),
                new Item("gem"),
                new Item("wand"),
                new Item("gem"),
                new Item("wand"),
        };

        // Instantiate 6 Wizards and let them cast their spells
        for (int i = 0; i < 6; i++) {
            Item left = items[i];
            Item right = items[(i + 1) % 6];

            if (i == items.length - 1) {
                // Pick different order for 1 so there is no deadlock
                wizards[i] = new Wizard(left, right);
            } else {
                wizards[i] = new Wizard(right, left);
            }
            (new Thread(wizards[i], "Wizard " + i)).start();
        }
    }
}
