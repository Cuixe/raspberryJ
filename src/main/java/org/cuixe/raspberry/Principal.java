package org.cuixe.raspberry;

public class Principal {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("CLI")) {
            CliMain.main(args);
        } else {
            ScheduledMain.main(args);
        }
    }
}
