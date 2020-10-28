class Wizard extends Main implements Runnable {
    // How many spells the Wizard has casted
    private int castedSpells = 0;
    // The item at the left-side of the Wizard
    private final Item left;
    // The item at the right-side of the Wizard
    private final Item right;
    // Duration of the actions
    private final int ACTION_DURATION = 1000;

    /**
     * Default constructor.
     * @param left  The item at the left-side.
     * @param right The item at the right-side.
     */
    public Wizard(Item left, Item right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (castedSpells < 3) {
            printState("Deliberating");
            try {
                synchronized (left) {
                    pickUp(left);
                    synchronized (right) {
                        pickUp(right);
                        castSpell();
                    }
                    printState("Deliberating");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /**
     * Take up an item.
     * @param item The item to pickup.
     * @throws InterruptedException If Thread.sleep throws anything.
     */
    public void pickUp(Item item) throws InterruptedException {
        printState("Grabbing " + item.getType());
        Thread.sleep(ACTION_DURATION);
    }

    /**
     * Cast a spell.
     * @throws InterruptedException If Thread.sleep throws anything.
     */
    public void castSpell() throws InterruptedException {
        printState("Casting spell");
        castedSpells++;
        Thread.sleep(ACTION_DURATION);
    }

    /**
     * Print the state of the wizard.
     * @param state The state of the wizard.
     */
    public void printState(String state) {
        System.out.println(Thread.currentThread().getName() + " " + state);
    }
}
