package in.definex.Feature.Accounts;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.FeatureAndCommandDescription;
import in.definex.String.Strings;

import static in.definex.ChatSystem.Client.Role.*;

/**
 * Created by adam_ on 04-12-2017.
 */
public class PromoteCommand extends Command {

    public static final String name = "promote";

    public PromoteCommand() {
        super(name, 1, Client.Role.CoAdmin);
    }

    @Override
    protected String compute(Client client, String[] args) {
        Client target = Client.getClient(args[0],client.getGroupId());

        if(target == null)
            return Strings.targetClientIsNotRegistered;


        if(target == client)
            return Strings.dontPromoteYourself;

        Client.Role newRole = null;

        switch(target.getRole()){

            case Member:
                newRole = Elder;
                break;

            case Elder:
                if(!client.getRole().hasPermission(Admin))
                    return Strings.youDontHaveAuthorityToPromote;
                newRole = CoAdmin;
                break;

            case CoAdmin:
                if(Client.getClientsWithRole(client.getGroupId(), Admin).size() > 0)
                    return Strings.thereCanBeOnlyOneAdminInAGroup;
                if(!client.getRole().hasPermission(SuperAdmin))
                    return Strings.youDontHaveAuthorityToPromote;
                newRole = Admin;
                break;

            case Admin:
                return Strings.thereIsNoRoleAboveAdmin;

            case SuperAdmin:
                return Strings.youDontHaveAuthorityToPromote;

        }

        Client.Role oldRole = target.getRole();
        target.changeRole(newRole);


        return String.format(Strings.successfullyChangedRole, target.getName(), oldRole, newRole);
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.promoteTemplate,
                FeatureAndCommandDescription.promoteExample,
                FeatureAndCommandDescription.promoteDescription
        );
    }
}
