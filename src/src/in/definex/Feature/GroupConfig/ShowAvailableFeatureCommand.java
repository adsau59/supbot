package in.definex.Feature.GroupConfig;

import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.Feature.FeatureManager;
import in.definex.String.FeatureAndCommandDescription;

/**
 * Created by adam_ on 04-12-2017.
 */
public class ShowAvailableFeatureCommand extends Command {

    public static final String name = "showallfeatures";


    public ShowAvailableFeatureCommand() {
        super(name, 0, Client.Role.Admin);
    }

    @Override
    protected String compute(Client client, String[] args) {
        return "Available features are :\n"+ Bot.getFeatureManager().getListOfFeatures();
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.showAvailTemplateExample,
                FeatureAndCommandDescription.showAvailTemplateExample,
                FeatureAndCommandDescription.showAvailDescription
        );
    }
}
