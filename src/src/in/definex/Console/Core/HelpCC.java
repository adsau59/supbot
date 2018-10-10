package in.definex.Console.Core;

import in.definex.Bot;
import in.definex.Console.Console;
import in.definex.Console.ConsoleCommand;
import in.definex.String.Strings;

/**
 * HelpCC : ConsoleCommand
 * shows the available commands and how to use them
 *
 * Usage:
 * help
 * help COMMAND_KEYWORD
 *
 * Example:
 * help run
 */
public class HelpCC extends ConsoleCommand {

    public HelpCC() {
        super("help", -1);
    }



    @Override
    public String compute(String[] args) {
        if(args.length == 0){

            String result = "Available Console Commands:\nUse \"help COMMAND_KEYWORD\" to get more info on a keyword\n\n";

            for(ConsoleCommand c: Bot.getConsole().getConsoleCommandManager().getConsoleCommandList()){
                result += c.getKeyword()+"\n";
            }

            return result;

        }

        ConsoleCommand t = null;
        for(ConsoleCommand c: Bot.getConsole().getConsoleCommandManager().getConsoleCommandList()){
            if(c.getKeyword().equals(args[0])){
                t = c;
            }
        }

        if(t == null)
            return "Console Command not found";

        return String.format(
                Strings.helpConsoleCommandFormate,
                t.getClass().getSimpleName(),
                t.getHelper().description,
                t.getHelper().template,
                t.getHelper().example);

    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "help\n" +
                        "help COMMAND_KEYWORD",
                "help run",
                "shows the available commands and how to use them"
        );
    }
}
