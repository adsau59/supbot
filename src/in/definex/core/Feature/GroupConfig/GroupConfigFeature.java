package in.definex.core.Feature.GroupConfig;

import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.String.FeatureAndCommandDescription;

/**
 * Created by adam_ on 04-12-2017.
 */
public class GroupConfigFeature extends Feature {

    public static final String name = "GroupConfig";

    public GroupConfigFeature() {
        super(name, new Command[]{
            new ShowAvailableFeatureCommand(),
            new AddFeatureCommand(),
            new RemoveFeatureCommand(),
                new RoleCommand()
        });
    }

    @Override
    public String getDescription() {
        return FeatureAndCommandDescription.groupConfigDescription;
    }
}
