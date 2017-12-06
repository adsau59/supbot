package in.definex.core.Feature.Help;

import in.definex.core.Action.ActionManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpCommandCommand extends Command {

    public static final String name = "help-command";

    private FeatureManager featureManager;

    public HelpCommandCommand(ActionManager actionManager, WebDriver driver, FeatureManager featureManager) {
        super(actionManager, driver, name, 1, Client.Role.Unregistered);
        this.featureManager = featureManager;
    }

    @Override
    protected String compute(Client client, String[] args) {
        FeatureManager.FeatureAndCommand featureAndCommand = featureManager.findFeatureByCommandKeyword(args[0]);

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
