package in.definex.core.Feature.GroupConfig;

import in.definex.core.Bot;
import in.definex.core.ChatSystem.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;

/**
 * Created by adam_ on 04-12-2017.
 */
public class RemoveFeatureCommand extends Command {

    public static final String name = "remove-feature";

    public RemoveFeatureCommand() {
        super(name, 1, Client.Role.CoAdmin);
    }

    @Override
    protected String compute(Client client, String[] args) {

        Feature newFeature = Bot.getFeatureManager().findFeatureByName(args[0]);

        if(newFeature == null)
            return Strings.featureDoesntExisits;

        if(!client.getChatGroup(Bot.getChatGroupsManager()).hasFeature(newFeature))
            return Strings.featureHasNotBeenAdded;

        client.getChatGroup(Bot.getChatGroupsManager()).removeFeature(newFeature);

        return String.format(Strings.succesffullyAddedFeature, newFeature.getName());
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.removeFeatureTemplate,
                FeatureAndCommandDescription.removeFeatureExample,
                FeatureAndCommandDescription.removeFeatureDescription
        );
    }

}
