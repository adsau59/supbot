package in.definex.Feature.Accounts;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

public class WhoIsCommand extends Command {

    private static final String command = "whois";

    public WhoIsCommand() {
        super(command, 1, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] args) {

        Client c = Client.GetClientWithAlias(args[0]);

        if(c == null)
            return "No user exists with alias "+args[0];

        return String.format("Name of client with alias %s is\n%s", args[0], c.getName());
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                Strings.commandPrefix+command+" [alias]",
                Strings.commandPrefix + command +" RIPjoker",
                "Find out the name of the client hiding behind an alias."
        );
    }
}
