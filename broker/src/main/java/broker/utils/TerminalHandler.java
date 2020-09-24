package broker.utils;

import broker.Context;
import broker.actions.not_requests.ModuleRemover;
import broker.communication.MessageGenerator;
import broker.models.Module;
import broker.models.PortData;
import broker.models.protocols.Operation;
import broker.servers.Finishable;

import java.util.Scanner;

public class TerminalHandler extends Thread {
    @Override
    public void run() {
        askForProcessStop();
    }

    private void askForProcessStop() {
        String input = "";
        Scanner in = new Scanner(System.in);

        System.out.println("Type 'stop' to stop the process: ");

        while (!input.equals("stop")) {
            input = in.nextLine();
        }

        Context context = Context.getInstance();
        MessageGenerator messageGenerator = new MessageGenerator();
        // todo fix problems
        for (PortData portsDatum : context.getPortsData()) {
            for (Module module : portsDatum.getModules()) {
                messageGenerator.sendMessage(Operation.CLOSE, null, module);
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ModuleRemover.removeModuleFromStorage(module);
            }
        }

        for (Finishable finishable : context.getWorkers()) {
            finishable.finish();
        }
    }
}
