package in.definex.Feature.Help;

import in.definex.Feature.Command;
import in.definex.Feature.Feature;
import in.definex.String.FeatureAndCommandDescription;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpFeature extends Feature {

    public static final String name = "Help";

    public HelpFeature() {
        super(name, new Command[]{
            new HelpCommand(),
                new HelpFeatureCommand(),
                new HelpCommandCommand()
        });
    }

    @Override
    public String getDescription() {
        return FeatureAndCommandDescription.helpDescription;
    }
}
