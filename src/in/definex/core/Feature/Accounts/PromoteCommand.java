package in.definex.core.Feature.Accounts;

import in.definex.core.Action.ActionManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

import static in.definex.core.Client.Role.*;

/**
 * Created by adam_ on 04-12-2017.
 */
public class PromoteCommand extends Command {

    public static final String name = "promote";

    public PromoteCommand(ActionManager actionManager, WebDriver driver) {
        super(actionManager, driver, name, 1, Client.Role.CoAdmin);
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
