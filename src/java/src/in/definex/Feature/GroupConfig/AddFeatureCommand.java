package in.definex.Feature.GroupConfig;

import in.definex.Bot;
import in.definex.ChatSystem.ChatGroupsManager;
import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.Feature.Feature;
import in.definex.Feature.FeatureManager;
import in.definex.String.FeatureAndCommandDescription;
import in.definex.String.Strings;

/**
 * Created by adam_ on 04-12-2017.
 */
public class AddFeatureCommand extends Command {

    public static final String name = "add-feature";


    public AddFeatureCommand() {
        super(name, 1, Client.Role.CoAdmin);
    }

    @Override
    protected String compute(Client client, String[] args) {

        Feature newFeature = Bot.getFeatureManager().findFeatureByName(args[0]);

        if(newFeature == null)
            return Strings.featureDoesntExisits;

        if(client.getChatGroup().hasFeature(newFeature))
            return Strings.featureAlreadyAdded;

        client.getChatGroup().addFeature(newFeature);

        return String.format(Strings.succesffullyAddedFeature, newFeature.getName());
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.addFeatureTemplate,
                FeatureAndCommandDescription.addFeatureExample,
                FeatureAndCommandDescription.addFeatureDescription
        );
    }

}
