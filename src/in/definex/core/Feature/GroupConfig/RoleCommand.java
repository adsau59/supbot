package in.definex.core.Feature.GroupConfig;

import in.definex.core.ChatSystem.Client;
import in.definex.core.Feature.Command;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;

/**
 * Created by adam_ on 04-12-2017.
 */
public class RoleCommand extends Command {

    public static final String name = "role";

    public RoleCommand() {
        super(name, -1, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] args) {

        if(args.length == 0)
            return String.format(Strings.roleResponse, client.getName(), client.getRole().name());

        Client target = Client.getClient(args[0], client.getGroupId());

        if(target == null)
            return Strings.targetClientIsNotRegistered;



        return String.format(Strings.roleResponse, target.getName(), target.getRole().name());
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.roleTemplate,
                FeatureAndCommandDescription.roleExample,
                FeatureAndCommandDescription.roleDescription
        );
    }
}
