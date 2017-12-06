package in.definex.core.Feature.Help;

import in.definex.core.Action.ActionManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpFeatureCommand extends Command {

    public static final String name = "help-feature";

    private FeatureManager featureManager;

    public HelpFeatureCommand(ActionManager actionManager, WebDriver driver, FeatureManager featureManager) {
        super(actionManager, driver, name, 1, Client.Role.Unregistered);
        this.featureManager = featureManager;
    }

    @Override
    protected String compute(Client client, String[] args) {

        Feature feature = featureManager.findFeatureByName(args[0]);

        if(feature == null)
            return Strings.featureDoesntExisits;

        String commands = "";

        for(Command command:feature.getCommands()){
            commands += command.getHelper().getTemplate()+"\n";
        }

        return String.format(Strings.helpFeatureResponse, feature.getName(), feature.getDescription(), commands);
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.helpFeatureTemplate,
                FeatureAndCommandDescription.helpFeatureExample,
                FeatureAndCommandDescription.helpFeatureDescription
        );
    }
}
