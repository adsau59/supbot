package in.definex.Feature.Accounts;

import in.definex.ChatSystem.ChatGroup;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.Strings;

import java.sql.Statement;

public class SetAliasCommand extends Command {

    private static final String command = "set-alias";

    public SetAliasCommand() {
        super(command, 1, Client.Role.Member);
    }

    @Override
    protected String compute(Client client, String[] args) {

        ChatGroup group = client.getChatGroup();

        String alias = args[0];

        for (Client c:group.getAllClients())
        {
            if(c.getAlias().equals(alias))
                return "Alias already taken.";
        }

        client.setAlias(alias);

        return "Alias successfully changed.";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                Strings.commandPrefix + command + "[alias]",
                Strings.commandPrefix + command + "RIPjoker",
                "Set an alias so that the bot can refer you by your alias instead of your number, some commnads might still use your number to refer you."
        );
    }
}
