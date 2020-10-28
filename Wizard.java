class Wizard extends Council implements Runnable  {
    // Whether the Wizard has a wand in a hand
    private boolean hasWand = false;
    // Whether the Wizard has a gem in a hand
    private boolean hasGem = false;
    // How many spells the Wizard has casted
    private int castedSpells = 0;
    // The state of the Wizard
    private String state = "Deliberating";
    // The item at the left-side of the Wizard
    private final Item left;
    // The item at the right-side of the Wizard
    private final Item right;

    public Wizard(Item left, Item right) {
        this.left = left;
        this.right = right;
    }

    public void run() {
        printState();
        while (castedSpells < 3) {
            try {
                castSpell();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public synchronized void castSpell() throws InterruptedException {
        if (hasWand && hasGem) {
            System.out.println("Casting spell");
            putDownGem();
            putDownWand();
            castedSpells++;
            Thread.sleep(1000);
        } else {
            grabWand();
            grabGem();
        }
    }

    public synchronized void grabWand() throws InterruptedException {
        state = "Grabbing wand";
        Item wand = left.isWand() ? left : right;
        if (!wand.isPickedUp()) {
            hasWand = true;
            wand.setPickedUp(true);
        }
        printState();
        Thread.sleep(1000);
    }

    public synchronized void putDownWand() throws InterruptedException {
        state = "Putting down wand";
        Item wand = left.isWand() ? left : right;
        wand.setPickedUp(false);
        hasWand = false;
        printState();
        Thread.sleep(1000);
    }

    public synchronized void grabGem() throws InterruptedException {
        state = "Grabbing gem";
        Item gem = left.isGem() ? left : right;
        if (!gem.isPickedUp()) {
            hasGem = true;
            gem.setPickedUp(true);
        }
        printState();
        Thread.sleep(1000);
    }

    public synchronized void putDownGem() throws InterruptedException {
        state = "Putting down gem";
        Item gem = left.isGem() ? left : right;
        gem.setPickedUp(false);
        hasGem = false;
        printState();
        Thread.sleep(1000);
    }

    public synchronized void printState() {
        System.out.println(state);
    }
}
