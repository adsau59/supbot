package in.definex.core.Feature.Accounts;

import in.definex.core.Action.ActionManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

import static in.definex.core.Client.Role.CoAdmin;
import static in.definex.core.Client.Role.SuperAdmin;

/**
 * Created by adam_ on 04-12-2017.
 */
public class DemoteCommand extends Command {

    public static final String name = "demote";

    public DemoteCommand(ActionManager actionManager, WebDriver driver) {
        super(actionManager, driver, name, 1, Client.Role.CoAdmin);
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
