package in.definex.Feature.Help;

import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.Feature.Feature;
import in.definex.String.FeatureAndCommandDescription;
import in.definex.String.Strings;

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
