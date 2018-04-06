package in.definex.core.Console;

import in.definex.core.Looper;

import java.util.Arrays;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

/**
 * Console
 * Enables to write console commands in the terminal
 *
 * Created by adam_ on 16-03-2018.
 */
public class Console {

    /**
     * Thread in which console loop is executed
     */
    private Thread myThread;
    /**
     * ConsoleCommandManager contains the list of commands
     */
    private ConsoleCommandManager consoleCommandManager;
    /**
     * Looper cache
     */
    private Looper looper;

    /**
     * Constructor
     *
     * @param looper looper cache
     */
    public Console(Looper looper){
        consoleCommandManager = new ConsoleCommandManager();
        myThread = new Thread("ConsoleThread"){
            @Override
            public void run() {
                waitForCommand();
            }
        };
        this.looper = looper;
    }

    public ConsoleCommandManager getConsoleCommandManager() {
        return consoleCommandManager;
    }
    public Thread getMyThread() {
        return myThread;
    }

    /**
     * Console loop
     * Waits for input, when gets it,
     * finds executes and returns response
     */
    private void waitForCommand(){

        Scanner scanner = new Scanner(System.in);

        while(!looper.isQuit()){
            System.out.print("?");
            String[] command = scanner.nextLine().split(" ");


            //TODO crashes if doesnt have the specified command
            //recognize command and sends it for processing
            String result = consoleCommandManager.getConsoleCommandByKeyword(command[0]).runCommand(Arrays.copyOfRange(command, 1, command.length));

            System.out.println(result);
        }

    }

}
