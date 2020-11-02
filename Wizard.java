class Wizard implements Runnable {
    // How many spells the Wizard has casted
    private int castedSpells = 0;
    // The item at the left-side of the Wizard
    private final Item left;
    // Is the item at the left-side of the Wizard held by this Wizard?
    private boolean leftHeld = false;
    // The item at the right-side of the Wizard
    private final Item right;
    // Is the item at the right-side of the Wizard held by this Wizard?
    private boolean rightHeld = false;
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
            printAction("Deliberating");
            try {
                synchronized (left) {
                    pickUp(left);
                    this.leftHeld = true;
                    synchronized (right) {
                        pickUp(right);
                        this.rightHeld = true;
                        castSpell();
                        putDown(right);
                        this.rightHeld = false;
                    }
                    putDown(left);
                    this.leftHeld = true;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        printAction("Done");
    }

    /**
     * Pick up an item.
     * @param item The item to pickup.
     * @throws InterruptedException If Thread.sleep throws anything.
     */
    public void pickUp(Item item) throws InterruptedException {
        printAction("Grabbing " + item.getType());
        Thread.sleep(ACTION_DURATION);
        printState();
    }

    /**
     * Put down an item.
     * @param item The item to pickup.
     * @throws InterruptedException If Thread.sleep throws anything.
     */
    public void putDown(Item item) throws InterruptedException {
        printAction("Putting down " + item.getType());
        Thread.sleep(ACTION_DURATION);
        printState();
    }

    /**
     * Cast a spell.
     * @throws InterruptedException If Thread.sleep throws anything.
     */
    public void castSpell() throws InterruptedException {
        printAction("Casting spell");
        castedSpells++;
        Thread.sleep(ACTION_DURATION);
    }

    /**
      * Print action by the wizard.
      * @param action The action of the wizard.
      */
    public void printAction(String action) {
        System.out.println(Thread.currentThread().getName() + " " + action);
    }

    /**
     * Print the state of the wizard.
     */
    public void printState() {
        String left = "";
        if (this.leftHeld) {
            left = "Holding ";
        } else {
            left = "Not holding ";
        }
        left += this.left.getType(); // Thread safe as the type of an item never changes

        String right = "";
        if (this.rightHeld) {
            right = "Holding ";
        } else {
            right = "Not holding ";
        }
        right += this.right.getType(); // Thread safe as the type of an item never changes

        System.out.println(Thread.currentThread().getName() + " " + left + "; " + right);
    }
}
