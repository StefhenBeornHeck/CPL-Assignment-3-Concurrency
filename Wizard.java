class Wizard extends Main implements Runnable  {
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

    public Wizard(Item left, Item right) {
        this.left = left;
        this.right = right;
    }

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
                        left.putDown();
                        right.putDown();
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

    public void pickUp(Item item) throws InterruptedException {
        item.pickUp();
        state = "Grabbing " + item.getType();
        printState();
        Thread.sleep(ACTION_DURATION);
    }

    public void castSpell() throws InterruptedException {
        state = "Casting spell";
        printState();
        castedSpells++;
        Thread.sleep(ACTION_DURATION);
    }

    public void printState() {
        System.out.println(Thread.currentThread().getName() + " " + state);
    }
}
