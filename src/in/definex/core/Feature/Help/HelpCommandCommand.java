package in.definex.core.Feature.Help;

import in.definex.core.Bot;
import in.definex.core.ChatSystem.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpCommandCommand extends Command {

    public static final String name = "help-command";

    public HelpCommandCommand() {
        super(name, 1, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] args) {
        FeatureManager.FeatureAndCommand featureAndCommand = Bot.getFeatureManager().findFeatureByCommandKeyword(args[0]);

        if(featureAndCommand == null)
            return Strings.commandDoesntExists;

        Command command = featureAndCommand.command;
        return String.format(Strings.helpCommandResponse, command.getKeyword(), command.getHelper().getDescription(), command.getHelper().getTemplate(), command.getHelper().getExample(), command.getMinRole().name());
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.helpCommandCommandTemplate,
                FeatureAndCommandDescription.helpCommandCommandExample,
                FeatureAndCommandDescription.helpCommandCommandDescription
        );
    }
}
