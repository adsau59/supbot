package in.definex.Feature.Help;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.Feature.Feature;
import in.definex.String.FeatureAndCommandDescription;
import in.definex.String.Strings;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpCommand extends Command {

    public static final String name = "help";

    public HelpCommand() {
        super(name, 0, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] args) {
        String availFeatures = "";

        for(Feature feature: client.getChatGroup().getMyFeatures()){
            availFeatures+=feature.getName()+"\n";
        }

        return String.format(Strings.helpResponse, availFeatures);
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.helpCommandTemplateExample,
                FeatureAndCommandDescription.helpCommandTemplateExample,
                FeatureAndCommandDescription.helpCommandDescription
        );
    }
}
