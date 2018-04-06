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
public class HelpCommand extends Command {

    public static final String name = "help";

    public HelpCommand() {
        super(name, 0, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] args) {
        String availFeatures = "";

        for(Feature feature: client.getChatGroup(Bot.getChatGroupsManager()).getMyFeatures()){
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
