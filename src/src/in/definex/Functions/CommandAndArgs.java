package in.definex.Functions;

import in.definex.String.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static CommandAndArgs Split(String text, Boolean useCommandPrefix){

        String rhs;
        if(useCommandPrefix)
            rhs = text.split(Strings.commandPrefix)[1];
        else
            rhs = text;

        List<String> matchList = new ArrayList<>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(rhs);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }

        String[] rest = matchList.toArray(new String[0]);
        return new CommandAndArgs(rest[0], Arrays.copyOfRange(rest, 1, rest.length));

    }

    public static CommandAndArgs Split(String text){
        return Split(text, true);
    }

}