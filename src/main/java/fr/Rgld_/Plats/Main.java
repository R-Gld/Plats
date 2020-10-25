package fr.Rgld_.Plats;

import fr.Rgld_.Plats.Helpers.Console;
import fr.Rgld_.Plats.Storage.Data;
import org.apache.commons.lang3.SystemUtils;

public class Main {

    private final Console console;
    private final Data data;

    public static void main(String[] args) { new Main(args); }
    public Main(String[] args) {
        console = new Console(this);
        Thread consoleTh = new Thread(console, "Console");
        consoleTh.start();
        if(!SystemUtils.IS_OS_LINUX) {
            System.out.println("Plats must be ran with Linux.");
            System.exit(0);
        }
        data = new Data(this);
    }

    public String getVersion() {
        return "1.0";
    }


    public Data getDatas() {
        return data;
    }

    public Console getConsole() {
        return console;
    }
}
