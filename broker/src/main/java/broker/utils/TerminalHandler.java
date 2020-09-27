package broker.utils;

import broker.Context;
import broker.actions.not_requests.ModuleRemover;
import broker.models.Module;
import broker.models.PortData;
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

        Context context = Context.getInstance();
        context.APP_IS_SHUT_DOWN = true;

        // todo shut down server

        for (Worker worker : context.getWorkers()) {
            worker.interrupt();
        }

        ArrayList<Module> modulesToDisable = new ArrayList<>();

        for (PortData portsDatum : context.getPortsData()) {
            modulesToDisable.addAll(portsDatum.getModules());
        }

        for (Module module : modulesToDisable) {
            ModuleRemover.removeModuleFromStorage(module);
        }
    }
}
