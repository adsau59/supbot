package in.definex.core.Functions;

import in.definex.core.String.Strings;

import java.util.Arrays;

/**
 * Created by adam_ on 01-12-2017.
 */
public class CommandAndArgs {
    public String cmd;
    public String[] args;

    public CommandAndArgs(String cmd, String[] args) {
        this.cmd = cmd;
        this.args = args;
    }

    public static CommandAndArgs Split(String text){

        String rhs = text.split(Strings.commandPrefix)[1];
        String[] rest = rhs.split(" ");

        return new CommandAndArgs(rest[0], Arrays.copyOfRange(rest, 1, rest.length));

    }

}