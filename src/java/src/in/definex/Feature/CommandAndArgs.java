package in.definex.Feature;

import in.definex.String.Strings;

import java.util.Arrays;

/**
 * CommandAndArgs
 * wrapper class for Command and arguments used by feature and command
 *
 * Created by adam_ on 01-12-2017.
 */
public class CommandAndArgs {
    public String cmd;
    public String[] args;

    public CommandAndArgs(String cmd, String[] args) {
        this.cmd = cmd;
        this.args = args;
    }

    /**
     * Splits text to command and arguments
     * @param text text to be converted to CommandAndArgs
     * @return CommandAndArgs object
     */
    public static CommandAndArgs Split(String text){

        String rhs = text.split(Strings.commandPrefix)[1];
        String[] rest = rhs.split(" ");

        return new CommandAndArgs(rest[0], Arrays.copyOfRange(rest, 1, rest.length));

    }

}