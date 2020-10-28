public class Council {
    public static void main(String[] args) {
        // Instantiate 6 Wizards and let them cast their spells
        for (int i = 0; i < 6; i++) {
            Wizard w = new Wizard();
            (new Thread(w)).start();
        }
    }
}
