package in.definex.Feature.GroupConfig;

import in.definex.Bot;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.Feature.Feature;
import in.definex.String.FeatureAndCommandDescription;
import in.definex.String.Strings;

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

        if(!client.getChatGroup().hasFeature(newFeature))
            return Strings.featureHasNotBeenAdded;

        client.getChatGroup().removeFeature(newFeature);

        return String.format(Strings.successfullyRemovedFeature, newFeature.getName());
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
