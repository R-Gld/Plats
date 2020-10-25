package fr.Rgld_.Plats.Helpers;

public class Interpreter {

    private final Console console;

    public Interpreter(Console console) {
        this.console = console;
    }

    public void parse(String line) {
        switch(line.toLowerCase()) {
            case "stop":
            case "exit":
                console.interrupt();
                System.out.println("Plats is closing...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                return;
            case "h":
            case "help":
                sendHelp();
                return;
        }
        sendHelp();
    }
    public void sendHelp() {
        sendHelp(true);
    }

        public void sendHelp(boolean top_bottom) {
        String v = console.getMain().getVersion();
        if(top_bottom)
            System.out.println("-----=={ Plats v" + v + " }==-----");
        System.out.println("  • help - Show this help page. (alias: h)");
        System.out.println("  • exit - Stop the processus (alias: stop).");
        if(top_bottom)
            System.out.println("-----=={ Plats v" + v + " }==-----");
    }
}
