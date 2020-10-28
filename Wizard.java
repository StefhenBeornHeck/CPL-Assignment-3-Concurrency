class Wizard extends Main implements Runnable {
    // How many spells the Wizard has casted
    private int castedSpells = 0;
    // The state of the Wizard
    private String state = "Deliberating";
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
            state = "Deliberating";
            printState();
            try {
                synchronized (left) {
                    pickUp(left);
                    synchronized (right) {
                        pickUp(right);
                        castSpell();
                    }
                    state = "Deliberating";
                    printState();
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
        state = "Grabbing " + item.getType();
        printState();
        Thread.sleep(ACTION_DURATION);
    }

    /**
     * Cast a spell.
     * @throws InterruptedException If Thread.sleep throws anything.
     */
    public void castSpell() throws InterruptedException {
        state = "Casting spell";
        printState();
        castedSpells++;
        Thread.sleep(ACTION_DURATION);
    }

    /**
     * Print the state of the wizard.
     */
    public void printState() {
        System.out.println(Thread.currentThread().getName() + " " + state);
    }
}
