package in.definex.Feature.Accounts;

import in.definex.ChatSystem.Client;
import in.definex.Feature.Command;
import in.definex.String.FeatureAndCommandDescription;
import in.definex.String.Strings;

import static in.definex.ChatSystem.Client.Role.CoAdmin;
import static in.definex.ChatSystem.Client.Role.SuperAdmin;

/**
 * Created by adam_ on 04-12-2017.
 */
public class DemoteCommand extends Command {

    public static final String name = "demote";

    public DemoteCommand() {
        super(name, 1, Client.Role.CoAdmin);
    }

    @Override
    protected String compute(Client client, String[] args) {
        Client target = Client.getClient(args[0],client.getGroupId());

        if(target == null)
            return Strings.targetClientIsNotRegistered;

        if(target == client)
            return Strings.dontDemoteYourself;

        Client.Role newRole = null;

        switch(target.getRole()){

            case Member:
                return Strings.cantDemoteMember;

            case Elder:
                newRole = Client.Role.Member;
                break;

            case CoAdmin:
                if(!client.getRole().hasPermission(Client.Role.Admin))
                    return Strings.youDontHaveAuthorityToDemote;
                newRole = Client.Role.Elder;
                break;

            case Admin:
                if(!client.getRole().hasPermission(SuperAdmin))
                    return Strings.youDontHaveAuthorityToDemote;
                newRole = CoAdmin;
                break;

            case SuperAdmin:
                return Strings.youDontHaveAuthorityToDemote;

        }

        Client.Role oldRole = target.getRole();
        target.changeRole(newRole);


        return String.format(Strings.successfullyChangedRole, target.getName(), oldRole, newRole);
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.demoteTemplate,
                FeatureAndCommandDescription.demoteExample,
                FeatureAndCommandDescription.demoteDescription
        );
    }
}
