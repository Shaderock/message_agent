package broker.utils;

import broker.Context;
import broker.models.Module;
import broker.servers.Worker;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalHandler extends Thread {
    @Override
    public void run() {
        askForProcessStop();
    }

    private void askForProcessStop() {
        String input;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("Type 'stop' to stop the process: ");
            input = in.nextLine();
        }
        while (!input.equals("stop"));

        System.out.println("Shutting down server");

        Context context = Context.getInstance();
        context.APP_IS_SHUT_DOWN = true;

        context.getServer().shutdownNow();

        for (Worker worker : context.getWorkers()) {
            worker.interrupt();
        }

        ArrayList<Module> modulesToDisable = new ArrayList<>(context.getModules());
        for (Module module : modulesToDisable) {
            ModuleRemover.removeModule(module);
        }
    }
}
