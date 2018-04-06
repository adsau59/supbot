package in.definex.core.Feature.Help;

import in.definex.core.Bot;
import in.definex.core.ChatSystem.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpFeatureCommand extends Command {

    public static final String name = "help-feature";


    public HelpFeatureCommand() {
        super(name, 1, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] args) {

        Feature feature = Bot.getFeatureManager().findFeatureByName(args[0]);

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
