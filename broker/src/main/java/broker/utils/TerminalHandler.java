package broker.utils;

import broker.Context;
import broker.models.GrpcModule;
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

        context.getServer().shutdownNow();

        for (Worker worker : context.getWorkers()) {
            worker.interrupt();
        }

        ArrayList<GrpcModule> modulesToDisable = new ArrayList<>(context.getGrpcModules());
        for (GrpcModule module : modulesToDisable) {
            ModuleRemover.removeModule(module);
        }
    }
}
