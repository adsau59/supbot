package in.definex.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ConsoleCommandManager
 * maintains the list of console commands
 *
 * Created by adam_ on 16-03-2018.
 */
public class ConsoleCommandManager {

    private List<ConsoleCommand> consoleCommandList;

    public ConsoleCommandManager() {
        consoleCommandList = new ArrayList<>();
    }


    /**
     * Adds new console command to the manager,
     * called in init() method in Looper class
     *
     * @param consoleCommands commands to be added
     */
    public void add(ConsoleCommand... consoleCommands){
        consoleCommandList.addAll(Arrays.asList(consoleCommands));
    }

    /**
     * Searches for commands from the consoleCommandList by keyword
     * @param keyword keyword of the command to be search
     * @return ConsoleCommand object, null if not found
     */
    ConsoleCommand getConsoleCommandByKeyword(String keyword){
        for (ConsoleCommand cc: consoleCommandList) {
            if(cc.getKeyword().equals(keyword))
                return cc;
        }
        return null;
    }

}
