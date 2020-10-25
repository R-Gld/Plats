package fr.Rgld_.Plats.Helpers;

import fr.Rgld_.Plats.Main;

import java.util.Scanner;

public class Console implements Runnable {

    private final Main main;
    private Scanner scanner = new Scanner(System.in);

    public Console(Main main) {
        this.main = main;
    }

    public void interrupt() {
        this.launched = false;
    }

    private boolean launched = true;

    /**
     *
     *  _______  ___      _______  _______  _______
     * |       ||   |    |   _   ||       ||       |
     * |    _  ||   |    |  |_|  ||_     _||  _____|
     * |   |_| ||   |    |       |  |   |  | |_____
     * |    ___||   |___ |   _   |  |   |  |_____  |
     * |   |    |       ||  | |  |  |   |   _____| |
     * |___|    |_______||__| |__|  |___|  |_______|
     *
     *
     *
     */

    public void run() {
        Interpreter interpreter = new Interpreter(this);
        System.out.println(
                "________________________________________________");
        System.out.println(
              "\n _______  ___      _______  _______  _______ \n" +
                "|       ||   |    |   _   ||       ||       |\n" +
                "|    _  ||   |    |  |_|  ||_     _||  _____|\n" +
                "|   |_| ||   |    |       |  |   |  | |_____ \n" +
                "|    ___||   |___ |   _   |  |   |  |_____  |\n" +
                "|   |    |       ||  | |  |  |   |   _____| |\n" +
                "|___|    |_______||__| |__|  |___|  |_______|\n");
        interpreter.sendHelp(false);
        System.out.println(
                "________________________________________________");
        System.out.println("\n\n");
        while(launched) {
            if(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                interpreter.parse(line);
            }
        }
    }

    public Main getMain() {
        return main;
    }
}
