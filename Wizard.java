class Wizard implements Runnable {
    private boolean hasWand = false;
    private boolean hasGem = false;
    private int castedSpells = 0;
    private String state = "Deliberating";

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
        hasWand = true;
        printState();
        Thread.sleep(1000);
    }

    public synchronized void putDownWand() throws InterruptedException {
        state = "Putting down wand";
        hasWand = false;
        printState();
        Thread.sleep(1000);
    }

    public synchronized void grabGem() throws InterruptedException {
        state = "Grabbing gem";
        hasGem = true;
        printState();
        Thread.sleep(1000);
    }

    public synchronized void putDownGem() throws InterruptedException {
        state = "Putting down gem";
        hasGem = false;
        printState();
        Thread.sleep(1000);
    }

    public synchronized void printState() {
        System.out.println(state);
    }
}
